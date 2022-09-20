package com.leanicontechnology.SpringBootAssessment.api.service.impl;

import com.leanicontechnology.SpringBootAssessment.api.dto.ProductDto;
import com.leanicontechnology.SpringBootAssessment.api.dto.SubscriptionDto;
import com.leanicontechnology.SpringBootAssessment.api.dto.UserDto;
import com.leanicontechnology.SpringBootAssessment.api.service.services.SubscriptionService;
import com.leanicontechnology.SpringBootAssessment.data.entity.ProductEntity;
import com.leanicontechnology.SpringBootAssessment.data.entity.SubscriptionEntity;
import com.leanicontechnology.SpringBootAssessment.data.repository.ProductRepository;
import com.leanicontechnology.SpringBootAssessment.data.repository.SubscriptionRepository;
import com.leanicontechnology.SpringBootAssessment.data.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubscriptionServiceImpl implements SubscriptionService {
    ModelMapper mapper = new ModelMapper();

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    /**
     * @param subscription subscription to be created
     * @return created subscription
     */
    @Override
    public SubscriptionDto createNewSubscription(SubscriptionDto subscription) {
        log.info("Create Subscription Service; Request Body: [{}]", subscription);

        var subscriptionAlreadyExists = subscriptionRepository.findBySubscriptionName(subscription.getSubscriptionName().trim())
                .stream()
                .map(subscriptionEntity -> subscriptionEntity.getSubscriptionName().equals(subscription.getSubscriptionName().trim()))
                .findAny()
                .orElse(Boolean.FALSE);

        if (!subscriptionAlreadyExists) {
            var subscriptionEntity = SubscriptionEntity.builder()
                    .subscriptionUUID(UUID.randomUUID().toString())
                    .subscriptionName(subscription.getSubscriptionName())
                    .description(subscription.getDescription())
                    .validityPeriod(subscription.getValidityPeriod()) // can be computed from start and end dates
                    .startDate(subscription.getStartDate())
                    .endDate(subscription.getEndDate())
                    .createdOn(LocalDateTime.now())
                    .isActive(true)
                    .build();
            return mapper.map(subscriptionRepository.save(subscriptionEntity), SubscriptionDto.class);
        }
        throw new RuntimeException("Subscription with name: " + subscription.getSubscriptionName() + " already exists");
    }

    /**
     * @return list of all subscriptions
     */
    @Override
    public List<SubscriptionDto> findAllSubscriptions() {
        log.info("Get All Subscriptions Service");
        return subscriptionRepository.findAll()
                .stream()
                .map(subscription -> mapper.map(subscription, SubscriptionDto.class))
                .collect(Collectors.toList());
    }

    /**
     * @param subscriptionUUID subscription uuid
     * @return subscription
     */
    @Override
    public SubscriptionDto findOneSubscription(String subscriptionUUID) {
        var subscription = getSubscription(subscriptionUUID);
        return mapper.map(subscription, SubscriptionDto.class);
    }

    /**
     * @param subscriptionUUID subscription to be updated
     * @param subscription     update request object
     * @return updated subscription
     */
    @Override
    public SubscriptionDto updateSubscription(String subscriptionUUID, SubscriptionDto subscription) {
        log.info("Update Subscription Service; Request Body: [{}]", subscription);
        var subscriptionEntity = getSubscription(subscriptionUUID);

        subscriptionEntity.setSubscriptionName(subscription.getSubscriptionName());
        subscriptionEntity.setDescription(subscription.getDescription());
        subscriptionEntity.setModifiedOn(LocalDateTime.now());
        subscriptionEntity.setValidityPeriod(subscription.getValidityPeriod());
        return mapper.map(subscriptionRepository.save(subscriptionEntity), SubscriptionDto.class);
    }

    /**
     * @param subscriptionUUID subscription to be deleted
     * @return deleted subscription
     */
    @Override
    public SubscriptionDto deleteSubscription(String subscriptionUUID) {
        log.info("Delete Subscription Service");
        var subscription = getSubscription(subscriptionUUID);
        subscription.setIsActive(false);
        return mapper.map(subscriptionRepository.save(subscription), SubscriptionDto.class);
    }

    /**
     * @param subscriptionUUID subscription to add products to
     * @param products set of products
     * @return subscription
     */
    @Override
    public SubscriptionDto addProductToSubscription(String subscriptionUUID, Set<ProductDto> products) {
        log.info("Add products to Subscription");
        var subscriptionEntity = subscriptionRepository.findBySubscriptionUUID(subscriptionUUID).orElseThrow(()-> new RuntimeException("No subscription found"));

        // var subscription = mapper.map(subscriptionEntity, SubscriptionDto.class);

        var productsEntity = products.stream()
                .map(product -> productRepository.findByProductUUID(product.getProductUUID())
                        .orElseThrow(RuntimeException::new))
                .collect(Collectors.toSet());
        log.info("Products to be added: [{}]", productsEntity);
        subscriptionEntity.setProducts(productsEntity);
        return mapper.map(subscriptionRepository.save(subscriptionEntity), SubscriptionDto.class);
    }



    private SubscriptionEntity getSubscription(String subscriptionUUID) {
        return subscriptionRepository.findBySubscriptionUUID(subscriptionUUID)
                .orElseThrow(() -> new RuntimeException("No subscription found with ID: " + subscriptionUUID));
    }
}
