package com.springldap.repository;

import com.springldap.domain.entity.Group;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Repository;

import javax.naming.Name;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Repository
public class LdapOdmGroupRepository {

    LdapTemplate ldapTemplate;

    public List<Group> findAll() {

        return ldapTemplate.findAll(Group.class);

    }

    public void addMemberToGroup(String groupCn, String userDn) {
        Name groupDn = buildGroupDn(groupCn);
        Name userDistinguishedName = LdapNameBuilder.newInstance(userDn).build();

        Group group = ldapTemplate.findByDn(groupDn, Group.class);
        group.addMember(userDistinguishedName);

        ldapTemplate.update(group);
    }

    public void removeMemberFromGroup(String groupName, String userDn) {
        Name groupDn = buildGroupDn(groupName);
        Name userDistinguishedName = LdapNameBuilder.newInstance(userDn).build();

        Group group = ldapTemplate.findByDn(groupDn, Group.class);
        group.removeMember(userDistinguishedName);

        ldapTemplate.update(group);
    }

    private Name buildGroupDn(String groupName) {
        return LdapNameBuilder.newInstance("ou=app_users_group,ou=Groups")
                .add("cn", groupName).build();
    }

}
