package com.springldap.service;

import com.springldap.domain.entity.LdapUser;
import com.springldap.mapper.LdapUserMapper;
import com.springldap.repository.LdapCustomRepository;
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
    LdapUserMapper ldapUserMapper;

    public List<LdapUser> findAll() {
        return ldapRepository.findAll();
    }

    public List<String> getAllUserNames() {
        return ldapRepository.getAllUserNames();
    }

    public List<UserGetDto> getAllLdapUsers() {
        return ldapUserMapper.toGetDtoList(ldapRepository.getAllUsers());
    }

}