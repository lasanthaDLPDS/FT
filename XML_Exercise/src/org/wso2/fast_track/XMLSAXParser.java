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

package org.wso2.fast_track;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

//Class for parsing given XML file by using SAX parser
public class XMLSAXParser {
	public static void main(String[] args) {
		try {
			// Get SAX Parser Factory Instance
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setValidating(true);
			factory.setNamespaceAware(true);
			SAXParser parser = factory.newSAXParser();
			// get the xml file
			File input = new File("src/resources/Orders.xml");
			parser.parse(input, new MyHandler());

		} catch (ParserConfigurationException e) {
			System.out.println("Error occurred in SAX parse");

		} catch (FactoryConfigurationError e) {
			System.out.println("Error occurred obtaining SAX Parser Factory");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}