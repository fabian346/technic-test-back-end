package com.technicTestBackEnd.api.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class InventoryEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public InventoryEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishInventoryChangeEvent(Long productId, int newQuantity) {
        InventoryChangedEvent event = new InventoryChangedEvent(this, productId, newQuantity);
        eventPublisher.publishEvent(event);
    }
}
