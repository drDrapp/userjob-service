package org.drdrapp.userjobservice.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class RequestDto {
    private final RequestUserDto user;
    private final RequestCompanyDto company;
    private final RequestUserjobDto userjob;
}