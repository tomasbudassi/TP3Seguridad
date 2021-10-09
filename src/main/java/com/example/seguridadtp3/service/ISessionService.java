package com.example.seguridadtp3.service;

import com.example.seguridadtp3.dto.UserAuthDTO;
import com.example.seguridadtp3.dto.UserResponseDTO;

import java.security.NoSuchAlgorithmException;

public interface ISessionService {
    UserResponseDTO login(UserAuthDTO userAuth) throws NoSuchAlgorithmException;
}
