package com.technicTestBackEnd.api.repositories.purchasehistory.iml;

import com.technicTestBackEnd.api.model.entities.purchasehistory.PurchaseHistory;

import java.util.List;

public interface IPurchaseHistoryRepoCustom {

    List<PurchaseHistory> findByProductId(Long productId);

    void savePurchaseHistory(PurchaseHistory purchaseHistory);
}
