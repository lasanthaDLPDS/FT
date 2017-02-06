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

public class Product {

    private String productId;
    private double price;

    public Product(String productId, double price){
        setProductId(productId);
        setPrice(price);
    }

    private void setProductId(String productId) {
        this.productId = productId;
    }

    private void setPrice(double price) {
        this.price = price;
    }

    public String getProductId() {

        return productId;
    }

    public double getPrice() {
        return price;
    }
}
