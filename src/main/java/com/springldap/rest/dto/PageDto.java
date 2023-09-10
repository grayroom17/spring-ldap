package com.springldap.rest.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageDto {

    Integer pageNumber;

    Integer pageSize;

}
