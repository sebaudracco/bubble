package com.mopub.mobileads.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlUtils {

    public interface NodeProcessor<T> {
        T process(Node node);
    }

    static class C36931 implements NodeProcessor<String> {
        C36931() {
        }

        public String process(Node node) {
            return XmlUtils.getNodeValue(node);
        }
    }

    static class C36942 implements NodeProcessor<String> {
        C36942() {
        }

        public String process(Node node) {
            return XmlUtils.getNodeValue(node);
        }
    }

    static class C36953 implements NodeProcessor<Node> {
        C36953() {
        }

        public Node process(Node node) {
            return node;
        }
    }

    private XmlUtils() {
    }

    public static Node getFirstMatchingChildNode(Node node, String nodeName) {
        return getFirstMatchingChildNode(node, nodeName, null, null);
    }

    public static Node getFirstMatchingChildNode(Node node, String nodeName, String attributeName, List<String> attributeValues) {
        if (node == null || nodeName == null) {
            return null;
        }
        List<Node> nodes = getMatchingChildNodes(node, nodeName, attributeName, attributeValues);
        if (nodes == null || nodes.isEmpty()) {
            return null;
        }
        return (Node) nodes.get(0);
    }

    public static List<Node> getMatchingChildNodes(Node node, String nodeName) {
        return getMatchingChildNodes(node, nodeName, null, null);
    }

    public static List<Node> getMatchingChildNodes(Node node, String nodeName, String attributeName, List<String> attributeValues) {
        if (node == null || nodeName == null) {
            return null;
        }
        List<Node> nodes = new ArrayList();
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);
            if (childNode.getNodeName().equals(nodeName) && nodeMatchesAttributeFilter(childNode, attributeName, attributeValues)) {
                nodes.add(childNode);
            }
        }
        return nodes;
    }

    public static boolean nodeMatchesAttributeFilter(Node node, String attributeName, List<String> attributeValues) {
        if (attributeName == null || attributeValues == null) {
            return true;
        }
        NamedNodeMap attrMap = node.getAttributes();
        if (attrMap != null) {
            Node attrNode = attrMap.getNamedItem(attributeName);
            if (attrNode != null && attributeValues.contains(attrNode.getNodeValue())) {
                return true;
            }
        }
        return false;
    }

    public static String getNodeValue(Node node) {
        if (node == null || node.getFirstChild() == null || node.getFirstChild().getNodeValue() == null) {
            return null;
        }
        return node.getFirstChild().getNodeValue().trim();
    }

    public static Integer getAttributeValueAsInt(Node node, String attributeName) {
        Integer num = null;
        if (!(node == null || attributeName == null)) {
            try {
                num = Integer.valueOf(Integer.parseInt(getAttributeValue(node, attributeName)));
            } catch (NumberFormatException e) {
            }
        }
        return num;
    }

    public static String getAttributeValue(Node node, String attributeName) {
        if (node == null || attributeName == null) {
            return null;
        }
        Node attrNode = node.getAttributes().getNamedItem(attributeName);
        if (attrNode != null) {
            return attrNode.getNodeValue();
        }
        return null;
    }

    public static <T> List<T> getListFromDocument(Document vastDoc, String elementName, String attributeName, String attributeValue, NodeProcessor<T> nodeProcessor) {
        ArrayList<T> results = new ArrayList();
        if (vastDoc != null) {
            NodeList nodes = vastDoc.getElementsByTagName(elementName);
            if (nodes != null) {
                List<String> attributeValues;
                if (attributeValue == null) {
                    attributeValues = null;
                } else {
                    attributeValues = Arrays.asList(new String[]{attributeValue});
                }
                for (int i = 0; i < nodes.getLength(); i++) {
                    Node node = nodes.item(i);
                    if (node != null && nodeMatchesAttributeFilter(node, attributeName, attributeValues)) {
                        T processed = nodeProcessor.process(node);
                        if (processed != null) {
                            results.add(processed);
                        }
                    }
                }
            }
        }
        return results;
    }

    public static <T> T getFirstMatchFromDocument(Document vastDoc, String elementName, String attributeName, String attributeValue, NodeProcessor<T> nodeProcessor) {
        if (vastDoc == null) {
            return null;
        }
        NodeList nodes = vastDoc.getElementsByTagName(elementName);
        if (nodes == null) {
            return null;
        }
        List<String> attributeValues;
        if (attributeValue == null) {
            attributeValues = null;
        } else {
            attributeValues = Arrays.asList(new String[]{attributeValue});
        }
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node != null && nodeMatchesAttributeFilter(node, attributeName, attributeValues)) {
                T processed = nodeProcessor.process(node);
                if (processed != null) {
                    return processed;
                }
            }
        }
        return null;
    }

    public static String getFirstMatchingStringData(Document vastDoc, String elementName) {
        return getFirstMatchingStringData(vastDoc, elementName, null, null);
    }

    public static String getFirstMatchingStringData(Document vastDoc, String elementName, String attributeName, String attributeValue) {
        return (String) getFirstMatchFromDocument(vastDoc, elementName, attributeName, attributeValue, new C36931());
    }

    public static List<String> getStringDataAsList(Document vastDoc, String elementName) {
        return getStringDataAsList(vastDoc, elementName, null, null);
    }

    public static List<String> getStringDataAsList(Document vastDoc, String elementName, String attributeName, String attributeValue) {
        return getListFromDocument(vastDoc, elementName, attributeName, attributeValue, new C36942());
    }

    public static List<Node> getNodesWithElementAndAttribute(Document vastDoc, String elementName, String attributeName, String attributeValue) {
        return getListFromDocument(vastDoc, elementName, attributeName, attributeValue, new C36953());
    }
}
