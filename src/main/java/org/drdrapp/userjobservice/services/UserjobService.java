package org.drdrapp.userjobservice.services;

import lombok.RequiredArgsConstructor;
import org.drdrapp.userjobservice.dto.request.RequestCompanyDto;
import org.drdrapp.userjobservice.dto.request.RequestDto;
import org.drdrapp.userjobservice.dto.request.RequestUserDto;
import org.drdrapp.userjobservice.dto.request.RequestUserjobDto;
import org.drdrapp.userjobservice.dto.response.ResponseCompanyUserDto;
import org.drdrapp.userjobservice.dto.response.ResponseUserCompanyDto;
import org.drdrapp.userjobservice.models.Company;
import org.drdrapp.userjobservice.models.Userjob;
import org.drdrapp.userjobservice.models.Users;
import org.drdrapp.userjobservice.repository.CompanyRepository;
import org.drdrapp.userjobservice.repository.UserRepository;
import org.drdrapp.userjobservice.repository.UserjobRepository;
import org.drdrapp.userjobservice.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserjobService {
    private final UserjobRepository userjobRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    public List<Userjob> getUserActiveJobsId(Users user) {
        return userjobRepository.getUserActiveJobsId(user);
    }

    public List<ResponseUserCompanyDto> getUserActiveJobsDto(Users user) {
        return userjobRepository.getUserActiveJobsDto(user);
    }

    public List<Userjob> getCompanyActiveUsersId(Company company) {
        return userjobRepository.getCompanyActiveUsersId(company);
    }

    public List<ResponseCompanyUserDto> getCompanyActiveUsersDto(Company company) {
        return userjobRepository.getCompanyActiveUsersDto(company);
    }

    @Transactional
    public Boolean createUserjob(RequestDto requestDto) {
        boolean result = false;
        if ((requestDto.getUser() != null) && (requestDto.getCompany() != null)) {
            if ((requestDto.getUser().getId() == null) && (requestDto.getCompany().getId() == null)) {
                Users prepareUser = modelMapper.map(requestDto.getUser(), Users.class);
                prepareUser.setCreated(LocalDateTime.now());
                prepareUser.setUpdated(null);
                Users resultUser = userRepository.save(prepareUser);
                Company prepareCompany = modelMapper.map(requestDto.getCompany(), Company.class);
                prepareCompany.setCreated(LocalDateTime.now());
                prepareCompany.setUpdated(null);
                Company resultCompany = companyRepository.save(prepareCompany);
                Userjob prepareUserjob = modelMapper.map(requestDto.getUserjob(), Userjob.class);
                prepareUserjob.setUser(resultUser);
                prepareUserjob.setCompany(resultCompany);
                prepareUserjob.setCreated(LocalDateTime.now());
                prepareUserjob.setUpdated(null);
                userjobRepository.save(prepareUserjob);
                result = true;
            }
        }
        return result;
    }

    @Transactional
    public List<String> updateUserjob(RequestDto requestDto) throws IllegalAccessException {
        boolean isExeption = false;
        boolean needUpdateUser = false;
        boolean needUpdateCompany = false;
        boolean needUpdateUserjob = false;
        Users oldUser = new Users();
        Company oldCompany = new Company();
        Userjob oldUserjob = new Userjob();

        RequestUserDto userDto = requestDto.getUser();
        if (userDto != null) {
            Long userId = userDto.getId();
            if (userId != null) {
                if (userRepository.existsById(userId)) {
                    needUpdateUser = true;
                    oldUser = userRepository.findById(userId).orElse(null);
                } else {
                    isExeption = true;
                }
            } else {
                isExeption = true;
            }
        }

        RequestCompanyDto companyDto = requestDto.getCompany();
        if (companyDto != null) {
            Long companyId = companyDto.getId();
            if (companyId != null) {
                if (companyRepository.existsById(companyId)) {
                    needUpdateCompany = true;
                    oldCompany = companyRepository.findById(companyId).orElse(null);
                } else {
                    isExeption = true;
                }
            } else {
                isExeption = true;
            }
        }

        RequestUserjobDto userjobDto = requestDto.getUserjob();
        if (userjobDto != null) {
            Long userjobId = userjobDto.getId();
            if (userjobId != null) {
                if (userjobRepository.existsById(userjobId)) {
                    needUpdateUserjob = true;
                    oldUserjob = userjobRepository.findById(userjobId).orElse(null);
                } else {
                    isExeption = true;
                }
            } else {
                isExeption = true;
            }
        }

        if (isExeption) {
            return null;
        } else {
            List<String> changedFields = new ArrayList<>();

            if (needUpdateUser && (oldUser != null)) {
                Users prepareUser = modelMapper.map(userDto, Users.class);
                prepareUser.setCreated(oldUser.getCreated());
                prepareUser.setUpdated(LocalDateTime.now());
                RequestUserDto oldUserDto = modelMapper.map(oldUser, RequestUserDto.class);
                changedFields = Utils.getDifferentFieldNames(userDto, oldUserDto);
                userRepository.save(prepareUser);
            }
            if (needUpdateCompany && (oldCompany != null)) {
                Company prepareCompany = modelMapper.map(companyDto, Company.class);
                prepareCompany.setCreated(oldCompany.getCreated());
                prepareCompany.setUpdated(LocalDateTime.now());
                RequestCompanyDto oldCompanyDto = modelMapper.map(oldCompany, RequestCompanyDto.class);
                changedFields.addAll(Utils.getDifferentFieldNames(companyDto, oldCompanyDto));
                companyRepository.save(prepareCompany);
            }
            if (needUpdateUserjob && (oldUserjob != null)) {
                Userjob prepareUserjob = modelMapper.map(userjobDto, Userjob.class);
                prepareUserjob.setCreated(oldUserjob.getCreated());
                prepareUserjob.setUpdated(LocalDateTime.now());
                prepareUserjob.setUser(oldUserjob.getUser());
                prepareUserjob.setCompany(oldUserjob.getCompany());
                RequestUserjobDto oldUserjobDto = modelMapper.map(oldUserjob, RequestUserjobDto.class);
                changedFields.addAll(Utils.getDifferentFieldNames(userjobDto, oldUserjobDto));
                userjobRepository.save(prepareUserjob);
            }

            return changedFields;
        }
    }
}