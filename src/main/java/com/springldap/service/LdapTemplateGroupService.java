package com.springldap.service;

import com.springldap.repository.LdapTemplateGroupRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class LdapTemplateGroupService {

    LdapTemplateGroupRepository ldapTemplateRepository;

    public void addMemberToGroup(String groupName, String userFullName) {
        ldapTemplateRepository.addMemberToGroup(groupName, userFullName);
    }

    public void removeMemberFromGroup(String groupName, String userFullName) {
        ldapTemplateRepository.removeMemberFromGroup(groupName, userFullName);
    }

}
