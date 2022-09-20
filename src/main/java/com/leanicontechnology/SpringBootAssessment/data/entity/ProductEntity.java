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
@Entity(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String productUUID;

    @Column(nullable = false)
    private String productName;

    private String description;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(nullable = false)
    private Double unitPrice;

    @Column(nullable = false)
    private LocalDateTime createdOn;

    private LocalDateTime modifiedOn;

    private String createdBy;

    private String modifiedBy;

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private SubscriptionEntity subscription;
}
