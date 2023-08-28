package com.springldap.repository;

import com.springldap.domain.entity.LdapUser;
import com.springldap.rest.dto.UserCreateDto;

import java.util.List;
import java.util.Optional;

public interface LdapTemplateRepository {

    List<String> getAllUserNames();

    List<LdapUser> getAllUsers();

    List<LdapUser> getAllUsersBySureName(String sureName);

    Optional<LdapUser> lookupByDn(String dn);

    void create(UserCreateDto dto);

    void delete(String dn);

}
