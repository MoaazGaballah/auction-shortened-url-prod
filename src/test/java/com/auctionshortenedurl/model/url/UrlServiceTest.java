package com.auctionshortenedurl.model.url;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.auctionshortenedurl.model.user.User;
import com.auctionshortenedurl.repository.url.UrlRepository;
import com.auctionshortenedurl.repository.user.UserRepository;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UrlService.class})
@ExtendWith(SpringExtension.class)
class UrlServiceTest {
    @MockBean
    private UrlRepository urlRepository;

    @Autowired
    private UrlService urlService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testKaydet() {
        User user = new User();
        user.setSoyad("https://example.org/example");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUserId(123L);
        user.setAd("https://example.org/example");
        when(this.userRepository.getById((Long) any())).thenReturn(user);

        User user1 = new User();
        user1.setSoyad("https://example.org/example");
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setUserId(123L);
        user1.setAd("https://example.org/example");

        Url url = new Url();
        url.setUser(user1);
        url.setUrlId(123L);
        url.setLongUrl("https://example.org/example");
        url.setShortUrl("https://example.org/example");
        when(this.urlRepository.save((Url) any())).thenReturn(url);

        User user2 = new User();
        user2.setSoyad("https://example.org/example");
        user2.setEmail("jane.doe@example.org");
        user2.setPassword("iloveyou");
        user2.setUserId(123L);
        user2.setAd("https://example.org/example");

        Url url1 = new Url();
        url1.setUser(user2);
        url1.setUrlId(123L);
        url1.setLongUrl("https://example.org/example");
        url1.setShortUrl("https://example.org/example");
        this.urlService.kaydet(url1);
        verify(this.userRepository).getById((Long) any());
        verify(this.urlRepository).save((Url) any());
        assertEquals(user2, url1.getUser());
    }

    @Test
    void testKaydet2() {
        User user = new User();
        user.setSoyad("https://example.org/example");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUserId(123L);
        user.setAd("https://example.org/example");
        when(this.userRepository.getById((Long) any())).thenReturn(user);

        User user1 = new User();
        user1.setSoyad("https://example.org/example");
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setUserId(123L);
        user1.setAd("https://example.org/example");

        Url url = new Url();
        url.setUser(user1);
        url.setUrlId(123L);
        url.setLongUrl("https://example.org/example");
        url.setShortUrl("https://example.org/example");
        when(this.urlRepository.save((Url) any())).thenReturn(url);

        User user2 = new User();
        user2.setSoyad("https://example.org/example");
        user2.setEmail("jane.doe@example.org");
        user2.setPassword("iloveyou");
        user2.setUserId(123L);
        user2.setAd("https://example.org/example");

        Url url1 = new Url();
        url1.setUser(user2);
        url1.setUrlId(123L);
        url1.setLongUrl("https://example.org/example");
        url1.setShortUrl("https://example.org/example");
        this.urlService.kaydet(url1);
        verify(this.userRepository).getById((Long) any());
        verify(this.urlRepository).save((Url) any());
        assertEquals(user1, url1.getUser());
    }

    @Test
    void testShortenUrl() {
        when(this.urlRepository.findAll()).thenReturn(new ArrayList<Url>());

        User user = new User();
        user.setSoyad("https://example.org/example");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUserId(123L);
        user.setAd("https://example.org/example");

        Url url = new Url();
        url.setUser(user);
        url.setUrlId(123L);
        url.setLongUrl("https://example.org/example");
        url.setShortUrl("https://example.org/example");
        assertSame(url, this.urlService.shortenUrl(url));
        verify(this.urlRepository).findAll();
    }

    @Test
    void testShortenUrl2() {
        User user = new User();
        user.setSoyad("https://example.org/example");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUserId(123L);
        user.setAd("https://example.org/example");

        Url url = new Url();
        url.setUser(user);
        url.setUrlId(123L);
        url.setLongUrl("https://example.org/example");
        url.setShortUrl("https://example.org/example");

        ArrayList<Url> urlList = new ArrayList<Url>();
        urlList.add(url);
        when(this.urlRepository.findAll()).thenReturn(urlList);

        User user1 = new User();
        user1.setSoyad("https://example.org/example");
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setUserId(123L);
        user1.setAd("https://example.org/example");

        Url url1 = new Url();
        url1.setUser(user1);
        url1.setUrlId(123L);
        url1.setLongUrl("https://example.org/example");
        url1.setShortUrl("https://example.org/example");
        assertSame(url1, this.urlService.shortenUrl(url1));
        verify(this.urlRepository).findAll();
    }

    @Test
    void testShortenUrl3() {
        User user = new User();
        user.setSoyad("https://example.org/example");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUserId(123L);
        user.setAd("https://example.org/example");

        Url url = new Url();
        url.setUser(user);
        url.setUrlId(123L);
        url.setLongUrl("https://example.org/example");
        url.setShortUrl("https://example.org/example");

        User user1 = new User();
        user1.setSoyad("https://example.org/example");
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setUserId(123L);
        user1.setAd("https://example.org/example");

        Url url1 = new Url();
        url1.setUser(user1);
        url1.setUrlId(123L);
        url1.setLongUrl("https://example.org/example");
        url1.setShortUrl("https://example.org/example");

        ArrayList<Url> urlList = new ArrayList<Url>();
        urlList.add(url1);
        urlList.add(url);
        when(this.urlRepository.findAll()).thenReturn(urlList);

        User user2 = new User();
        user2.setSoyad("https://example.org/example");
        user2.setEmail("jane.doe@example.org");
        user2.setPassword("iloveyou");
        user2.setUserId(123L);
        user2.setAd("https://example.org/example");

        Url url2 = new Url();
        url2.setUser(user2);
        url2.setUrlId(123L);
        url2.setLongUrl("https://example.org/example");
        url2.setShortUrl("https://example.org/example");
        assertSame(url2, this.urlService.shortenUrl(url2));
        verify(this.urlRepository).findAll();
    }

    @Test
    void testShortenUrl4() {
        when(this.urlRepository.findAll()).thenReturn(new ArrayList<Url>());

        User user = new User();
        user.setSoyad("https://example.org/example");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUserId(123L);
        user.setAd("https://example.org/example");

        Url url = new Url();
        url.setUser(user);
        url.setUrlId(123L);
        url.setLongUrl("https://example.org/example");
        url.setShortUrl("https://example.org/example");
        assertSame(url, this.urlService.shortenUrl(url));
        verify(this.urlRepository).findAll();
    }

    @Test
    void testShortenUrl5() {
        User user = new User();
        user.setSoyad("https://example.org/example");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUserId(123L);
        user.setAd("https://example.org/example");

        Url url = new Url();
        url.setUser(user);
        url.setUrlId(123L);
        url.setLongUrl("https://example.org/example");
        url.setShortUrl("https://example.org/example");

        ArrayList<Url> urlList = new ArrayList<Url>();
        urlList.add(url);
        when(this.urlRepository.findAll()).thenReturn(urlList);

        User user1 = new User();
        user1.setSoyad("https://example.org/example");
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setUserId(123L);
        user1.setAd("https://example.org/example");

        Url url1 = new Url();
        url1.setUser(user1);
        url1.setUrlId(123L);
        url1.setLongUrl("https://example.org/example");
        url1.setShortUrl("https://example.org/example");
        assertSame(url1, this.urlService.shortenUrl(url1));
        verify(this.urlRepository).findAll();
    }

    @Test
    void testShortenUrl6() {
        User user = new User();
        user.setSoyad("https://example.org/example");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUserId(123L);
        user.setAd("https://example.org/example");

        Url url = new Url();
        url.setUser(user);
        url.setUrlId(123L);
        url.setLongUrl("https://example.org/example");
        url.setShortUrl("https://example.org/example");

        User user1 = new User();
        user1.setSoyad("https://example.org/example");
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setUserId(123L);
        user1.setAd("https://example.org/example");

        Url url1 = new Url();
        url1.setUser(user1);
        url1.setUrlId(123L);
        url1.setLongUrl("https://example.org/example");
        url1.setShortUrl("https://example.org/example");

        ArrayList<Url> urlList = new ArrayList<Url>();
        urlList.add(url1);
        urlList.add(url);
        when(this.urlRepository.findAll()).thenReturn(urlList);

        User user2 = new User();
        user2.setSoyad("https://example.org/example");
        user2.setEmail("jane.doe@example.org");
        user2.setPassword("iloveyou");
        user2.setUserId(123L);
        user2.setAd("https://example.org/example");

        Url url2 = new Url();
        url2.setUser(user2);
        url2.setUrlId(123L);
        url2.setLongUrl("https://example.org/example");
        url2.setShortUrl("https://example.org/example");
        assertSame(url2, this.urlService.shortenUrl(url2));
        verify(this.urlRepository).findAll();
    }

    @Test
    void testOpenBrowser() {
        String shortUrl = "ktlLid";
        User user = new User();
        user.setSoyad("https://example.org/example");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUserId(123L);
        user.setAd("https://example.org/example");

        Url url = new Url();
        url.setUser(user);
        url.setUrlId(123L);
        url.setLongUrl("https://example.org/example");
        url.setShortUrl(shortUrl);
        when(this.urlRepository.findByShortUrl(shortUrl)).thenReturn(url);
        assertEquals("Göndermiş olduğunuz url yi tekrar kontrol ediniz",
                this.urlService.openBrowser(url.getLongUrl()));
        verify(this.urlRepository).findByShortUrl((String) any());
    }
}

