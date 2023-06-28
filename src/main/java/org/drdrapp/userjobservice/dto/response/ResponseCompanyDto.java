package org.drdrapp.userjobservice.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.drdrapp.userjobservice.models.Company;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class ResponseCompanyDto {
    private final Company company;
    private final List<ResponseCompanyUserDto> userCompanyList;
}
