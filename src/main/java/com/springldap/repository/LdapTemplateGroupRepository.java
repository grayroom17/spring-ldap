package com.springldap.repository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapNameAware;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Repository;

import javax.naming.Name;
import javax.naming.ldap.LdapName;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Repository
public class LdapTemplateGroupRepository implements BaseLdapNameAware {

    LdapTemplate ldapTemplate;
    @NonFinal
    LdapName baseLdapPath;

    public void addMemberToGroup(String groupName, String userFullName) {
        Name groupDn = buildGroupDn(groupName);
        Name userDn = buildPersonDn(userFullName);

        DirContextOperations ctx = ldapTemplate.lookupContext(groupDn);
        ctx.addAttributeValue("member", userDn.toString());

        ldapTemplate.modifyAttributes(ctx);
    }

    public void removeMemberFromGroup(String groupName, String userFullName) {
        Name groupDn = buildGroupDn(groupName);
        Name userDn = buildPersonDn(userFullName);

        DirContextOperations ctx = ldapTemplate.lookupContext(groupDn);
        ctx.removeAttributeValue("member", userDn.toString());

        ldapTemplate.modifyAttributes(ctx);
    }

    public void setBaseLdapPath(LdapName baseLdapPath) {
        this.baseLdapPath = baseLdapPath;
    }

    private Name buildGroupDn(String groupName) {
        return LdapNameBuilder.newInstance("ou=app_users_group,ou=Groups")
                .add("cn", groupName).build();
    }

    private Name buildPersonDn(String fullName) {
        return LdapNameBuilder.newInstance(baseLdapPath)
                .add("cn", fullName)
                .build();
    }
}
