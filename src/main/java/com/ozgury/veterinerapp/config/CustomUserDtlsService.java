package com.ozgury.veterinerapp.config;

import com.ozgury.veterinerapp.dto.user.User;
import com.ozgury.veterinerapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

public class CustomUserDtlsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByEmail(email);

            if (user == null){
                throw new UsernameNotFoundException("Kullanıcı Bulunamadı");
            }else {
                System.out.println(user);
                return new CustomUserDtls(user);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
