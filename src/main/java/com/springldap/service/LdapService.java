package com.springldap.service;

import com.springldap.domain.entity.LdapUser;
import com.springldap.mapper.LdapUserMapper;
import com.springldap.repository.LdapCustomRepository;
import com.springldap.rest.dto.AttributeDto;
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

    public List<UserGetDto> getAllLdapUsersBySureName(String sureName) {
        return ldapUserMapper.toGetDtoList(ldapRepository.getAllUsersBySureName(sureName));
    }

    public UserGetDto lookupByDn(String dn) {
        LdapUser ldapUser = ldapRepository.lookupByDn(dn)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ldapUserMapper.toGetDto(ldapUser);
    }

    public UserGetDto lookupByDnWithContextMapper(String dn) {
        LdapUser ldapUser = ldapRepository.lookupByDnWithContextMapper(dn)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ldapUserMapper.toGetDto(ldapUser);
    }

    public void create(UserCreateDto dto) {
        ldapRepository.create(dto);
    }


    public void delete(String dn) {
        ldapRepository.delete(dn);
    }

    public void rebind(String dn, UserCreateDto dto) {
        ldapRepository.rebind(dn, dto);
    }

    public void updateAttribute(String dn, AttributeDto attribute) {
        ldapRepository.updateAttribute(dn, attribute);
    }

}
