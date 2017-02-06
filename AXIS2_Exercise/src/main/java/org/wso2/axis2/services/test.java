///*
// * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package org.wso2.axis2.services;
//
///**
// * Created by lasantha on 2/5/17.
// */
//public class test {
//
//
//    private int customerCount = 0;
//    private int orderCount = 0;
//    private Stock currentStock;
//    private HashMap<Integer, Customer> currentCustomers;
//    private HashMap<Integer, Order> currentOrders;
//
//    /**
//     * Initialize all relevant objects for the service
//     */
//    public test() {
//        init();
//    }
//
//    /**
//     * This method is used for initializing the relevant objects
//     */
//    private void init() {
//        currentStock = Stock.getInstance();
//        currentCustomers = new HashMap<Integer, Customer>();
//        currentCustomers.put(++customerCount, new Customer(customerCount, "Milan"));
//        currentOrders = new HashMap<Integer, Order>();
//    }
//
//    /**
//     * @param name desired name of the customer
//     * @return customer id
//     */
//    public int addCustomer(String name) {
//        int tempCustomerId = ++customerCount;
//        currentCustomers.put(tempCustomerId, new Customer(tempCustomerId, name));
//        return tempCustomerId;
//    }
//
//    /**
//     * @param customerName name of the customer who places an order
//     * @return order id
//     */
//    public int placeOrder(int customerId) {
//        int tempOrderId = ++orderCount;
//        currentOrders.put(tempOrderId, new Order(tempOrderId, customerId));
//        return tempOrderId;
//    }
//
//    /**
//     * @param orderId oder id of the customer
//     * @param itemId  item no of desired item
//     * @param qty     purchasing quantity
//     */
//    public int addItemToOrder(int orderId, int itemId, int qty) {
//        // check current stock
//        int currentValue = currentStock.reduceItem(itemId, qty);
//        currentOrders.get(orderId).addItemtoOrder(itemId, qty);
//        return currentValue;
//    }
//
//    /**
//     * This method is used to get information of current stock details
//     *
//     * @return string current stock state
//     */
//    public String getCurrentStockStatus() {
//        return currentStock.viewStock();
//    }
//
//    /**
//     * This method is used to get items list that are currently available
//     *
//     * @return string of item list
//     */
//    public String getCurrentItemList() {
//        return currentStock.viewItemList();
//    }
//
//    /**
//     * This method is used to get the ordered items of the customer
//     *
//     * @param orderId of the required order
//     * @return string of item list of the oder
//     */
//    public String getOrderedList(int orderId) {
//        Order tempOrder = currentOrders.get(orderId);
//        return tempOrder.viewOrderList();
//    }
//}
