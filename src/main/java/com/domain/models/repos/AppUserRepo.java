package com.domain.models.repos;

import com.domain.models.entities.AppUser;
import com.domain.models.entities.AppUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

}
