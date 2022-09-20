package com.leanicontechnology.SpringBootAssessment.data.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String userUUID;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String dateOfBirth;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String otherNames;

    @Column(nullable = false)
    private Boolean isActive;

    private String imageUrl;

    @Column(nullable = false)
    private LocalDateTime createdOn;

    private LocalDateTime modifiedOn;

    private String createdBy;

    private String modifiedBy;
}
