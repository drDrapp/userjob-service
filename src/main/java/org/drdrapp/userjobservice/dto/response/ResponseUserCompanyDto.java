package org.drdrapp.userjobservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ResponseUserCompanyDto {
    private Long id;
    private String companyName;
    private String description;
    private Boolean isActivity;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String linkDescription;
    private Boolean linkIsActivity;

    public ResponseUserCompanyDto(Long id,
                                  String companyName,
                                  String description,
                                  Boolean isActivity,
                                  LocalDateTime created,
                                  LocalDateTime updated,
                                  String linkDescription,
                                  Boolean linkIsActivity) {
        this.id = id;
        this.companyName = companyName;
        this.description = description;
        this.isActivity = isActivity;
        this.created = created;
        this.updated = updated;
        this.linkDescription = linkDescription;
        this.linkIsActivity = linkIsActivity;
    }
}
