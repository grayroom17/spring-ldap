package com.springldap.rest.controller;

import com.springldap.rest.dto.UserCreateDto;
import com.springldap.rest.dto.UserGetDto;
import com.springldap.service.LdapOdmUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("ldap-odm-users")
public class LdapOdmUserController {

    LdapOdmUserService ldapOdmUserService;

    @GetMapping("find-one-by-guid")
    UserGetDto findOneByGuid(@RequestParam("guid") String guid) {
        return ldapOdmUserService.findOneByGuid(guid);
    }

    @GetMapping
    List<UserGetDto> findAll() {
        return ldapOdmUserService.findAll();
    }

    @GetMapping("find-all-by-first-name")
    List<UserGetDto> findAllByFirsName(@RequestParam("firstName") String firstName) {
        return ldapOdmUserService.findAllByFirsName(firstName);
    }

    @PostMapping
    void create(@RequestBody UserCreateDto dto) {
        ldapOdmUserService.create(dto);
    }

    @PutMapping
    void update(@RequestBody UserCreateDto dto) {
        ldapOdmUserService.update(dto);
    }

    @DeleteMapping
    void delete(@RequestBody UserCreateDto dto) {
        ldapOdmUserService.delete(dto);
    }

}
