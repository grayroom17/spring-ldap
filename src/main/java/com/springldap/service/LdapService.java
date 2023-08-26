package com.springldap.service;

import com.springldap.mapper.UserMapper;
import com.springldap.repository.LdapCustomRepository;
import com.springldap.repository.UserRepository;
import com.springldap.rest.dto.UserGetDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class LdapService {

    LdapCustomRepository ldapRepository;
    UserRepository userRepository;
    UserMapper userMapper;

    public List<UserGetDto> findAll() {
        return userMapper.toGetDtoList(userRepository.findAll());
    }

}
