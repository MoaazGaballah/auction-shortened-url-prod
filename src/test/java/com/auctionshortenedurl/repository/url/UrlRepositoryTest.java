package com.auctionshortenedurl.repository.url;

import com.auctionshortenedurl.model.url.Url;
import com.auctionshortenedurl.model.user.User;
import com.auctionshortenedurl.repository.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UrlRepositoryTest {

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        urlRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void itShouldFindUrlObjectByShortenUrl(){

        // given
        User userUnderTest = new User();
        userUnderTest.setAd("Moaaz");
        userUnderTest.setEmail("test1@test.com");
        userUnderTest.setPassword("1");
        userUnderTest.setSoyad("Gaballah");
        userRepository.save(userUnderTest);

        String shortenUrl = "GvuzF84";

        Url expectedUrl = new Url();
        expectedUrl.setShortUrl(shortenUrl);
        expectedUrl.setUser(userUnderTest);
        expectedUrl.setLongUrl("https://www.google.com/");
        urlRepository.save(expectedUrl);

        // when
        Url url = urlRepository.findByShortUrl(shortenUrl);

        // then
        assertThat(expectedUrl).isEqualTo(url);
    }

}