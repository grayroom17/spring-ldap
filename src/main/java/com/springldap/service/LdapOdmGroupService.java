package com.springldap.service;

import com.springldap.mapper.GroupMapper;
import com.springldap.repository.LdapOdmGroupRepository;
import com.springldap.rest.dto.GroupGetDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class LdapOdmGroupService {

    LdapOdmGroupRepository ldapOdmGroupRepository;
    GroupMapper groupMapper;

    public List<GroupGetDto> findAll() {
        return groupMapper.toGetDtoList(ldapOdmGroupRepository.findAll());
    }

    public void addMemberToGroup(String groupCn, String userDn) {
        ldapOdmGroupRepository.addMemberToGroup(groupCn, userDn);
    }

    public void removeMemberFromGroup(String groupName, String userDn) {
        ldapOdmGroupRepository.removeMemberFromGroup(groupName, userDn);
    }

}
