package com.auctionshortenedurl.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.auctionshortenedurl.model.url.Url;
import com.auctionshortenedurl.model.url.UrlService;
import com.auctionshortenedurl.model.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UrlController.class})
@ExtendWith(SpringExtension.class)
class UrlControllerTest {
    @Autowired
    private UrlController urlController;

    @MockBean
    private UrlService urlService;

    @Test
    void testGotoLink() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/url/gotolink/{strUrl}",
                "https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.urlController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testShortenUrl() throws Exception {
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
        doNothing().when(this.urlService).kaydet((Url) any());
        when(this.urlService.shortenUrl((Url) any())).thenReturn(url);

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
        String content = (new ObjectMapper()).writeValueAsString(url1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/url/shortenurl")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.urlController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("URL?n?z ba?ar?l?yla k?salt?lm??t?r. K?sa URLiniz: https://example.org/example"));
    }

    @Test
    void testGotoLink2() throws Exception {
        when(this.urlService.openBrowser((String) any())).thenReturn("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/url/gotolink/{strUrl}", "Uri Vars",
                "Uri Vars");
        MockMvcBuilders.standaloneSetup(this.urlController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("https://example.org/example"));
    }
}

