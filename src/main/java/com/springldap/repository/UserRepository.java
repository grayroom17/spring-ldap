package com.springldap.repository;

import com.springldap.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByGuidIsIn(List<UUID> guids);

}
