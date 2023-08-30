package com.springldap.mapper;

import com.springldap.domain.entity.LdapUser;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class LdapUserContextMapper implements ContextMapper<LdapUser> {

    @Override
    public LdapUser mapFromContext(Object ctx) {
        DirContextAdapter context = (DirContextAdapter) ctx;
        return LdapUser.builder()
                .distinguishedName(context.getDn())
                .commonName(context.getStringAttribute("cn"))
                .canonicalName(context.getStringAttribute("canonicalName"))
                .guid(context.getStringAttribute("objectGuid"))
                .userPrincipalName(context.getStringAttribute("userPrincipalName"))
                .displayName(context.getStringAttribute("displayName"))
                .fullName(context.getStringAttribute("Name"))
                .firstname(context.getStringAttribute("GivenName"))
                .lastname(context.getStringAttribute("sn"))
                .otherName(context.getStringAttribute("OtherName"))
                .initials(context.getStringAttribute("initials"))
                .telephoneNumber(context.getStringAttribute("telephoneNumber"))
                .homePhone(Arrays.asList(context.getStringAttributes("homePhone")))
                .mobilePhone(context.getStringAttribute("mobilePhone"))
                .country(context.getStringAttribute("country"))
                .state(context.getStringAttribute("state"))
                .city(context.getStringAttribute("city"))
                .street(context.getStringAttribute("street"))
                .postalCode(context.getStringAttribute("postalCode"))
                .company(context.getStringAttribute("company"))
                .organization(context.getStringAttribute("organization"))
                .division(context.getStringAttribute("division"))
                .department(context.getStringAttribute("department"))
                .office(context.getStringAttribute("office"))
                .manager(context.getStringAttribute("manager"))
                .employeeId(context.getStringAttribute("employeeId"))
                .employeeNumber(context.getStringAttribute("employeeNumber"))
                .mail(context.getStringAttribute("mail"))
                .mailNickname(context.getStringAttribute("mailNickname"))
                .samAccountName(context.getStringAttribute("samAccountName"))
                .officePhone(context.getStringAttribute("officePhone"))
                .ipPhone(context.getStringAttribute("ipPhone"))
                .title(context.getStringAttribute("title"))
                .enabled(context.getStringAttribute("enabled"))
                .build();
    }

}
