package com.auctionshortenedurl.model.user;

import com.auctionshortenedurl.exception.BadRequestException;
import com.auctionshortenedurl.repository.user.UserRepository;
import com.sun.org.apache.xerces.internal.impl.xs.identity.Selector;
import com.sun.org.apache.xpath.internal.operations.String;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.hamcrest.MockitoHamcrest;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepositoryUnderTest;
    private UserService userServiceUnderTest;

    @BeforeEach
    void setUp() {
        userServiceUnderTest = new UserService(userRepositoryUnderTest);
    }

    @Test
    void kaydet() {

        // given
        User expectedUser = new User();
        expectedUser.setAd("Moaaz");
        expectedUser.setEmail("test@test.com");
        expectedUser.setPassword("1");
        expectedUser.setSoyad("Gaballah");

        // when
        userServiceUnderTest.kaydet(expectedUser);

        // then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        // capturing the user that saved at the save line
        verify(userRepositoryUnderTest).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(expectedUser);
    }

    @Test
    void willReturnMessageWhenEmailTaken(){
        // given
        User expectedUser = new User();
        expectedUser.setAd("Moaaz");
        expectedUser.setEmail("test@test.com");
        expectedUser.setPassword("1");
        expectedUser.setSoyad("Gaballah");

//        given(userRepositoryUnderTest.getAllByEmail(expectedUser.getEmail()).size())
//                .willReturn(0);
        when(userRepositoryUnderTest.getAllByEmail(expectedUser.getEmail()))
                .thenReturn(null);
        // when
        // then
        assertThatThrownBy(() ->userServiceUnderTest.kaydet(expectedUser))
                .isInstanceOf(java.lang.NullPointerException.class);

        verify(userRepositoryUnderTest, never()).save(any());
    }

    @Test
    void canLogin() {

    }
}