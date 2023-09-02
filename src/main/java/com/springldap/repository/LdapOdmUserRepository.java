package com.springldap.repository;

import com.springldap.domain.entity.LdapUser;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Repository
public class LdapOdmUserRepository {

    LdapTemplate ldapTemplate;

    public Optional<LdapUser> findOneByGuid(String guid) {
        return Optional.of(ldapTemplate.findOne(
                query()
                        .where("ObjectGUID")
                        .is(guid),
                LdapUser.class));
    }

    public List<LdapUser> findAll() {
        return ldapTemplate.findAll(LdapUser.class);
    }

    public List<LdapUser> findAllByFirsName(String firstName) {
        return ldapTemplate.find(
                query()
                        .where("GivenName")
                        .is(firstName),
                LdapUser.class
        );
    }

    public void create(LdapUser ldapUser) {
        ldapTemplate.create(ldapUser);
    }

    public void update(LdapUser ldapUser) {
        ldapTemplate.update(ldapUser);
    }

    public void delete(LdapUser ldapUser) {
        ldapTemplate.delete(ldapUser);
    }

}
