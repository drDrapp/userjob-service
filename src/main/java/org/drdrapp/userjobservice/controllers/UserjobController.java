package org.drdrapp.userjobservice.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.drdrapp.userjobservice.dto.request.RequestDto;
import org.drdrapp.userjobservice.dto.response.ResponseCompanyDto;
import org.drdrapp.userjobservice.dto.response.ResponseDto;
import org.drdrapp.userjobservice.dto.response.ResponseUserDto;
import org.drdrapp.userjobservice.models.Company;
import org.drdrapp.userjobservice.models.Users;
import org.drdrapp.userjobservice.services.UserjobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserjobController {
    private final UserjobService userjobService;

    @GetMapping("/get-userjob")
    public ResponseEntity<?> getData(@RequestParam(value = "user", required = false) Users user,
                                     @RequestParam(value = "company", required = false) Company company,
                                     @RequestParam(required = false) Map<String, String> params,
                                     HttpServletRequest request) {

        if (params.isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else if (params.containsKey("user")) {
            if (user == null) {
                return ResponseEntity.notFound().build();
            } else {
                user.setUserCompanies(userjobService.getUserActiveJobsId(user));
                return ResponseEntity.ok(new ResponseDto(new ResponseUserDto(user, userjobService.getUserActiveJobsDto(user)), null));
            }
        } else if (params.containsKey("company")) {
            if (company == null) {
                return ResponseEntity.notFound().build();
            } else {
                company.setCompanyUsers(userjobService.getCompanyActiveUsersId(company));
                return ResponseEntity.ok(new ResponseDto(null, new ResponseCompanyDto(company, userjobService.getCompanyActiveUsersDto(company))));
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "/create-userjob")
    public ResponseEntity<?> createData(@RequestBody RequestDto requestDto) {
        if (userjobService.createUserjob(requestDto)) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }
    }

    @PatchMapping("/update-userjob")
    public ResponseEntity<List<String>> updateDate(@RequestBody RequestDto requestDto) throws IllegalAccessException {
        List<String> changedFields = userjobService.updateUserjob(requestDto);
        if (changedFields == null) {
            return ResponseEntity
                    .badRequest()
                    .build();
        } else {
            return ResponseEntity
                    .ok(changedFields);
        }
    }

}