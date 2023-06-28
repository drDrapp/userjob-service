package org.drdrapp.userjobservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class ResponseDto {
    private final ResponseUserDto user;
    private final ResponseCompanyDto company;
}
