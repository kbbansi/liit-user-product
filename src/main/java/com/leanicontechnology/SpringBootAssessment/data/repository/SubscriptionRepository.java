package com.leanicontechnology.SpringBootAssessment.data.repository;

import com.leanicontechnology.SpringBootAssessment.data.entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {
    Optional<SubscriptionEntity> findBySubscriptionUUID(String subscriptionUUID);

    Optional<SubscriptionEntity> findBySubscriptionName(String subscriptionName);

}
