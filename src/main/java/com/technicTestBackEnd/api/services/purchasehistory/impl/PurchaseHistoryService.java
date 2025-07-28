package com.technicTestBackEnd.api.services.purchasehistory.impl;

import com.technicTestBackEnd.api.model.entities.purchasehistory.PurchaseHistory;
import com.technicTestBackEnd.api.repositories.purchasehistory.iml.IPurchaseHistoryRepoCustom;
import com.technicTestBackEnd.api.services.purchasehistory.IPurchaseHistoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseHistoryService implements IPurchaseHistoryService {

    private final IPurchaseHistoryRepoCustom purchaseHistoryRepo;

    public PurchaseHistoryService(IPurchaseHistoryRepoCustom purchaseHistoryRepo) {
        this.purchaseHistoryRepo = purchaseHistoryRepo;
    }

    @Override
    public void recordPurchase(Long productId, int quantity) {
        PurchaseHistory purchaseHistory = new PurchaseHistory();
        purchaseHistory.setQuantity(quantity);
        purchaseHistory.setPurchaseDate(LocalDateTime.now());
        purchaseHistoryRepo.savePurchaseHistory(purchaseHistory);
    }

    @Override
    public List<PurchaseHistory> getHistoryByProductId(Long productId) {
        return purchaseHistoryRepo.findByProductId(productId);
    }
}
