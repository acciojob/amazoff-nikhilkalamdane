package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public void addOrder(Order order){
        orderRepository.addOrder(order);
    }

    public void addDeliveryPartner(String partnerId){
        orderRepository.addDeliveryPartner(partnerId);
    }

    public Order getOrderById(String id){
        return orderRepository.getOrderById(id);
    }

    public DeliveryPartner getPartnerById(String id){
        return orderRepository.getPartnerById(id);
    }

    public Integer getOrderCountByPartnerId(String id){
        return orderRepository.getOrderCountByPartnerId(id);
    }

    public List<String> getAllOrders(){
        return orderRepository.getAllOrders();
    }
}
