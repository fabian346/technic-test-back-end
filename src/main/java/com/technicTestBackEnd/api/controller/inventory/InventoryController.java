package com.technicTestBackEnd.api.controller.inventory;

import com.technicTestBackEnd.api.model.responses.inventory.InventoryResponse;
import com.technicTestBackEnd.api.services.inventory.IInventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/inventory")
@CrossOrigin(origins = "*")
public class InventoryController {

    private final IInventoryService inventoryService;

    public InventoryController(IInventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }


    @GetMapping("/{productId}")
    public ResponseEntity<InventoryResponse> getInventory(@PathVariable Long productId) {
        return ResponseEntity.ok(inventoryService.getInventoryByProductId(productId));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Void> updateQuantity(@PathVariable Long productId, @RequestParam Integer quantity) {
        inventoryService.updateQuantity(productId, quantity);
        return ResponseEntity.noContent().build();
    }
}
