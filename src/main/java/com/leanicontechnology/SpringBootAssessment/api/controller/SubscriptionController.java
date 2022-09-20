package com.leanicontechnology.SpringBootAssessment.api.controller;

import com.leanicontechnology.SpringBootAssessment.api.dto.ProductDto;
import com.leanicontechnology.SpringBootAssessment.api.dto.SubscriptionDto;
import com.leanicontechnology.SpringBootAssessment.api.service.services.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/v1/subscriptions")
@AllArgsConstructor
public class SubscriptionController {

    SubscriptionService subscriptionService;

    @GetMapping(path = "/")
    public ResponseEntity<List<SubscriptionDto>> getAllSubscriptions() {
        return new ResponseEntity<>(subscriptionService.findAllSubscriptions(), HttpStatus.OK);
    }

    @GetMapping(path = "/{subscriptionUUID}")
    public ResponseEntity<SubscriptionDto> getOneSubscription(@PathVariable String subscriptionUUID) {
        return new ResponseEntity<>(subscriptionService.findOneSubscription(subscriptionUUID), HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<SubscriptionDto> createNewSubscription(@RequestBody SubscriptionDto subscription) {
        return new ResponseEntity<>(subscriptionService.createNewSubscription(subscription), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{subscriptionUUID}")
    public ResponseEntity<SubscriptionDto> updateSubscription(@PathVariable String subscriptionUUID,
                                                              @RequestBody SubscriptionDto subscription) {
        return new ResponseEntity<>(subscriptionService.updateSubscription(subscriptionUUID, subscription), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{subscriptionUUID}")
    public ResponseEntity<SubscriptionDto> deleteSubscription(@PathVariable String subscriptionUUID) {
        return new ResponseEntity<>(subscriptionService.deleteSubscription(subscriptionUUID), HttpStatus.OK);
    }

    @PutMapping(path = "/{subscriptionUUID}/add-product")
    public ResponseEntity<SubscriptionDto> addProductToSubscription(@PathVariable String subscriptionUUID, @RequestBody Set<ProductDto> products) {
        return new ResponseEntity<>(subscriptionService.addProductToSubscription(subscriptionUUID, products), HttpStatus.OK);
    }

}
