package com.auctionshortenedurl.model.user;


import com.auctionshortenedurl.exception.ResourceNotFoundException;
import com.auctionshortenedurl.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User kaydet(User user){
        return this.userRepository.save(user);
    }

    public User getUser(UserInfo userInfo) {
        return this.userRepository.findByEmail(userInfo.getEmail());
    }
}
