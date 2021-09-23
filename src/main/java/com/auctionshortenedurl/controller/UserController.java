package com.auctionshortenedurl.controller;

import com.auctionshortenedurl.model.user.User;
import com.auctionshortenedurl.model.user.UserInfo;
import com.auctionshortenedurl.model.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody User usr) {

        String durum = "Üye kaydınız başarısızdır, Lütfen Parametrelerinizi tekrar Doğrulayın!";
        try {
            durum = userService.kaydet(usr);
            return new ResponseEntity<>(durum, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(durum, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserInfo userInfo) {
        String durum = "Giriş işleminiz başarısızdır, Lütfen Parametrelerinizi tekrar Doğrulayın!";
        try{
            durum = userService.login(userInfo);
            return new ResponseEntity<>(durum, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(durum, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

