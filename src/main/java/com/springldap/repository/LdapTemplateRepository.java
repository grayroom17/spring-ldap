package com.springldap.repository;

import com.springldap.domain.entity.LdapUser;

import java.util.List;

public interface LdapTemplateRepository {

    List<String> getAllUserNames();

    List<LdapUser> getAllUsers();

}
