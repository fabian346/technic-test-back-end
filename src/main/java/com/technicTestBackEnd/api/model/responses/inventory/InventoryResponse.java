package com.technicTestBackEnd.api.model.responses.inventory;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class InventoryResponse {

    private Long productId;
    private Integer amount;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
