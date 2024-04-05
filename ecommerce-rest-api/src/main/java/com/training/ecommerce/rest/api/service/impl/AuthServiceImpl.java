package com.training.ecommerce.rest.api.service.impl;

import com.training.ecommerce.rest.api.entity.Role;
import com.training.ecommerce.rest.api.payload.LoginDto;
import com.training.ecommerce.rest.api.payload.LoginResponse;
import com.training.ecommerce.rest.api.payload.RegisterDto;
import com.training.ecommerce.rest.api.payload.RegisterResponse;
import com.training.ecommerce.rest.api.repository.RoleRepository;
import com.training.ecommerce.rest.api.repository.UserRepository;
import com.training.ecommerce.rest.api.security.JwtTokenProvider;
import com.training.ecommerce.rest.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.training.ecommerce.rest.api.entity.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class AuthServiceImpl implements AuthService
{
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public LoginResponse login(LoginDto loginDto)
    {
        LoginResponse response = new LoginResponse();
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);
        response.setToken(token);
        response.setMessage("User login success");
        response.setError(false);

        return response;
    }

    @Override
    public RegisterResponse Register(RegisterDto registerDto)
    {
        RegisterResponse registerResponse = new RegisterResponse();
        if(userRepository.existsByUsername(registerDto.getUsername()))
        {
            registerResponse.setError(true);
            registerResponse.setMessage("User already exists!.");
            throw new RuntimeException("User already exists!.");
        }
        if(userRepository.existsByEmail(registerDto.getEmail()))
        {
            registerResponse.setError(true);
            registerResponse.setMessage("User already exists!.");
            throw new RuntimeException("email already exists!.");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);
        registerResponse.setMessage("user Registered Successfully");
        registerResponse.setUser(user);
        registerResponse.setError(false);
        return registerResponse;
    }
}
