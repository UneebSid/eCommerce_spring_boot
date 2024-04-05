package com.training.ecommerce.rest.api.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse
{
    private String message;
    private String token;
    private Boolean error;

}
