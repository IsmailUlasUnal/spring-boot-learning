package com.ismailulasunal.twitterclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ismailulasunal.twitterclone.models.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByAuthority(String authority);
}
