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

public class Stock {

    private HashMap<Product, Integer> stockList;

    public Stock(){
        stockList=new HashMap<>();
        stockList.put(new Product("IOT DEVELOPMENT PLATFORM",2000.00),100);
        stockList.put(new Product("WUNDERBAR",1500.00),200);
        stockList.put(new Product("RASPBERRY PI 2",7000.00),300);
        stockList.put(new Product("BEAGLEBONE BLACK",5000.00),400);
        stockList.put(new Product("ARDUINO UNO",4000.00),500);
    }

    public HashMap<Product, Integer> getStockList() {
        return stockList;
    }

    public void updateStock(Product prd, int qnt){
        

    }
}
