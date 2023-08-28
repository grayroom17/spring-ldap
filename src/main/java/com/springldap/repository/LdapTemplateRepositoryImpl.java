package com.springldap.repository;

import com.springldap.domain.entity.LdapUser;
import com.springldap.mapper.LdapUserMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.ldap.InvalidNameException;
import org.springframework.ldap.NameNotFoundException;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.ldap.query.LdapQueryBuilder.query;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class LdapTemplateRepositoryImpl implements LdapTemplateRepository {

    public static final String OBJECT_CLASS = "objectClass";

    LdapTemplate ldapTemplate;
    LdapUserMapper ldapUserMapper;

    @Override
    public List<String> getAllUserNames() {
        return ldapTemplate.search(query().
                        where(OBJECT_CLASS).is("user"),
                (AttributesMapper<String>) attributes -> attributes.get("givenName").get().toString());
    }

    @Override
    public List<LdapUser> getAllUsers() {
        return ldapTemplate.search(query()
                        .where(OBJECT_CLASS).is("user"),
                ldapUserMapper);
    }

    @Override
    public List<LdapUser> getAllUsersBySureName(String sureName) {
        return ldapTemplate.search(query()
                        .where(OBJECT_CLASS).is("user")
                        .and("sn").is(sureName),
                ldapUserMapper);
    }

    @Override
    public Optional<LdapUser> lookupByDn(String dn) {
        try {
            return Optional.of(ldapTemplate.lookup(dn, ldapUserMapper));
        } catch (NameNotFoundException e) {
            return Optional.empty();
        } catch (InvalidNameException e) {
            throw new ResponseStatusException(BAD_REQUEST, dn, e);
        }
    }

}
