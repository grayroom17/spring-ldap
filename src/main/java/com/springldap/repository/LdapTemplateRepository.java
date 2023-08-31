package com.springldap.repository;

import com.springldap.domain.entity.LdapUser;
import com.springldap.rest.dto.AttributeDto;
import com.springldap.rest.dto.UserCreateDto;

import java.util.List;
import java.util.Optional;

public interface LdapTemplateRepository {

    List<String> getAllUserNames();

    List<LdapUser> getAllUsers();

    List<LdapUser> getAllUsersBySureName(String sureName);

    Optional<LdapUser> lookupByDn(String dn);

    Optional<LdapUser> lookupByDnWithContextMapper(String dn);

    void create(UserCreateDto dto);

    void createWithDirContextAdapter(String dn, UserCreateDto dto);

    void delete(String dn);

    void rebind(String dn, UserCreateDto dto);

    void updateAttribute(String dn, AttributeDto attribute);

    void updateWithDirContextOperations(String dn, UserCreateDto dto);

}
