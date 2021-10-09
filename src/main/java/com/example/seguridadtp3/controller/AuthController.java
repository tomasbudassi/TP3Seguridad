package com.example.seguridadtp3.controller;

import com.example.seguridadtp3.dto.UserAuthDTO;
import com.example.seguridadtp3.dto.UserResponseDTO;
import com.example.seguridadtp3.service.ISessionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class AuthController {

    private ISessionService sessionService;

    @PostMapping()
    public UserResponseDTO login(@RequestBody UserAuthDTO user) throws Exception {
        return sessionService.login(user);
    }
}