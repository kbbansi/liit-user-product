package com.leanicontechnology.SpringBootAssessment.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class SubscriptionDto {
    private long id;
    private String subscriptionUUID;
    private String subscriptionName;
    private String description;
    private String startDate;
    private String endDate;
    private Double validityPeriod;
    private Double price;
    private boolean isActive;
    private UserDto user;
    private Set<ProductDto> products;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
    private String createdBy;
    private String modifiedBy;
}
