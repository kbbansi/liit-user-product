package com.leanicontechnology.SpringBootAssessment.data.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Getter
@Setter
@Entity(name = "subscriptions")
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String subscriptionUUID;

    @Column(nullable = false)
    private String subscriptionName;

    private String description;

    @Column(nullable = false)
    private String startDate;

    @Column(nullable = false)
    private String endDate;

    @Column(nullable = false)
    private Double validityPeriod;

    private Double price;

    @Column(nullable = false)
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToMany
    @JoinColumn(name = "product_id")
    private Set<ProductEntity> products;

    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
    private String createdBy;
    private String modifiedBy;
}
