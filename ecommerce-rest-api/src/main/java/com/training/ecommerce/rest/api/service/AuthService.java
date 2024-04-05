package com.training.ecommerce.rest.api.service;

import com.training.ecommerce.rest.api.payload.LoginDto;
import com.training.ecommerce.rest.api.payload.LoginResponse;
import com.training.ecommerce.rest.api.payload.RegisterDto;
import com.training.ecommerce.rest.api.payload.RegisterResponse;

public interface AuthService
{
    LoginResponse login(LoginDto loginDto);
    RegisterResponse Register(RegisterDto registerDto);
}
