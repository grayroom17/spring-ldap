package com.springldap.service;

import com.springldap.domain.entity.LdapUser;
import com.springldap.domain.entity.User;
import com.springldap.mapper.UserMapper;
import com.springldap.repository.LdapCustomRepository;
import com.springldap.repository.UserRepository;
import com.springldap.rest.dto.UserGetDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class UserService {

    LdapCustomRepository ldapRepository;
    UserRepository userRepository;
    UserMapper userMapper;

    public List<UserGetDto> findAll() {
        return userMapper.toGetDtoList(userRepository.findAll());
    }

    @Transactional
    public void syncWithAD() {
        List<LdapUser> ldapUsers = ldapRepository.findAll();
        List<UUID> ldapUserGuids = ldapUsers.stream()
                .filter(Objects::nonNull)
                .map(ldapUser -> UUID.fromString(ldapUser.getGuid()))
                .toList();
        Map<String, LdapUser> ldapUserByGuid = ldapUsers.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(LdapUser::getGuid, identity()));

        List<User> existedUsers = userRepository.findAllByGuidIsIn(ldapUserGuids);

        existedUsers.forEach(user -> userMapper.updateFromLdapUser(user, ldapUserByGuid.get(user.getGuid().toString())));

        userRepository.saveAll(existedUsers);

        List<String> existedUserGuids = existedUsers.stream()
                .map(user -> user.getGuid().toString())
                .toList();

        List<LdapUser> newLdapUsers = ldapUsers.stream()
                .filter(Objects::nonNull)
                .filter(ldapUser -> !existedUserGuids.contains(ldapUser.getGuid()))
                .toList();

        userRepository.saveAll(userMapper.fromLdapUserList(newLdapUsers));
    }

}
