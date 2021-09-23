package com.auctionshortenedurl.repository.user;

import com.auctionshortenedurl.model.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepositoryUnderTest;

    @AfterEach
    void tearDown() {
        userRepositoryUnderTest.deleteAll();
    }

    @Test
    void itShouldReturnUserFoundByEmail() {

        // given
        String email = "test1@test.com";

        User expectedUser = new User();
        expectedUser.setAd("Moaaz");
        expectedUser.setEmail(email);
        expectedUser.setPassword("1");
        expectedUser.setSoyad("Gaballah");
        userRepositoryUnderTest.save(expectedUser);

        // when
        User user = userRepositoryUnderTest.findByEmail(email);

        // then
        assertThat(expectedUser).isEqualTo(user);
    }

    @Test
    void itShouldReturnAllUsersFoundByEmail(){


        // given
        String email = "test1@test.com";

        User expectedUser1 = new User();
        expectedUser1.setAd("Moaaz");
        expectedUser1.setEmail(email);
        expectedUser1.setPassword("1");
        expectedUser1.setSoyad("Gaballah");

        User expectedUser2 = new User();
        expectedUser2.setAd("Moaaz");
        expectedUser2.setEmail(email);
        expectedUser2.setPassword("1");
        expectedUser2.setSoyad("Gaballah");

        User expectedUser3 = new User();
        expectedUser3.setAd("Moaaz");
        expectedUser3.setEmail(email);
        expectedUser3.setPassword("1");
        expectedUser3.setSoyad("Gaballah");

        userRepositoryUnderTest.save(expectedUser1);
        userRepositoryUnderTest.save(expectedUser2);
        userRepositoryUnderTest.save(expectedUser3);

        List<User> expectedUserList = new ArrayList<>(Arrays.asList(expectedUser1, expectedUser2, expectedUser3));

        // when
        List<User> users = userRepositoryUnderTest.getAllByEmail(email);

        // then
        assertThat(users).containsExactlyInAnyOrderElementsOf(expectedUserList);
    }
}