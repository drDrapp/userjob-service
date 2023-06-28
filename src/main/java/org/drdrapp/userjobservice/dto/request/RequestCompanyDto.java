package org.drdrapp.userjobservice.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class RequestCompanyDto {
    private Long id;
    private String companyName;
    private String description;
    private Boolean isActivity;
    private LocalDateTime created;
    private LocalDateTime updated;
}
