package com.springldap.mapper;

import com.springldap.domain.entity.LdapUser;
import com.springldap.rest.dto.UserCreateDto;
import com.springldap.rest.dto.UserGetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.ldap.support.LdapNameBuilder;

import javax.naming.Name;
import java.util.List;

@Mapper
public interface LdapUserMapper {

    List<UserGetDto> toGetDtoList(List<LdapUser> ldapUsers);

    @Mapping(target = "id", ignore = true)
    UserGetDto toGetDto(LdapUser ldapUser);

    default String stringFromDistinguishedName(Name distinguishedName) {
        return distinguishedName.toString();
    }

    LdapUser fromCreateDto(UserCreateDto dto);

    default Name distinguishedNameFromString(String dn) {
        return LdapNameBuilder.newInstance(dn).build();
    }

}
