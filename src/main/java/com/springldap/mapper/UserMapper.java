package com.springldap.mapper;

import com.springldap.domain.entity.User;
import com.springldap.rest.dto.UserGetDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    UserGetDto toGetDto(User user);

    List<UserGetDto> toGetDtoList(List<User> users);

}
