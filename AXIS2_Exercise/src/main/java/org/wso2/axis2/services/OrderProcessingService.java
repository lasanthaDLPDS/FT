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

package org.wso2.axis2.services;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.wso2.axis2.services.base.Order;

import java.util.HashMap;


public class OrderProcessingService {

    private final String namespace = "http://stream.xml.xmlbeans.apache.org/xsd";

    private int orderCount;
    private int orderId;
    private HashMap<Integer, Order> currentOrders;

    public OrderProcessingService(){
        this.currentOrders= new HashMap<>();
        this.orderCount=0;
        this.orderId=0;
    }

    public OMElement createOrder(OMElement element) {
        element.build();
        element.detach();
        orderId++;
        Order newOrder=new Order(orderId);

        OMElement orderItem = (OMElement) element.getFirstElement();
        OMElement itemElement = (OMElement) orderItem.getFirstOMChild();
        OMElement quantityElement = (OMElement) itemElement.getNextOMSibling();
        newOrder.addItems(itemElement.getText(), Integer.parseInt(quantityElement.getText()));

        while ((orderItem = (OMElement) orderItem.getNextOMSibling()) != null) {
            itemElement = (OMElement) orderItem.getFirstOMChild();
            quantityElement = (OMElement) itemElement.getNextOMSibling();
            newOrder.addItems(itemElement.getText(), Integer.parseInt(quantityElement.getText()));
        }

        orderCount++;
        currentOrders.put(orderId,newOrder);

        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace(namespace, "ns");
        OMElement method = fac.createOMElement("createOrderResponse", omNs);
        OMElement orderId = fac.createOMElement("orderId", omNs);
        orderId.addChild(fac.createOMText(orderId, Integer.toString(newOrder.getOrderId())));
        OMElement price = fac.createOMElement("totalPrice", omNs);
        price.addChild(fac.createOMText(price, Double.toString(newOrder.getTotalPrice())));
        method.addChild(orderId);
        method.addChild(price);

        return method;
    }


    public OMElement getOrderDetails(OMElement element) {
        element.build();
        element.detach();

        OMElement orderIdElement = element.getFirstElement();
        String orderId = orderIdElement.getText();


        Order orderDetails = currentOrders.get(Integer.parseInt(orderId));
        HashMap<String,Integer> orderList = orderDetails.getOrderList();

        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace(namespace, "ns");
        OMElement method = fac.createOMElement("getOrderDetails", omNs);

        if (orderList != null) {
            for (String key : orderList.keySet()) {
                OMElement item = fac.createOMElement("item", omNs);

                OMElement itemId = fac.createOMElement("itemId", omNs);
                itemId.addChild(fac.createOMText(itemId, key));
                item.addChild(itemId);

                OMElement quantity = fac.createOMElement("quantity", omNs);
                quantity.addChild(fac.createOMText(quantity, Integer.toString(orderList.get(key))));
                item.addChild(quantity);

                method.addChild(item);
            }
        }

        return method;
    }

    public OMElement ping(OMElement element) throws XMLStreamException {

        element.build();
        element.detach();

        String returnText = Integer.toString(orderCount+100);
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace(namespace, "ns");
        OMElement method = fac.createOMElement("getPriceResponse", omNs);
        OMElement value = fac.createOMElement("return", omNs);
        value.addChild(fac.createOMText(value, returnText));
        method.addChild(value);
        return method;
    }

    public OMElement getNumOrders(OMElement element) throws XMLStreamException {

        Integer getNumOrders = 10;

        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace(namespace, "ns");
        OMElement orders = fac.createOMElement("getNumOrders", omNs);
        OMElement value = fac.createOMElement("return", omNs);
        value.addChild(fac.createOMText(value, getNumOrders.toString()));
        orders.addChild(value);
        return orders;
    }
}

