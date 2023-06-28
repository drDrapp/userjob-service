package org.drdrapp.userjobservice.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ResponseDto {
    private final ResponseUserDto user;
    private final ResponseCompanyDto company;
}
