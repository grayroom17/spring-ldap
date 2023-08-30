package com.springldap.mapper;

import com.springldap.domain.entity.LdapUser;
import com.springldap.rest.dto.UserGetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.naming.Name;
import java.util.List;

@Mapper
public interface LdapUserMapper {

    List<UserGetDto> toGetDtoList(List<LdapUser> ldapUsers);

    @Mapping(target = "id", ignore = true)
    UserGetDto toGetDto(LdapUser ldapUser);

    default String mapDistinguishedName(Name distinguishedName) {
        return distinguishedName.toString();
    }

}
