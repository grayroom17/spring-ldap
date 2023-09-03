package com.springldap.domain.entity;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entry(objectClasses = {"top", "groupOfNames"}/*, base = "ou=app_users_group,ou=Groups"*/)
public class Group {

    @Id
    Name dn;

    @Attribute(name = "cn")
    @DnAttribute("cn")
    String cn;

    @Attribute(name = "member")
    Set<Name> members;

    public void addMember(Name member) {
        members.add(member);
    }

    public void removeMember(Name member) {
        members.remove(member);
    }

}
