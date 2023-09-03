package com.springldap.mapper;

import com.springldap.domain.entity.Group;
import com.springldap.rest.dto.GroupGetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.naming.Name;
import java.util.List;

@Mapper
public interface GroupMapper {

    List<GroupGetDto> toGetDtoList(List<Group> groups);

    @Mapping(target = "distinguishedName", source = "dn")
    @Mapping(target = "commonName", source = "cn")
    GroupGetDto toGetDto(Group group);

    default String stringFromDistinguishedName(Name distinguishedName) {
        return distinguishedName.toString();
    }


}
