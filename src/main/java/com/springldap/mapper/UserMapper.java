package com.springldap.mapper;

import com.springldap.domain.entity.LdapUser;
import com.springldap.domain.entity.User;
import com.springldap.rest.dto.UserGetDto;
import org.mapstruct.*;

import javax.naming.Name;
import java.util.List;

@Mapper
public interface UserMapper {

    UserGetDto toGetDto(User user);

    List<UserGetDto> toGetDtoList(List<User> users);

    @Mapping(target = "id", ignore = true)
    void updateFromLdapUser(@MappingTarget User target, LdapUser source);

    default String mapDistinguishedName(Name name) {
        return name.toString();
    }

    List<User> fromLdapUserList(List<LdapUser> ldapUsers);

    @Mapping(target = "id", ignore = true)
    User fromLdapUserList(LdapUser ldapUser);

}
