package com.springldap.repository;

import com.springldap.domain.entity.LdapUser;
import com.springldap.mapper.LdapUserMapper;
import com.springldap.rest.dto.AttributeDto;
import com.springldap.rest.dto.UserCreateDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.ldap.InvalidNameException;
import org.springframework.ldap.NameNotFoundException;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.web.server.ResponseStatusException;

import javax.naming.Name;
import javax.naming.directory.*;
import javax.naming.ldap.LdapName;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.ldap.query.LdapQueryBuilder.query;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class LdapTemplateRepositoryImpl implements LdapTemplateRepository {

    public static final String BASE_DN = "dc=spring-ldap,dc=com";

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

    @Override
    public void create(UserCreateDto dto) {
        Name dn = buildDn(dto);
        Attributes attributes = buildAttributes(dto);
        ldapTemplate.bind(dn, null, attributes);
    }

    @Override
    public void delete(String dn) {
        LdapName ldapName = LdapNameBuilder.newInstance(dn).build();
        ldapTemplate.unbind(ldapName);
    }

    @Override
    public void rebind(String dn, UserCreateDto dto) {
        LdapName ldapName = LdapNameBuilder.newInstance(dn).build();
        ldapTemplate.rebind(ldapName, null, buildAttributes(dto));
    }

    @Override
    public void updateAttribute(String dn, AttributeDto attribute) {
        LdapName ldapName = LdapNameBuilder.newInstance(dn).build();
        Attribute newAttribute = new BasicAttribute(attribute.getAttributeName(), attribute.getAttributeValue());
        ModificationItem modificationItem = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, newAttribute);
        ldapTemplate.modifyAttributes(ldapName, new ModificationItem[]{modificationItem});
    }

    private Name buildDn(UserCreateDto dto) {
        return LdapNameBuilder.newInstance("")
                .add("cn", dto.getCommonName())
                .build();
    }

    private Attributes buildAttributes(UserCreateDto dto) {
        Attributes attrs = new BasicAttributes(true);
        BasicAttribute objectClass = new BasicAttribute(OBJECT_CLASS);
        objectClass.add("top");
        objectClass.add("user");
        attrs.put(objectClass);
        attrs.put("cn", dto.getCommonName());
        attrs.put("canonicalName", dto.getCanonicalName());
        attrs.put("objectGuid", dto.getGuid());
        attrs.put("userPrincipalName", dto.getUserPrincipalName());
        attrs.put("displayName", dto.getDisplayName());
        attrs.put("Name", dto.getCommonName());
        attrs.put("GivenName", dto.getCommonName());
        attrs.put("sn", dto.getLastname());
        attrs.put("OtherName", dto.getOtherName());
        attrs.put("initials", dto.getInitials());
        attrs.put("telephoneNumber", dto.getTelephoneNumber());
        attrs.put("homePhone", dto.getHomePhone());
        attrs.put("mobilePhone", dto.getMobilePhone());
        attrs.put("country", dto.getCountry());
        attrs.put("state", dto.getState());
        attrs.put("city", dto.getCity());
        attrs.put("street", dto.getStreet());
        attrs.put("postalCode", dto.getPostalCode());
        attrs.put("company", dto.getCompany());
        attrs.put("organization", dto.getOrganization());
        attrs.put("division", dto.getDivision());
        attrs.put("department", dto.getDepartment());
        attrs.put("office", dto.getOffice());
        attrs.put("manager", dto.getManager());
        attrs.put("employeeId", dto.getEmployeeId());
        attrs.put("employeeNumber", dto.getEmployeeNumber());
        attrs.put("mail", dto.getMail());
        attrs.put("mailNickname", dto.getMailNickname());
        attrs.put("samAccountName", dto.getSamAccountName());
        attrs.put("officePhone", dto.getOfficePhone());
        attrs.put("ipPhone", dto.getIpPhone());
        attrs.put("title", dto.getTitle());
        attrs.put("enabled", dto.getEnabled());
        return attrs;
    }

}
