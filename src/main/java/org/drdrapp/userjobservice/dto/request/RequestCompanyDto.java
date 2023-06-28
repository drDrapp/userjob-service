package org.drdrapp.userjobservice.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCompanyDto {
    private Long id;
    private String companyName;
    private String description;
    private Boolean isActivity;
    private LocalDateTime created;
    private LocalDateTime updated;
}
