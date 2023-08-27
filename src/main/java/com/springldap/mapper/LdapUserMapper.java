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
                .commonName(attributes.get("cn").get().toString())
                .canonicalName(attributes.get("canonicalName").get().toString())
                .guid(attributes.get("objectGuid").get().toString())
                .userPrincipalName(attributes.get("userPrincipalName").get().toString())
                .displayName(attributes.get("displayName").get().toString())
                .fullName(attributes.get("Name").get().toString())
                .firstname(attributes.get("GivenName").get().toString())
                .lastname(attributes.get("sn").get().toString())
                .otherName(attributes.get("OtherName").get().toString())
                .initials(attributes.get("initials").get().toString())
                .telephoneNumber(attributes.get("telephoneNumber").get().toString())
                .homePhone(attributes.get("homePhone").get().toString())
                .mobilePhone(attributes.get("mobilePhone").get().toString())
                .country(attributes.get("country").get().toString())
                .state(attributes.get("state").get().toString())
                .city(attributes.get("city").get().toString())
                .street(attributes.get("street").get().toString())
                .postalCode(attributes.get("postalCode").get().toString())
                .company(attributes.get("company").get().toString())
                .organization(attributes.get("organization").get().toString())
                .division(attributes.get("division").get().toString())
                .department(attributes.get("department").get().toString())
                .office(attributes.get("office").get().toString())
                .manager(attributes.get("manager").get().toString())
                .employeeId(attributes.get("employeeId").get().toString())
                .employeeNumber(attributes.get("employeeNumber").get().toString())
                .mail(attributes.get("mail").get().toString())
                .mailNickname(attributes.get("mailNickname").get().toString())
                .samAccountName(attributes.get("samAccountName").get().toString())
                .officePhone(attributes.get("officePhone").get().toString())
                .ipPhone(attributes.get("ipPhone").get().toString())
                .title(attributes.get("title").get().toString())
                .enabled(attributes.get("enabled").get().toString())
                .build();
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
