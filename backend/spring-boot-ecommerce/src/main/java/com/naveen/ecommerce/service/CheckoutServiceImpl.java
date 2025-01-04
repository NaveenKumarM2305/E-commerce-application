package com.naveen.ecommerce.service;

import com.naveen.ecommerce.dao.CustomerRepository;
import com.naveen.ecommerce.dto.Purchase;
import com.naveen.ecommerce.dto.PurchaseResponse;
import com.naveen.ecommerce.entity.Customer;
import com.naveen.ecommerce.entity.Order;
import com.naveen.ecommerce.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private CustomerRepository  customerRepository;

    @Autowired
    CheckoutServiceImpl(CustomerRepository customerRepository){
        this.customerRepository= customerRepository;
    }

    @Override
    public PurchaseResponse placeOrder(Purchase purchase) {
        // retrieve the order from dto
        Order order = purchase.getOrder();

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        //populate order with order items
        Set<OrderItem> orderItmes = purchase.getOrderItems();
        orderItmes.forEach(item -> order.add(item));

        //populate order with billin address and shipping address
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());


        //populate customer with order
        Customer customer = purchase.getCustomer();
        customer.add(order);



        //save to the database
        customerRepository.save(customer);

        // return the response
        return new PurchaseResponse(orderTrackingNumber);

    }



    private String generateOrderTrackingNumber(){
        return UUID.randomUUID().toString();
    }
}
