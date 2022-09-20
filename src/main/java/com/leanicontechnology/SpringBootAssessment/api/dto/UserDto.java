package com.leanicontechnology.SpringBootAssessment.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String userUUID;
    private String userName;
    private String dateOfBirth;
    private String email;
    private String firstName;
    private String lastName;
    private String otherNames;
    private boolean isActive;
    private String imageUrl;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
    private String createdBy;
    private String modifiedBy;
}
