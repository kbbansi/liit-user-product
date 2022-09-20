package com.leanicontechnology.SpringBootAssessment.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ProductDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String productUUID;
    private String productName;
    private String description;
    private double unitPrice;
    private boolean isActive;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
    private String createdBy;
    private String modifiedBy;
    private SubscriptionDto subscription;
}
