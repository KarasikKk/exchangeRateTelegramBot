package com.demo.exchangeRate.model;

public interface BankRate {
    double getUSDPurchaseRate();
    double getEURPurchaseRate();
    double getUSDSellingRate();
    double getEURSellingRate();
}
