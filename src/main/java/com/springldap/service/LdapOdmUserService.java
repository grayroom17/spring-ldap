package com.springldap.service;

import com.springldap.domain.entity.LdapUser;
import com.springldap.mapper.LdapUserMapper;
import com.springldap.repository.LdapOdmUserRepository;
import com.springldap.rest.dto.UserCreateDto;
import com.springldap.rest.dto.UserGetDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class LdapOdmUserService {

    LdapOdmUserRepository ldapOdmUserRepository;
    LdapUserMapper ldapUserMapper;

    public UserGetDto findOneByGuid(String guid) {
        return ldapUserMapper.toGetDto(ldapOdmUserRepository.findOneByGuid(guid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    public List<UserGetDto> findAll() {
        return ldapUserMapper.toGetDtoList(ldapOdmUserRepository.findAll());
    }

    public List<UserGetDto> findAllByFirsName(String firstName) {
        return ldapUserMapper.toGetDtoList(ldapOdmUserRepository.findAllByFirsName(firstName));
    }

    public void create(UserCreateDto dto) {
        LdapUser ldapUser = ldapUserMapper.fromCreateDto(dto);

        ldapOdmUserRepository.create(ldapUser);
    }

    public void update(UserCreateDto dto) {
        LdapUser ldapUser = ldapUserMapper.fromCreateDto(dto);

        ldapOdmUserRepository.update(ldapUser);
    }

    public void delete(UserCreateDto dto) {
        LdapUser ldapUser = ldapUserMapper.fromCreateDto(dto);

        ldapOdmUserRepository.delete(ldapUser);
    }
}
