package org.drdrapp.userjobservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import org.drdrapp.userjobservice.dto.request.RequestCompanyDto;
import org.drdrapp.userjobservice.dto.request.RequestDto;
import org.drdrapp.userjobservice.dto.request.RequestUserDto;
import org.drdrapp.userjobservice.dto.request.RequestUserjobDto;
import org.drdrapp.userjobservice.dto.response.ResponseDto;
import org.drdrapp.userjobservice.dto.response.ResponseUserDto;
import org.drdrapp.userjobservice.models.Company;
import org.drdrapp.userjobservice.models.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class UserjobControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ModelMapper modelMapper;
    @Inject
    private ObjectMapper objectMapper;

    @Test
    public void getData_BadRequest() throws Exception {
        ResultActions resultActions =
                mockMvc
                        .perform(get("/get-userjob"))
                        .andDo(print())
                        .andExpect(status().isBadRequest());
    }

    @Test
    public void getData_NotFound() throws Exception {
        ResultActions resultActions =
                mockMvc
                        .perform(get("/get-userjob")
                                .param("user", "999")
                                .contentType(APPLICATION_JSON_VALUE))
                        .andDo(print())
                        .andExpect(status().isNotFound());
    }

    @Test
    public void getData_UserById() throws Exception {

        LocalDateTime createdDateTime = LocalDateTime.of(2000, 1, 1, 0, 0, 1);
        Users expectedUser = Users.builder()
                .id(1000L)
                .familyName("User1")
                .birthday(LocalDate.parse("1901-01-01"))
                .gender("M")
                .age(41)
                .isBlocked(false)
                .created(createdDateTime)
                .userCompanies(new ArrayList<>())
                .build();
        ResponseUserDto expectedUserDto = ResponseUserDto.builder()
                .user(expectedUser)
                .userCompanyList(new ArrayList<>())
                .build();
        ResponseDto expectedDto = ResponseDto.builder()
                .user(expectedUserDto)
                .company(null)
                .build();

        String expectedJson = objectMapper.writeValueAsString(expectedDto);

        ResultActions resultActions =
                mockMvc
                        .perform(get("/get-userjob")
                                .param("user", "1000")
                                .contentType(APPLICATION_JSON_VALUE))
                        .andDo(print())
                        .andDo((MvcResult response) -> {
                            if (response.getResponse().getStatus() != HttpStatus.OK.value()) {
                                throw new IllegalStateException("Failed: status code [" + response.getResponse().getStatus() + "]");
                            }
                            String content = response.getResponse().getContentAsString();
                            assertEquals(expectedJson, content);
                        });
    }

    @Test
    public void updateData_User() throws Exception {
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0, 1);
        RequestUserDto requestUserDto = RequestUserDto.builder()
                .id(1000L)
                .familyName("UserChanged!")
                .birthday(LocalDate.parse("2001-10-02"))
                .build();
        RequestDto requestDto = RequestDto.builder()
                .user(requestUserDto)
                .company(null)
                .userjob(null)
                .build();

        String requestJson = objectMapper.writeValueAsString(requestDto);

        ResultActions resultActions =
                mockMvc
                        .perform(patch("/update-userjob")
                                .contentType(APPLICATION_JSON_VALUE)
                                .content(requestJson))
                        .andDo(print())
                        .andExpect(status().isOk());

        resultActions =
                mockMvc
                        .perform(get("/get-userjob")
                                .param("user", "1000")
                                .contentType(APPLICATION_JSON_VALUE))
                        .andDo(print())
                        .andDo((MvcResult response) -> {
                            if (response.getResponse().getStatus() != HttpStatus.OK.value()) {
                                throw new IllegalStateException("Failed: status code [" + response.getResponse().getStatus() + "]");
                            }
                            String content = response.getResponse().getContentAsString();
                            ResponseDto responseDto = objectMapper.readValue(content, ResponseDto.class);
                            Users contentUser = responseDto.getUser().getUser();
                            contentUser.setCreated(dateTime);
                            contentUser.setUpdated(dateTime);
                            Users prepareUser = modelMapper.map(requestUserDto, Users.class);
                            prepareUser.setCreated(dateTime);
                            prepareUser.setUpdated(dateTime);
                            assertEquals(prepareUser, contentUser);
                        });
    }

    @Test
    public void createData_all() throws Exception {
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0, 1);
        RequestUserDto requestUserDto = RequestUserDto.builder()
                .familyName("UserX")
                .birthday(LocalDate.parse("2000-01-01"))
                .build();
        RequestCompanyDto requestCompanyDto = RequestCompanyDto.builder()
                .companyName("CompanyX")
                .isActivity(true)
                .build();
        RequestUserjobDto requestUserjobDto = RequestUserjobDto.builder()
                .description("UserX - CompanyX")
                .isActivity(true)
                .build();
        RequestDto requestDto = RequestDto.builder()
                .user(requestUserDto)
                .company(requestCompanyDto)
                .userjob(requestUserjobDto)
                .build();

        String requestJson = objectMapper.writeValueAsString(requestDto);

        ResultActions resultActions =
                mockMvc
                        .perform(post("/create-userjob")
                                .contentType(APPLICATION_JSON_VALUE)
                                .content(requestJson))
                        .andDo(print())
                        .andExpect(status().isCreated());
        // All new records start with id 10.000
        resultActions =
                mockMvc
                        .perform(get("/get-userjob")
                                .param("user", "10000")
                                .contentType(APPLICATION_JSON_VALUE))
                        .andDo(print())
                        .andDo((MvcResult response) -> {
                            if (response.getResponse().getStatus() != HttpStatus.OK.value()) {
                                throw new IllegalStateException("Failed: status code [" + response.getResponse().getStatus() + "]");
                            }
                            String content = response.getResponse().getContentAsString();
                            ResponseDto responseDto = objectMapper.readValue(content, ResponseDto.class);
                            Users contentUser = responseDto.getUser().getUser();
                            contentUser.setCreated(dateTime);
                            contentUser.setUpdated(dateTime);
                            Users prepareUser = modelMapper.map(requestUserDto, Users.class);
                            prepareUser.setId(10000L);
                            prepareUser.setCreated(dateTime);
                            prepareUser.setUpdated(dateTime);
                            assertEquals(prepareUser, contentUser);
                        });
        resultActions =
                mockMvc
                        .perform(get("/get-userjob")
                                .param("company", "10000")
                                .contentType(APPLICATION_JSON_VALUE))
                        .andDo(print())
                        .andDo((MvcResult response) -> {
                            if (response.getResponse().getStatus() != HttpStatus.OK.value()) {
                                throw new IllegalStateException("Failed: status code [" + response.getResponse().getStatus() + "]");
                            }
                            String content = response.getResponse().getContentAsString();
                            ResponseDto responseDto = objectMapper.readValue(content, ResponseDto.class);
                            Company contentCompany = responseDto.getCompany().getCompany();
                            contentCompany.setCreated(dateTime);
                            contentCompany.setUpdated(dateTime);
                            Company prepareCompany = modelMapper.map(requestCompanyDto, Company.class);
                            prepareCompany.setId(10000L);
                            prepareCompany.setCreated(dateTime);
                            prepareCompany.setUpdated(dateTime);
                            assertEquals(prepareCompany, contentCompany);
                        });
    }
}