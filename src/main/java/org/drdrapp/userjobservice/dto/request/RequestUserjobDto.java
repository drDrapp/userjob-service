package org.drdrapp.userjobservice.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class RequestUserjobDto {
    private Long id;
    private String description;
    private Boolean isActivity;
    private LocalDateTime created;
    private LocalDateTime updated;
}
