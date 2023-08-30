package com.springldap.mapper;

import com.springldap.domain.entity.LdapUser;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class LdapUserContextMapper extends AbstractContextMapper<LdapUser> {

    @Override
    public LdapUser doMapFromContext(DirContextOperations ctx) {
        return LdapUser.builder()
                .distinguishedName(ctx.getDn())
                .commonName(ctx.getStringAttribute("cn"))
                .canonicalName(ctx.getStringAttribute("canonicalName"))
                .guid(ctx.getStringAttribute("objectGuid"))
                .userPrincipalName(ctx.getStringAttribute("userPrincipalName"))
                .displayName(ctx.getStringAttribute("displayName"))
                .fullName(ctx.getStringAttribute("Name"))
                .firstname(ctx.getStringAttribute("GivenName"))
                .lastname(ctx.getStringAttribute("sn"))
                .otherName(ctx.getStringAttribute("OtherName"))
                .initials(ctx.getStringAttribute("initials"))
                .telephoneNumber(ctx.getStringAttribute("telephoneNumber"))
                .homePhone(Arrays.asList(ctx.getStringAttributes("homePhone")))
                .mobilePhone(ctx.getStringAttribute("mobilePhone"))
                .country(ctx.getStringAttribute("country"))
                .state(ctx.getStringAttribute("state"))
                .city(ctx.getStringAttribute("city"))
                .street(ctx.getStringAttribute("street"))
                .postalCode(ctx.getStringAttribute("postalCode"))
                .company(ctx.getStringAttribute("company"))
                .organization(ctx.getStringAttribute("organization"))
                .division(ctx.getStringAttribute("division"))
                .department(ctx.getStringAttribute("department"))
                .office(ctx.getStringAttribute("office"))
                .manager(ctx.getStringAttribute("manager"))
                .employeeId(ctx.getStringAttribute("employeeId"))
                .employeeNumber(ctx.getStringAttribute("employeeNumber"))
                .mail(ctx.getStringAttribute("mail"))
                .mailNickname(ctx.getStringAttribute("mailNickname"))
                .samAccountName(ctx.getStringAttribute("samAccountName"))
                .officePhone(ctx.getStringAttribute("officePhone"))
                .ipPhone(ctx.getStringAttribute("ipPhone"))
                .title(ctx.getStringAttribute("title"))
                .enabled(ctx.getStringAttribute("enabled"))
                .build();
    }

}
