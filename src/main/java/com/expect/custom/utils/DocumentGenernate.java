package com.expect.custom.utils;

import java.util.Map;
import java.util.Set;

public class DocumentGenernate {

	private StringBuilder htmlSb = new StringBuilder();

	public String getHtml() {
		return htmlSb.toString();
	}

	public void createText(final CharSequence text) {
		htmlSb.append(text);
	}

	public void createOpenElementTag(final String elementName) {
		htmlSb.append("<" + elementName + ">");
	}

	public void createOpenElementTag(final String elementName, final String attributeName,
			final String attributeValue) {
		htmlSb.append("<" + elementName + " " + attributeName + "='" + attributeValue + "'>");
	}

	public void createOpenElementTag(final String elementName, final Map<String, String> attributes) {
		StringBuilder attributeStr = new StringBuilder();
		if (attributes != null) {
			Set<String> keys = attributes.keySet();
			for (String key : keys) {
				String value = attributes.get(key);
				attributeStr.append(" " + key + "='" + value + "'");
			}
		}
		htmlSb.append("<" + elementName + attributeStr.toString() + ">");
	}

	public void createCloseElementTag(final String elementName) {
		htmlSb.append("</" + elementName + ">");
	}

	public void createSingleElementTag(final String elementName) {
		htmlSb.append("<" + elementName + "/>");
	}

	public void createSingleElementTag(final String elementName, final String attributeName,
			final String attributeValue) {
		htmlSb.append("<" + elementName + " " + attributeName + "='" + attributeValue + "' />");
	}

	public void createSingleElementTag(final String elementName, final Map<String, String> attributes) {
		StringBuilder attributeStr = new StringBuilder();
		if (attributes != null) {
			Set<String> keys = attributes.keySet();
			for (String key : keys) {
				String value = attributes.get(key);
				attributeStr.append(" " + key + "='" + value + "'");
			}
		}
		htmlSb.append("<" + elementName + attributeStr.toString() + "/>");
	}
}
