package com.springldap.repository;

import com.springldap.domain.entity.LdapUser;
import org.springframework.data.ldap.repository.LdapRepository;


public interface LdapCustomRepository extends LdapRepository<LdapUser> {

}
