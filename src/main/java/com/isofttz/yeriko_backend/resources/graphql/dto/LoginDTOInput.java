package com.isofttz.yeriko_backend.resources.graphql.dto;

import lombok.Data;

@Data
public class LoginDTOInput {
    private String userName;
    private String password;
}