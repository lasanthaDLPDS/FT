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

import org.w3c.dom.Document;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

public class XmlValidator {
	
	 public static void main(String[] args) {
	        try {

	            // Get Document Builder Factory
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            factory.setValidating(false);
	            factory.setNamespaceAware(false);

	            DocumentBuilder builder = factory.newDocumentBuilder();
	            Document doc = builder.parse(new File("src/resources/Orders.xml"));

	            // Handle validation
	            SchemaFactory constraintFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	            Source constraints = new StreamSource(new File("src/resources/Orders.xsd"));
	            Schema schema = constraintFactory.newSchema(constraints);
	            Validator validator = schema.newValidator();

	            // Validate the DOM tree
	            try {
	                validator.validate(new DOMSource(doc));
	                System.out.println("Document validates fine.");
	            } catch (org.xml.sax.SAXException e) {
	                System.out.println("Validation error: " + e.getMessage());
	            }

	        } catch (ParserConfigurationException e) {
	            System.out.println("The underlying parser does not support the requested features.");
	        } catch (FactoryConfigurationError e) {
	            System.out.println("Error occurred obtaining Document Builder Factory.");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}