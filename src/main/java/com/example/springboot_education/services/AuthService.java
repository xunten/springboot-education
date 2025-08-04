package com.example.springboot_education.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.springboot_education.dtos.AuthDTOs.LoginRequestDto;
import com.example.springboot_education.dtos.AuthDTOs.LoginResponseDto;
import com.example.springboot_education.entities.Users;
import com.example.springboot_education.exceptions.HttpException;
import com.example.springboot_education.repositories.UsersJpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final JwtService jwtService;
    private final UsersJpaRepository userJpaRepository;

    public LoginResponseDto login(LoginRequestDto request) throws Exception {
        // ✅ Dùng findByUsernameWithRoles để load đầy đủ Roles
        Users user = this.userJpaRepository.findByUsernameWithRoles(request.getUsername())
                .orElseThrow(() -> new HttpException("Invalid username or password", HttpStatus.UNAUTHORIZED));

        // Verify password (cách đơn giản, production thì nên mã hóa)
        if (!request.getPassword().equals(user.getPassword())) {
            throw new HttpException("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }

        // Generate a new access token (include roles)
        String accessToken = jwtService.generateAccessToken(user);

        return LoginResponseDto.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .accessToken(accessToken)
                .build();
    }
}
