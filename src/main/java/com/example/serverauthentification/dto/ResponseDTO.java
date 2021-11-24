package com.example.serverauthentification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private Date created;
    private String id;
    private String refreshToken;
    private long ttl;
    private String userId;
}
