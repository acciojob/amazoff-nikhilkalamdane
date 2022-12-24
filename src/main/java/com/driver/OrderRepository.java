package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderRepository {
    List<Order> orderList = new ArrayList<>();
    List<DeliveryPartner> deliveryPartnerList = new ArrayList<>();
    Set<String> assignOrders = new HashSet<>();
    Map<String, HashSet<Order>> orderDeliveryPartnerPair = new HashMap<>();

    public void addOrder(Order order){
        orderList.add(order);
    }

    public void addDeliveryPartner(String partnerId){
        DeliveryPartner deliveryPartner = new DeliveryPartner(partnerId);
        deliveryPartnerList.add(deliveryPartner);
    }

    public void addOrderPartnerPair(String orderId, String partnerId){

        for(DeliveryPartner partner: deliveryPartnerList){
            if(partnerId.equals(partner.getId())){
                int orderCount = partner.getNumberOfOrders();
                orderCount++;
                partner.setNumberOfOrders(orderCount);
            }
        }

        assignOrders.add(orderId);

        HashSet<Order> orderHashSet = orderDeliveryPartnerPair.getOrDefault(partnerId, new HashSet<>());

        for(Order o: orderList){
            if(orderId.equals(o.getId())){
                orderHashSet.add(o);
            }
        }

        orderDeliveryPartnerPair.put(partnerId, orderHashSet);
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

    public List<String> getOrdersByPartnerById(String partnerId){
        List<String> list = new ArrayList<>();
        Set<Order> set = new HashSet<>();

        for(Map.Entry<String, HashSet<Order>> map: orderDeliveryPartnerPair.entrySet()){
            if(map.getKey().equals(partnerId)){
                set = map.getValue();
                break;
            }
        }

        for(Order order: set){
            list.add(order.getId());
        }

        return list;
    }

    public List<String> getAllOrders(){
        List<String> list = new ArrayList<>();
        for(Order order: orderList){
            list.add(order.getId());
        }

        return list;
    }

    public Integer getCountOfUnassignedOrders(){
        return orderList.size() - assignOrders.size();
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String id){
        int count = 0;
        int h = Integer.parseInt(time.substring(0,2));
        int m = Integer.parseInt(time.substring(3, time.length()));
        int givenTime = (h * 60) + m;

        for(Map.Entry<String, HashSet<Order>> map: orderDeliveryPartnerPair.entrySet()){
            if(id.equals(map.getKey())){
                for(Order order: map.getValue()){
                    if(order.getDeliveryTime() >= givenTime){
                        return map.getValue().size() - count++;
                    }else{
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public String getLastDeliveryTimeByPartnerId(String id){
        int time = 0;

        for(Map.Entry<String, HashSet<Order>> map: orderDeliveryPartnerPair.entrySet()){
            if(map.getKey().equals(id)){
                for(Order order: map.getValue()){
                    time = order.getDeliveryTime();
                }
                break;
            }
        }

        return String.valueOf(time / 60) + ":" + String.valueOf(time % 60);
    }

    public void deletePartnerById(String id){
        for(DeliveryPartner partner: deliveryPartnerList){
            if(id.equals(partner.getId())){
                deliveryPartnerList.remove(partner);
                orderDeliveryPartnerPair.remove(id);
                return;
            }
        }
    }

    public void deleteByOrderId(String id){
        for(Order order: orderList){
            if(id.equals(order.getId())){
                orderList.remove(order);
                break;
            }
        }

        for(Map.Entry<String, HashSet<Order>> map: orderDeliveryPartnerPair.entrySet()){
            map.getValue().removeIf(order -> order.getId().equals(id));
        }
    }
}
