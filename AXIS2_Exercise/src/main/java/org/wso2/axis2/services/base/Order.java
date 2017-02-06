/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.axis2.services.base;

import java.util.HashMap;

public class Order {

    private int orderId;
    private HashMap<String,Integer> orderList;
    private HashMap<Product, Integer> stockList;
    private double totalPrice=0.0;
    private Stock stock;

    public Order(int orderId){
        setOrderId(orderId);
        orderList = new HashMap<>();
        stock= new Stock();
        stockList=stock.getStockList();
    }

    private void setOrderId(int orderId) {
        this.orderId = orderId;
    }


    public int getOrderId() {
        return orderId;
    }

    public HashMap<String, Integer> getOrderList() {
        return orderList;
    }

    public void addItems(String productId, int qty){
        this.orderList.put(productId,qty);
        for (Product product : stockList.keySet()){
            if (product.getProductId().equals(productId)){
                stock.updateStock();
            }

        }
    }

    public double getTotalPrice() {
        if (orderList.size()>0 && totalPrice==0.0){
            calculateOrderPrice();
        }
        return totalPrice;
    }

    private double calculateOrderPrice(){
        for (String key : orderList.keySet()) {
            for (Product product : stockList.keySet()){
                if (product.getProductId().equals(key)){
                    totalPrice+=orderList.get(key)*product.getPrice();
                }

            }

        }
        return totalPrice;
    }
}
