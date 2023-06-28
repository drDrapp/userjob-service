package org.drdrapp.userjobservice.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}