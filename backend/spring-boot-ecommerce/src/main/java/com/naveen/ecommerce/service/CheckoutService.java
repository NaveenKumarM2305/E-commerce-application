package com.naveen.ecommerce.service;

import com.naveen.ecommerce.dto.Purchase;
import com.naveen.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);
}
