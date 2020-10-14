package com.example.backtoken;

import org.springframework.boot.SpringApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncoderAleatorio {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("joao"));
    }
}
