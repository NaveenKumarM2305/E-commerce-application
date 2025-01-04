package com.naveen.ecommerce.dto;

import com.naveen.ecommerce.entity.Address;
import com.naveen.ecommerce.entity.Customer;
import com.naveen.ecommerce.entity.Order;
import com.naveen.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;

    private Address billingAddress;

    private Address shippingAddress;

    private Order order;

    private Set<OrderItem> orderItems;


}
