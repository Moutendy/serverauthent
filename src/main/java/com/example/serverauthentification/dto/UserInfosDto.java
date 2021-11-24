package com.example.serverauthentification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfosDto {
    private String cin;
    private String completeName;
    private String dn;
    private String email;
    private String employeenumber;
    private String firstName;
    private String id;
    private String lastName;
    private String sexe;
    private String sn;
    private String title;
    private String userOU;
    private String userRAT;
    private String username;
}
