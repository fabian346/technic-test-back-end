package com.technicTestBackEnd.api.repositories.purchasehistory.iml;

import com.technicTestBackEnd.api.model.entities.purchasehistory.PurchaseHistory;
import com.technicTestBackEnd.api.repositories.purchasehistory.IPurchaseHistoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PurchaseHistoryRepoImpl implements IPurchaseHistoryRepoCustom{

    private final IPurchaseHistoryRepository purchaseHistoryRepository;

    public PurchaseHistoryRepoImpl(IPurchaseHistoryRepository purchaseHistoryRepository) {
        this.purchaseHistoryRepository = purchaseHistoryRepository;
    }

    @Override
    public List<PurchaseHistory> findByProductId(Long productId) {
        return purchaseHistoryRepository.findAllById(productId);
    }

    @Override
    public void savePurchaseHistory(PurchaseHistory purchaseHistory) {
        purchaseHistoryRepository.save(purchaseHistory);
    }
}
