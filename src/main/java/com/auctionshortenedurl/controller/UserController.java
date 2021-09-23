package com.auctionshortenedurl.controller;

import com.auctionshortenedurl.model.user.User;
import com.auctionshortenedurl.model.user.UserInfo;
import com.auctionshortenedurl.model.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@Valid @RequestBody User usr) {
        try{

            userService.kaydet(usr);
            return new ResponseEntity<>("Üye kaydınız başarılıyla alınmıştır.", HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>("Üye kaydınız başarısızdır, Lütfen Parametrelerinizi tekrar Doğrulayın!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("/login")
    public ResponseEntity<Object> login (@RequestBody UserInfo userInfo){
        try {
            User user = userService.getUser(userInfo);

            if (user.getPassword().equals(userInfo.getPassword()))
                return new ResponseEntity<>("Giriş Başarıyla yapıldı!", HttpStatus.OK);

            return new ResponseEntity<>("Hatalı email yada şifre, Lütfen Tekrar Kontrol ediniz.", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Lütfen Parametrelerinizi Doğrulayın!", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}

