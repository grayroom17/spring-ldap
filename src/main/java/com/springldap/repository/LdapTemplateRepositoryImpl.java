package com.springldap.repository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;

import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class LdapTemplateRepositoryImpl implements LdapTemplateRepository {

    LdapTemplate ldapTemplate;

    @Override
    public List<String> getAllUserNames() {
        return ldapTemplate.search(query().
                        where("objectClass").is("user"),
                (AttributesMapper<String>) attributes -> attributes.get("givenName").get().toString());
    }

}
