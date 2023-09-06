package com.springldap.repository;

import com.springldap.domain.entity.LdapUser;
import com.springldap.mapper.LdapUserAttributesMapper;
import com.springldap.mapper.LdapUserContextMapper;
import com.springldap.rest.dto.AttributeDto;
import com.springldap.rest.dto.UserCreateDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.ldap.InvalidNameException;
import org.springframework.ldap.NameNotFoundException;
import org.springframework.ldap.core.*;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Repository;
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
@Repository
public class LdapTemplateUserRepository {

    public static final String OBJECT_CLASS = "objectClass";

    LdapTemplate ldapTemplate;
    LdapUserAttributesMapper attributesMapper;
    LdapUserContextMapper contextMapper;

    public List<String> getAllUserNames() {
        return ldapTemplate.search(query().
                        where(OBJECT_CLASS).is("user"),
                (AttributesMapper<String>) attributes -> attributes.get("givenName").get().toString());
    }

    public List<LdapUser> getAllUsers() {
        return ldapTemplate.search(query()
                        .where(OBJECT_CLASS).is("user"),
                contextMapper);
    }

    public List<LdapUser> findAllByFullName(String fullName) {
        return ldapTemplate.search(
                query()
                        .filter("(&(objectClass=user)(name=%s))".formatted(fullName)),
                contextMapper);
    }

    public List<LdapUser> getAllUsersBySureName(String sureName) {
        return ldapTemplate.search(query()
                        .where(OBJECT_CLASS).is("user")
                        .and("sn").is(sureName),
                attributesMapper);
    }

    public Optional<LdapUser> lookupByDn(String dn) {
        try {
            return Optional.of(ldapTemplate.lookup(dn, attributesMapper));
        } catch (NameNotFoundException e) {
            return Optional.empty();
        } catch (InvalidNameException e) {
            throw new ResponseStatusException(BAD_REQUEST, dn, e);
        }
    }

    public Optional<LdapUser> lookupByDnWithContextMapper(String dn) {
        try {
            return Optional.of(ldapTemplate.lookup(dn, contextMapper));
        } catch (NameNotFoundException e) {
            return Optional.empty();
        } catch (InvalidNameException e) {
            throw new ResponseStatusException(BAD_REQUEST, dn, e);
        }
    }

    public void create(UserCreateDto dto) {
        Name dn = buildDn(dto);
        Attributes attributes = buildAttributes(dto);
        ldapTemplate.bind(dn, null, attributes);
    }

    //предпочтительный способ создания новой записи
    public void createWithDirContextAdapter(String dn, UserCreateDto dto) {
        LdapName ldapName = LdapNameBuilder.newInstance(dn).build();
        DirContextAdapter ctx = new DirContextAdapter(ldapName);

        mapToContext(dto, ctx);

        ldapTemplate.bind(ctx);
    }

    public void delete(String dn) {
        LdapName ldapName = LdapNameBuilder.newInstance(dn).build();
        ldapTemplate.unbind(ldapName);
    }

    public void rebind(String dn, UserCreateDto dto) {
        LdapName ldapName = LdapNameBuilder.newInstance(dn).build();
        ldapTemplate.rebind(ldapName, null, buildAttributes(dto));
    }

    public void updateAttribute(String dn, AttributeDto attribute) {
        LdapName ldapName = LdapNameBuilder.newInstance(dn).build();
        Attribute newAttribute = new BasicAttribute(attribute.getAttributeName(), attribute.getAttributeValue());
        ModificationItem modificationItem = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, newAttribute);
        ldapTemplate.modifyAttributes(ldapName, new ModificationItem[]{modificationItem});
    }

    //предпочтительный способ обновления
    public void updateWithDirContextOperations(String dn, UserCreateDto dto) {
        LdapName ldapName = LdapNameBuilder.newInstance(dn).build();
        DirContextOperations context = ldapTemplate.lookupContext(ldapName);

        mapToContext(dto, context);

        ldapTemplate.modifyAttributes(context);
    }

    public List search(final Name base, final String filter, final String[] params, final SearchControls controls) {
        SearchExecutor executor = ctx -> ctx.search(base, filter, params, controls);

        CollectingNameClassPairCallbackHandler handler = new ContextMapperCallbackHandler(contextMapper);

        ldapTemplate.search(executor, handler);
        return handler.getList();
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

    private static void mapToContext(UserCreateDto dto, DirContextOperations ctx) {
        ctx.setAttributeValues(OBJECT_CLASS, new String[]{"top", "user"});
        ctx.setAttributeValue("cn", dto.getCommonName());
        ctx.setAttributeValue("canonicalName", dto.getCanonicalName());
        ctx.setAttributeValue("objectGuid", dto.getGuid());
        ctx.setAttributeValue("userPrincipalName", dto.getUserPrincipalName());
        ctx.setAttributeValue("displayName", dto.getDisplayName());
        ctx.setAttributeValue("Name", dto.getCommonName());
        ctx.setAttributeValue("GivenName", dto.getCommonName());
        ctx.setAttributeValue("sn", dto.getLastname());
        ctx.setAttributeValue("OtherName", dto.getOtherName());
        ctx.setAttributeValue("initials", dto.getInitials());
        ctx.setAttributeValue("telephoneNumber", dto.getTelephoneNumber());
        ctx.setAttributeValue("homePhone", dto.getHomePhone());
        ctx.setAttributeValue("mobilePhone", dto.getMobilePhone());
        ctx.setAttributeValue("country", dto.getCountry());
        ctx.setAttributeValue("state", dto.getState());
        ctx.setAttributeValue("city", dto.getCity());
        ctx.setAttributeValue("street", dto.getStreet());
        ctx.setAttributeValue("postalCode", dto.getPostalCode());
        ctx.setAttributeValue("company", dto.getCompany());
        ctx.setAttributeValue("organization", dto.getOrganization());
        ctx.setAttributeValue("division", dto.getDivision());
        ctx.setAttributeValue("department", dto.getDepartment());
        ctx.setAttributeValue("office", dto.getOffice());
        ctx.setAttributeValue("manager", dto.getManager());
        ctx.setAttributeValue("employeeId", dto.getEmployeeId());
        ctx.setAttributeValue("employeeNumber", dto.getEmployeeNumber());
        ctx.setAttributeValue("mail", dto.getMail());
        ctx.setAttributeValue("mailNickname", dto.getMailNickname());
        ctx.setAttributeValue("samAccountName", dto.getSamAccountName());
        ctx.setAttributeValue("officePhone", dto.getOfficePhone());
        ctx.setAttributeValue("ipPhone", dto.getIpPhone());
        ctx.setAttributeValue("title", dto.getTitle());
        ctx.setAttributeValue("enabled", dto.getEnabled());
    }

    //ODM - Object-Directory Mapping

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

    public List<LdapUser> findAllInBase(String base) {
        return ldapTemplate.find(
                query()
                        .base(base)
                        .where(OBJECT_CLASS)
                        .is("user"),
                LdapUser.class);
    }

}
