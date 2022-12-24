package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderRepository {
    List<Order> orderList = new ArrayList<>();
    List<DeliveryPartner> deliveryPartnerList = new ArrayList<>();

    Map<String, HashSet<Order>> orderDeliveryPartnerPair = new HashMap<>();

    public void addOrder(Order order){
        orderList.add(order);
    }

    public void addDeliveryPartner(String partnerId){
        DeliveryPartner deliveryPartner = new DeliveryPartner(partnerId);
        deliveryPartnerList.add(deliveryPartner);
    }

    public Order getOrderById(String id){
        for(Order order: orderList){
            if(id.equals(order.getId())){
                return order;
            }
        }
        return null;
    }

    public DeliveryPartner getPartnerById(String id){
        for(DeliveryPartner deliveryPartner: deliveryPartnerList){
            if(id.equals(deliveryPartner.getId())){
                return deliveryPartner;
            }
        }
        return null;
    }

    public Integer getOrderCountByPartnerId(String id){

        for(DeliveryPartner partner: deliveryPartnerList){
            if(id.equals(partner.getId())){
                return partner.getNumberOfOrders();
            }
        }
        return null;
    }

    public List<String> getAllOrders(){
        List<String> list = new ArrayList<>();
        for(Order order: orderList){
            list.add(order.getId());
        }

        return list;
    }
}
