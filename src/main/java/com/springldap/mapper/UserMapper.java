package com.springldap.mapper;

import com.springldap.domain.entity.LdapUser;
import com.springldap.domain.entity.User;
import com.springldap.rest.dto.UserGetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import javax.naming.Name;
import java.util.List;

@Mapper
public interface UserMapper {

    @Mapping(target = "homePhone", ignore = true)
    UserGetDto toGetDto(User user);

    List<UserGetDto> toGetDtoList(List<User> users);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "homePhone", ignore = true)
    void updateFromLdapUser(@MappingTarget User target, LdapUser source);

    default String mapDistinguishedName(Name name) {
        return name.toString();
    }

    List<User> fromLdapUserList(List<LdapUser> ldapUsers);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "homePhone", ignore = true)
    User fromLdapUser(LdapUser ldapUser);

}
