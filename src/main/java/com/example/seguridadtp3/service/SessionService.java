package com.example.seguridadtp3.service;

import com.example.seguridadtp3.dto.UserAuthDTO;
import com.example.seguridadtp3.dto.UserResponseDTO;
import com.example.seguridadtp3.exceptions.ApiException;
import com.example.seguridadtp3.model.User;
import com.example.seguridadtp3.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SessionService implements ISessionService {
    private final UserRepository userRepository;

    /**
     * Perform the validation of the username and password entered.
     * If it's correct, returns the account with the necessary token to carry out the other queries.
     */
    @Override
    public UserResponseDTO login(UserAuthDTO userAuth) throws NoSuchAlgorithmException {

        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(userAuth.getPassword().getBytes(),0,userAuth.getPassword().length());

        User user = userRepository.findByUsernameAndPassword(userAuth.getUsername(),new BigInteger(1,m.digest()).toString(16))
                .orElseThrow( () -> new ApiException("404", "Username and/or Password is incorrect", 404));

        String token = getJWTToken(user);
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUsername(userAuth.getUsername());
        userResponseDTO.setToken(token);
        return userResponseDTO;
    }

    /**
     * Generate a token for a specific user
     * @param user
     * @return
     */
    private String getJWTToken(User user) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(user.getUserRole().getName());

        String token = Jwts
                .builder()
                .setId("TP3SEGURIDADJWT")
                .setSubject(user.getUsername())
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 7200000)) // 2 Horas
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();
        return "Bearer " + token;
    }
}