package com.springldap.rest.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupGetDto {

    String distinguishedName;
    String commonName;
    List<String> members;

}
