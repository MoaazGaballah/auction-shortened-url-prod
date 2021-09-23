package com.auctionshortenedurl.controller;

import com.auctionshortenedurl.model.url.Url;
import com.auctionshortenedurl.model.url.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/url")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping("/shortenurl")
    public ResponseEntity<Object> shortenUrl(@RequestBody Url url) {
        try {

            url = urlService.shortenUrl(url);

            urlService.kaydet(url);

            return new ResponseEntity<>("URLınız başarılıyla kısaltılmıştır. Kısa URLiniz: " + url.getShortUrl(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("URL kısaltma işleminiz başarısızdır, Lütfen Parametrelerinizi tekrar Doğrulayın!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/gotolink/{strUrl}")
    public ResponseEntity<Object> gotoLink(@PathVariable String strUrl) {
        String status = "URL yönlendirme işleminiz başarısızdır, Lütfen Parametrelerinizi tekrar Doğrulayın!";
        try {
            status = urlService.openBrowser(strUrl);
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
