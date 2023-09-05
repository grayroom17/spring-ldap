package com.springldap.service;

import com.springldap.domain.entity.LdapUser;
import com.springldap.mapper.LdapUserMapper;
import com.springldap.repository.LdapTemplateUserRepository;
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
public class LdapTemplateUserService {

    LdapTemplateUserRepository ldapTemplateUserRepository;
    LdapUserMapper ldapUserMapper;


    public List<String> getAllUserNames() {
        return ldapTemplateUserRepository.getAllUserNames();
    }

    public List<UserGetDto> getAllLdapUsers() {
        return ldapUserMapper.toGetDtoList(ldapTemplateUserRepository.getAllUsers());
    }

    public List<UserGetDto> findAllByFullName(String fullName) {
        return ldapUserMapper.toGetDtoList(ldapTemplateUserRepository.findAllByFullName(fullName));
    }

    public List<UserGetDto> getAllLdapUsersBySureName(String sureName) {
        return ldapUserMapper.toGetDtoList(ldapTemplateUserRepository.getAllUsersBySureName(sureName));
    }

    public UserGetDto lookupByDn(String dn) {
        LdapUser ldapUser = ldapTemplateUserRepository.lookupByDn(dn)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ldapUserMapper.toGetDto(ldapUser);
    }

    public UserGetDto lookupByDnWithContextMapper(String dn) {
        LdapUser ldapUser = ldapTemplateUserRepository.lookupByDnWithContextMapper(dn)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ldapUserMapper.toGetDto(ldapUser);
    }

    public void create(UserCreateDto dto) {
        ldapTemplateUserRepository.create(dto);
    }

    public void createWithDirContextAdapter(String dn, UserCreateDto dto) {
        ldapTemplateUserRepository.createWithDirContextAdapter(dn, dto);
    }

    public void delete(String dn) {
        ldapTemplateUserRepository.delete(dn);
    }

    public void rebind(String dn, UserCreateDto dto) {
        ldapTemplateUserRepository.rebind(dn, dto);
    }

    public void updateAttribute(String dn, AttributeDto attribute) {
        ldapTemplateUserRepository.updateAttribute(dn, attribute);
    }

    public void updateWithDirContextOperations(String dn, UserCreateDto dto) {
        ldapTemplateUserRepository.updateWithDirContextOperations(dn, dto);
    }

    //ODM - Object-Directory Mapping

    public UserGetDto findOneByGuid(String guid) {
        return ldapUserMapper.toGetDto(ldapTemplateUserRepository.findOneByGuid(guid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    public List<UserGetDto> findAll() {
        return ldapUserMapper.toGetDtoList(ldapTemplateUserRepository.findAll());
    }

    public List<UserGetDto> findAllByFirsName(String firstName) {
        return ldapUserMapper.toGetDtoList(ldapTemplateUserRepository.findAllByFirsName(firstName));
    }

    public List<UserGetDto> findAllInBase(String base) {
        return ldapUserMapper.toGetDtoList(ldapTemplateUserRepository.findAllInBase(base));
    }

}
