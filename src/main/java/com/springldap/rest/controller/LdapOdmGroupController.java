package com.springldap.rest.controller;

import com.springldap.rest.dto.GroupGetDto;
import com.springldap.service.LdapOdmGroupService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("ldap-odm-group")
public class LdapOdmGroupController {

    LdapOdmGroupService ldapOdmGroupService;

    @GetMapping
    List<GroupGetDto> findAll() {
        return ldapOdmGroupService.findAll();
    }

    @PatchMapping("/add-member-to-group")
    void addMemberToGroup(@RequestParam("groupCn") String groupCn,
                          @RequestParam("userDn") String userDn) {
        ldapOdmGroupService.addMemberToGroup(groupCn, userDn);
    }

    @PatchMapping("/remove-member-from-group")
    void removeMemberFromGroup(@RequestParam("groupDn") String groupDn,
                               @RequestParam("userDn") String userDn) {
        ldapOdmGroupService.removeMemberFromGroup(groupDn, userDn);
    }

}
