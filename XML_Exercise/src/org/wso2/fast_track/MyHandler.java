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

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

class MyHandler extends DefaultHandler {
	// SAX callback implementations from DocumentHandler, ErrorHandler, etc.

	private Writer out;

	MyHandler() throws SAXException {
		try {
			out = new OutputStreamWriter(System.out, "UTF8");
		} catch (IOException e) {
			throw new SAXException("I/O Error Occured", e);
		}
	}

	public void startDocument() throws SAXException {
		writeToConsole("<?xml version=\"1.0\"?>\n");
	}

	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		 writeToConsole(qName +" - ");
		 if (atts != null) {
			 for (int i=0, len = atts.getLength(); i<len; i++) {
				 writeToConsole(" " + atts.getQName(i) + "=\"" + atts.getValue(i) + "\"");
			 }
		 }
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		writeToConsole("End "+qName);
	}

	public void characters(char[] ch, int start, int len) throws SAXException {
		writeToConsole(new String(ch, start, len));
	}

	private void writeToConsole(String s) throws SAXException {
		try {
			out.write(s);
			out.flush();
		} catch (IOException e) {
			throw new SAXException("IO Error Occurred.", e);
		}
	}
}