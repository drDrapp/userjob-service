package org.drdrapp.userjobservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.drdrapp.userjobservice.models.Company;

import java.util.List;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class ResponseCompanyDto {
    private final Company company;
    private final List<ResponseCompanyUserDto> userCompanyList;
}
