package com.domain.services;

import com.domain.models.entities.AppUser;
import com.domain.models.repos.AppUserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AppUserService implements UserDetailsService {

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepo.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(
                        String.format("User dengan email '%s' tidak ditemukan", email)
                )
        );
    }

    public AppUser registerAppUser(AppUser appUser) {
        boolean userExists = appUserRepo.findByEmail(appUser.getEmail()).isPresent();
        if (userExists) {
            throw new RuntimeException(
                    String.format("User '%s' already exists", appUser.getEmail())
            );
        }
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        return appUserRepo.save(appUser);
    }


}
