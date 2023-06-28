package org.drdrapp.userjobservice.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class RequestUserDto {
    private Long id;
    private String familyName;
    private String middleName;
    private String firstName;
    private LocalDate birthday;
    private String gender;
    private Integer age;
    private String description;
    private Boolean isBlocked;
    private LocalDateTime created;
    private LocalDateTime updated;
}