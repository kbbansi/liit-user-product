package com.leanicontechnology.SpringBootAssessment.api.service.services;

import com.leanicontechnology.SpringBootAssessment.api.dto.ProductDto;
import com.leanicontechnology.SpringBootAssessment.api.dto.SubscriptionDto;
import com.leanicontechnology.SpringBootAssessment.api.dto.UserDto;

import java.util.List;
import java.util.Set;

public interface SubscriptionService {
    SubscriptionDto createNewSubscription(SubscriptionDto subscription);

    List<SubscriptionDto> findAllSubscriptions();

    SubscriptionDto findOneSubscription(String subscriptionUUID);

    SubscriptionDto updateSubscription(String subscriptionUUID, SubscriptionDto subscription);

    SubscriptionDto deleteSubscription(String subscriptionUUID);

    SubscriptionDto addProductToSubscription(String subscriptionUUID, Set<ProductDto> products);
}
