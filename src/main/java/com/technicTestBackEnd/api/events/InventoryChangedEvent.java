package com.technicTestBackEnd.api.events;

import org.springframework.context.ApplicationEvent;

public class InventoryChangedEvent extends ApplicationEvent {

    private final Long productId;
    private final int newQuantity;

    public InventoryChangedEvent(Object source, Long productId, int newQuantity) {
        super(source);
        this.productId = productId;
        this.newQuantity = newQuantity;
    }

    public Long getProductId() {
        return productId;
    }

    public int getNewQuantity() {
        return newQuantity;
    }
}
