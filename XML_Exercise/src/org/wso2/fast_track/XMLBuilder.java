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

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.OutputStreamWriter;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class XMLBuilder {

    public XMLBuilder() {

    }

    public static void main(String argv[]) {

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("catalog");
            doc.appendChild(rootElement);

            // book elements
            Element book = doc.createElement("book");
            rootElement.appendChild(book);

            // set attribute to book element
            Attr attr = doc.createAttribute("id");
            attr.setValue("1");
            book.setAttributeNode(attr);

            // author elements
            Element author = doc.createElement("author");
            author.appendChild(doc.createTextNode("Mr. DLPDS"));
            book.appendChild(author);

            // title elements
            Element title = doc.createElement("title");
            title.appendChild(doc.createTextNode("XML Exercise"));
            book.appendChild(title);

            // price elements
            Element price = doc.createElement("price");
            price.appendChild(doc.createTextNode("100.00"));
            book.appendChild(price);

            // publish_date elements
            Element publishDate = doc.createElement("publish_date");
            publishDate.appendChild(doc.createTextNode("2017-02-01"));
            book.appendChild(publishDate);

            // description elements
            Element description = doc.createElement("description");
            description.appendChild(doc.createTextNode("Creating XML Description Example"));
            book.appendChild(description);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            //indent XML properly
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("SampleOutput.xml"));
            transformer.transform(source, result);

            // write result to console
            StreamResult res = new StreamResult(new OutputStreamWriter(System.out));
            transformer.transform(source, res);

            LOGGER.info("File Saved!");

        } catch (ParserConfigurationException e) {
            LOGGER.info("Parser Error " + e.getMessage());

        } catch (TransformerException e) {
            LOGGER.info("Transformer Error " + e.getMessage());

        }
    }
}