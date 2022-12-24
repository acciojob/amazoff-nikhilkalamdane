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

    public void addOrderPartnerPair(String orderId, String partnerId){
        orderRepository.addOrderPartnerPair(orderId, partnerId);
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

    public List<String> getOrdersByPartnerId(String partnerId) {
        return orderRepository.getOrdersByPartnerById(partnerId);
    }

    public List<String> getAllOrders(){
        return orderRepository.getAllOrders();
    }

    public Integer getCountOfUnassignedOrders(){
        return orderRepository.getCountOfUnassignedOrders();
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(time, partnerId);
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        return orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
    }

    public void deletePartnerById(String id) {
        orderRepository.deletePartnerById(id);
    }

    public void deleteByOrderId(String id) {
        orderRepository.deleteByOrderId(id);
    }
}
