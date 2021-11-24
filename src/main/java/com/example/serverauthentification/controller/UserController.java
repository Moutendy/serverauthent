package com.example.serverauthentification.controller;

import com.example.serverauthentification.dto.ApiResponse;
import com.example.serverauthentification.dto.ResponseDTO;
import com.example.serverauthentification.dto.UserDTO;
import org.keycloak.OAuth2Constants;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.authorization.client.util.HttpResponseException;
import org.keycloak.representations.AccessTokenResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RequestMapping(value = "/api/AppUsers")
@RestController
@CrossOrigin("*")
public class UserController {

    private static final Logger log = (Logger) LoggerFactory.getLogger(UserController.class);

    @Value("${keycloak.auth-server-url}")
    private String authServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("545da293-d02b-4500-bf70-995cb665a491")
    private String clientSecret;



    @PostMapping(path = "/login")
    public ResponseEntity<?> signin(@RequestBody UserDTO userDTO) {

        try {

            Map<String, Object> clientCredentials = new HashMap<>();
            clientCredentials.put("secret", clientSecret);
            clientCredentials.put("grant_type", OAuth2Constants.PASSWORD);
            log.info("login attempt for user : " + userDTO.getUsername());
            Configuration configuration = new Configuration(authServerUrl, realm, "admin-cli", clientCredentials, null);
            AuthzClient authzClient = AuthzClient.create(configuration);
            AccessTokenResponse response = authzClient.obtainAccessToken(userDTO.getUsername(), userDTO.getPassword());
            ResponseDTO dto = new ResponseDTO();
            dto.setCreated(new Date());
            dto.setId(response.getToken());
            dto.setRefreshToken(response.getRefreshToken());
            dto.setTtl(response.getExpiresIn());
            dto.setUserId(userDTO.getUsername());
            return ResponseEntity.ok(dto);

        } catch (HttpResponseException e) {

            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Bad Credentials."),
                    HttpStatus.resolve(e.getStatusCode()));
        }
    }

}
