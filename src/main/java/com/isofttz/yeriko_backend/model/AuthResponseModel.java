package com.isofttz.yeriko_backend.model;

import com.isofttz.yeriko_backend.entities.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseModel {
    private int statusCode;

    private String message;

    private String accessToken;

    private Long loginTime;

    private Long expirationDuration;

    private Users userDetails;
}

