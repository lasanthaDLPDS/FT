package org.wso2.axis2;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

import java.util.HashMap;

public class Client {


    private static EndpointReference targetEPR = new EndpointReference(
            "http://localhost:8080/axis2/services/OrderProcessingService");
    private static final String namespace = "http://stream.xml.xmlbeans.apache.org/xsd";

    public static void main(String[] args) {
        try {
            Options options = new Options();
            options.setTo(targetEPR);
            options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

            ServiceClient sender;
            sender = new ServiceClient();
            sender.setOptions(options);

            String orderId = createOrder(sender);
            OMElement orderResult = getOrderDetails(sender, orderId);

            System.out.print("Order id - ");
            System.out.println(orderId);
            System.out.println("Order items");
            printOrder(orderResult);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static OMElement createOrderPayload(HashMap<String, Integer> order) {
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace(namespace, "ns");
        OMElement method = fac.createOMElement("createOrder", omNs);

        for (String key : order.keySet()) {
            OMElement item = fac.createOMElement("item", omNs);

            OMElement itemId = fac.createOMElement("itemId", omNs);
            itemId.addChild(fac.createOMText(itemId, key));
            item.addChild(itemId);

            OMElement quantity = fac.createOMElement("quantity", omNs);
            quantity.addChild(fac.createOMText(quantity, "" + order.get(key)));
            item.addChild(quantity);

            method.addChild(item);
        }

        return method;
    }

    private static OMElement getOrderDetailsPayload(String id) {
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace(namespace, "ns");

        OMElement method = fac.createOMElement("getOrderDetails", omNs);
        OMElement orderId = fac.createOMElement("orderId", omNs);
        orderId.addChild(fac.createOMText(orderId, id));
        method.addChild(orderId);
        return method;
    }

    private static String createOrder(ServiceClient sender) {
        HashMap<String, Integer> order = new HashMap<>();
        order.put("RASPBERRY PI 2", 1);
        order.put("ARDUINO UNO", 2);
        order.put("BEAGLEBONE BLACK", 3);
        order.put("WUNDERBAR", 4);
        order.put("IOT DEVELOPMENT PLATFORM", 6);

        String orderId = "";

        OMElement orderPayload = createOrderPayload(order);
        OMElement result;
        try {

            result = sender.sendReceive(orderPayload);

            result.build();
            result.detach();

            OMElement responseItem = (OMElement) result.getFirstElement();
            OMElement priceElement ;
            orderId = responseItem.getText();

            priceElement = (OMElement) responseItem.getNextOMSibling();

            System.out.println("Total price for order id "+orderId+" : "+priceElement.getText());
        } catch (AxisFault e) {
            e.printStackTrace();
        }

        return orderId;

    }

    private static OMElement getOrderDetails(ServiceClient sender, String orderId) {
        OMElement orderDetails = getOrderDetailsPayload(orderId);
        OMElement orderResult;
        try {
            orderResult = sender.sendReceive(orderDetails);
        } catch (AxisFault e) {
            OMFactory fac = OMAbstractFactory.getOMFactory();
            OMNamespace omNs = fac.createOMNamespace(namespace, "ns");
            orderResult = fac.createOMElement("wrong", omNs);
            orderResult.addChild(fac.createOMText(orderResult, "wrong"));
            e.printStackTrace();
        }

        return orderResult;
    }

    private static void printOrder(OMElement element) {
        element.build();

        if (((OMElement) element.getFirstElement()) != null) {

            element.detach();

            OMElement orderItem = (OMElement) element.getFirstElement();
            OMElement itemElement;
            OMElement quantityElement;

            do {
                itemElement = (OMElement) orderItem.getFirstOMChild();
                quantityElement = (OMElement) itemElement.getNextOMSibling();
                System.out.println(itemElement.getText() + " - "
                        + Integer.parseInt(quantityElement.getText()));
            } while ((orderItem = (OMElement) orderItem.getNextOMSibling()) != null);
        } else {
            System.out.println("No such order");
        }

    }

}