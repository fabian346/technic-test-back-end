package com.technicTestBackEnd.api.services.buy;

import com.technicTestBackEnd.api.model.responses.buy.BuyResponse;

public interface IBuyService {

    public BuyResponse buy(Long productId, Integer amount);
}
