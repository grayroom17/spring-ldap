package com.springldap.mapper;

import com.springldap.domain.entity.LdapUser;
import com.springldap.rest.dto.UserGetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.support.LdapUtils;

import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.ldap.LdapName;
import java.util.List;

@Mapper
public abstract class LdapUserMapper implements AttributesMapper<LdapUser> {

    @Override
    public LdapUser mapFromAttributes(Attributes attributes) throws NamingException {
        return LdapUser.builder()
                .distinguishedName(getDistinguishedName(attributes))
                .commonName(getAttributeValue(getAttribute("cn", attributes)))
                .canonicalName(getAttributeValue(getAttribute("canonicalName", attributes)))
                .guid(getAttributeValue(getAttribute("objectGuid", attributes)))
                .userPrincipalName(getAttributeValue(getAttribute("userPrincipalName", attributes)))
                .displayName(getAttributeValue(getAttribute("displayName", attributes)))
                .fullName(getAttributeValue(getAttribute("Name", attributes)))
                .firstname(getAttributeValue(getAttribute("GivenName", attributes)))
                .lastname(getAttributeValue(getAttribute("sn", attributes)))
                .otherName(getAttributeValue(getAttribute("OtherName", attributes)))
                .initials(getAttributeValue(getAttribute("initials", attributes)))
                .telephoneNumber(getAttributeValue(getAttribute("telephoneNumber", attributes)))
                .homePhone(getAttributeValue(getAttribute("homePhone", attributes)))
                .mobilePhone(getAttributeValue(getAttribute("mobilePhone", attributes)))
                .country(getAttributeValue(getAttribute("country", attributes)))
                .state(getAttributeValue(getAttribute("state", attributes)))
                .city(getAttributeValue(getAttribute("city", attributes)))
                .street(getAttributeValue(getAttribute("street", attributes)))
                .postalCode(getAttributeValue(getAttribute("postalCode", attributes)))
                .company(getAttributeValue(getAttribute("company", attributes)))
                .organization(getAttributeValue(getAttribute("organization", attributes)))
                .division(getAttributeValue(getAttribute("division", attributes)))
                .department(getAttributeValue(getAttribute("department", attributes)))
                .office(getAttributeValue(getAttribute("office", attributes)))
                .manager(getAttributeValue(getAttribute("manager", attributes)))
                .employeeId(getAttributeValue(getAttribute("employeeId", attributes)))
                .employeeNumber(getAttributeValue(getAttribute("employeeNumber", attributes)))
                .mail(getAttributeValue(getAttribute("mail", attributes)))
                .mailNickname(getAttributeValue(getAttribute("mailNickname", attributes)))
                .samAccountName(getAttributeValue(getAttribute("samAccountName", attributes)))
                .officePhone(getAttributeValue(getAttribute("officePhone", attributes)))
                .ipPhone(getAttributeValue(getAttribute("ipPhone", attributes)))
                .title(getAttributeValue(getAttribute("title", attributes)))
                .enabled(getAttributeValue(getAttribute("enabled", attributes)))
                .build();
    }

    private static String getAttributeValue(Attribute attribute) throws NamingException {
        return attribute != null
                ? attribute.get().toString()
                : null;
    }

    private static Attribute getAttribute(String attributeName, Attributes attributes) {
        return attributes.get(attributeName);
    }

    private static LdapName getDistinguishedName(Attributes attributes) throws NamingException {
        Attribute attribute = attributes.get("distinguishedName");
        return attribute == null
                ? LdapUtils.emptyLdapName()
                : new LdapName(attribute.get().toString());
    }

    public abstract List<UserGetDto> toGetDtoList(List<LdapUser> ldapUsers);

    @Mapping(target = "id", ignore = true)
    public abstract UserGetDto toGetDto(LdapUser ldapUser);

    public String mapDistinguishedName(Name distinguishedName) {
        return distinguishedName.toString();
    }

}
