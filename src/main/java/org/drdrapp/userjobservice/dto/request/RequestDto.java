package org.drdrapp.userjobservice.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class RequestDto {
    private final RequestUserDto user;
    private final RequestCompanyDto company;
    private final RequestUserjobDto userjob;
}