package com.springldap.rest.controller;

import com.springldap.service.LdapTemplateGroupService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("ldap-template-group")
public class LdapTemplateGroupController {

    LdapTemplateGroupService ldapTemplateGroupService;

    @PatchMapping("/add-member-to-group")
    void addMemberToGroup(@RequestParam("groupDn") String groupDn,
                          @RequestParam("userFullName") String userFullName) {
        ldapTemplateGroupService.addMemberToGroup(groupDn, userFullName);
    }

    @PatchMapping("/remove-member-from-group")
    void removeMemberFromGroup(@RequestParam("groupDn") String groupDn,
                               @RequestParam("userFullName") String userFullName) {
        ldapTemplateGroupService.removeMemberFromGroup(groupDn, userFullName);
    }

}
