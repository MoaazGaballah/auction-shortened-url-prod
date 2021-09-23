package com.auctionshortenedurl.model.user;

import com.auctionshortenedurl.exception.BadRequestException;
import com.auctionshortenedurl.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public String kaydet(User user){
        if (!user.getAd().equals(null) && !user.getEmail().equals(null) && !user.getPassword().equals(null)) {
            List<User> users = userRepository.getAllByEmail(user.getEmail());
            if (users.size() == 0){
                this.userRepository.save(user);
                return "Üye kaydınız başarılıyla alınmıştır.";
            }
        }
        return "Bu eposta" + user.getEmail() + " daha önce kullanılmıştır";
    }

    public String login(UserInfo userInfo){
        if (!userInfo.getEmail().equals(null) && !userInfo.getPassword().equals(null)){
            User user = userRepository.findByEmail(userInfo.getEmail());
            if (user != null  && user.getPassword().equals(userInfo.getPassword()))
                return "Giriş Başarıyla yapıldı!";
            return "Hatalı email yada şifre, Lütfen Tekrar Kontrol ediniz.";
        }
        return  "";
    }
}
