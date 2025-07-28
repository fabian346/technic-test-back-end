package com.technicTestBackEnd.api.model.responses.buy;

public class BuyResponse {

    private Long productId;
    private Integer amountBuy;
    private String message;

    public BuyResponse(Long productId, String message, Integer amountBuy) {
        this.productId = productId;
        this.message = message;
        this.amountBuy = amountBuy;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getAmountBuy() {
        return amountBuy;
    }

    public void setAmountBuy(Integer amountBuy) {
        this.amountBuy = amountBuy;
    }
}
