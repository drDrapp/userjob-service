package org.drdrapp.userjobservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.drdrapp.userjobservice.models.Users;

import java.util.List;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class ResponseUserDto {
    private final Users user;
    private final List<ResponseUserCompanyDto> userCompanyList;
}
