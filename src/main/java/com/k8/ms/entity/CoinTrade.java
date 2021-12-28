package com.k8.ms.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

/**
 * @author Sujith T
 *
 * <!In God We Trust>
 */
@Document(collection = "coin_trade")
public class CoinTrade {

    @Id
    private String id;

    @Indexed
    private String client;

    @Indexed
    private TradeType tradeType;

    @Indexed
    private String currency;

    private double unitPrice;
    private int quantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public enum TradeType {
        BUY,
        SELL,
        TRANSFER;

        private TradeType() {

        }
    }
}
