package com.technicTestBackEnd.api.repositories.purchasehistory;

import com.technicTestBackEnd.api.model.entities.purchasehistory.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {

    List<PurchaseHistory> findAllById(Long id);
}
