package com.training.ecommerce.rest.api.payload;

import com.training.ecommerce.rest.api.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class RegisterResponse
{
    private String message;
    private String token;
    private Boolean error;
    private User user;
}
