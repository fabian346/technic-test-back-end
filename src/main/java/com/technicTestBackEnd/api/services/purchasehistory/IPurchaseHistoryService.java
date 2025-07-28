package com.technicTestBackEnd.api.services.purchasehistory;

import com.technicTestBackEnd.api.model.entities.purchasehistory.PurchaseHistory;

import java.util.List;

public interface IPurchaseHistoryService {

    void recordPurchase(Long productId, int quantity);

    List<PurchaseHistory> getHistoryByProductId(Long productId);
}
