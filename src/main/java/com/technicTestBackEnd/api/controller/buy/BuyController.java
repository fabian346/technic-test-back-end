package com.technicTestBackEnd.api.controller.buy;

import com.technicTestBackEnd.api.model.responses.buy.BuyResponse;
import com.technicTestBackEnd.api.services.buy.IBuyService;
import com.technicTestBackEnd.api.services.buy.impl.BuyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/")
@CrossOrigin(origins = "*")
public class BuyController {

    private final IBuyService buyService;

    public BuyController(IBuyService buyService) {
        this.buyService = buyService;
    }

    @PostMapping("/buy")
    public ResponseEntity<BuyResponse> buy(@RequestParam Long productId, @RequestParam int amount){
        BuyResponse buyResponse = buyService.buy(productId, amount);
        return ResponseEntity.ok(buyResponse);
    }
}
