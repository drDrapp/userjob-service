package org.drdrapp.userjobservice.repository;

import org.drdrapp.userjobservice.dto.response.ResponseCompanyUserDto;
import org.drdrapp.userjobservice.dto.response.ResponseUserCompanyDto;
import org.drdrapp.userjobservice.models.Company;
import org.drdrapp.userjobservice.models.Userjob;
import org.drdrapp.userjobservice.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserjobRepository extends JpaRepository<Userjob, Long> {

    @Query("SELECT uj FROM Userjob uj " +
            "WHERE uj.user = ?1")
    List<Userjob> getUserJobsId(Users user);

    @Query("SELECT uj FROM Userjob uj " +
            "WHERE uj.user = ?1 AND uj.isActivity = true")
    List<Userjob> getUserActiveJobsId(Users user);

    @Query("SELECT NEW org.drdrapp.userjobservice.dto.response.ResponseUserCompanyDto(uj.company.id, uj.company.companyName, uj.company.description, uj.company.isActivity, uj.company.created, uj.company.updated, uj.description, uj.isActivity) FROM Userjob uj " +
            "WHERE uj.user = ?1 AND uj.isActivity = true")
    List<ResponseUserCompanyDto> getUserActiveJobsDto(Users user);

    @Query("SELECT uj FROM Userjob uj " +
            "WHERE uj.company = ?1 AND uj.isActivity = true")
    List<Userjob> getCompanyActiveUsersId(Company company);

    @Query("SELECT NEW org.drdrapp.userjobservice.dto.response.ResponseCompanyUserDto(uj.user.id, uj.user.familyName, uj.user.middleName, uj.user.firstName, uj.user.birthday, uj.user.gender, uj.user.age, uj.user.description, uj.user.isBlocked, uj.user.created, uj.user.updated, uj.description, uj.isActivity) FROM Userjob uj " +
            "WHERE uj.company = ?1 AND uj.isActivity = true")
    List<ResponseCompanyUserDto> getCompanyActiveUsersDto(Company company);

}