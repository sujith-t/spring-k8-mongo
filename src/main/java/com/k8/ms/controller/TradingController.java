package com.k8.ms.controller;

import com.k8.ms.entity.CoinTrade;
import com.k8.ms.repository.CoinTradeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Sujith T
 *
 * <!In God We Trust>
 */
@RestController
@RequestMapping("/trade")
public class TradingController {

    @Autowired
    private CoinTradeDao dao;

    @PostMapping
    public ResponseEntity<CoinTrade> placeOrder(@RequestBody CoinTrade order) {
        dao.save(order);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Void> changeOrder(@PathVariable String orderId, @RequestBody CoinTrade order) {
        Optional<CoinTrade> opt = dao.findById(orderId);
        opt.ifPresent(x -> {
            x.setCurrency(order.getCurrency());
            x.setQuantity(order.getQuantity());
            x.setUnitPrice(order.getUnitPrice());
            dao.save(x);
        });

        return ResponseEntity.ok().build();
    }

    @GetMapping("/currency/{code}")
    public ResponseEntity<List<CoinTrade>> getOrders(@PathVariable String code) {
        List<CoinTrade> orders = dao.findByCurrency(code);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<CoinTrade> getOrder(@PathVariable String orderId) {
        Optional<CoinTrade> opt = dao.findById(orderId);
        return ResponseEntity.ok(opt.orElse(null));
    }

    @GetMapping("/health")
    public ResponseEntity<String> checkHealth() {
        return ResponseEntity.ok("v1.2");
    }
}
