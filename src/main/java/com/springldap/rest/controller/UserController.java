package com.springldap.rest.controller;

import com.springldap.rest.dto.UserGetDto;
import com.springldap.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {

    UserService userService;

    @GetMapping
    List<UserGetDto> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    UserGetDto findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @GetMapping("/find-by-guid/{guid}")
    UserGetDto findByGuid(@PathVariable("guid") String guid) {
        return userService.findByGuid(guid);
    }

    @GetMapping("/sync-with-ad")
    void syncWithAD() {
        userService.syncWithAD();
    }

}
