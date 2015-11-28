package com.epam.xmlRegex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Belarus on 25.11.2015.
 */
public class XMLDocument extends Node{

    private static final String SEARCH_STRING = "([a-zA-Z]{1}[a-zA-Z\\d\\-]*)[\\s]*([.[^>]]*)>*([.[^<]]*)<*/*([a-zA-Z\\d\\-]*)";
    private static final String SPLIT_STRING = ">[\\s]*<";
    private static final String ATTRIBUTE_STRING = "([a-zA-Z]{1}[a-zA-Z\\d\\-]*)\\s*={1}\\s*\"([.[^\"]])\"";

    public XMLDocument(String fileName) {
        super(fileName);
        fillFromFile(fileName);
    }

    public void fillFromFile(String fileName){

        String data = readFile(fileName);
        String[] nodeData = splitXMLString(data);
        parseData(nodeData,0,this);

    }

    private String readFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
        } catch (IOException fe) {
            System.out.println(fe.getMessage());
        }
        return sb.toString().trim();
    }

    private String[] splitXMLString(String xmlString){

        Pattern splitPattern = Pattern.compile(SPLIT_STRING);
        return splitPattern.split(xmlString);

    }

    private int parseData(String[] nodeData,int index, Node upperNode){
        Pattern searchPattern = Pattern.compile(SEARCH_STRING);

        while (index<nodeData.length-1) {
            if(nodeData[index].startsWith("<?xml")) {index ++;continue;}
            Matcher m = searchPattern.matcher(nodeData[index]);
            if (m.find()) {

                String nodeName = m.group(1);
                String nodeAttributes = m.group(2);
                String nodeValue = m.group(3);
                String nodeEnd = m.group(4);

                List<Attribute> attributes = getAttributes(nodeAttributes);

                if (nodeData[index].trim().equals("/" + nodeName)) {
                    return index;
                } else if (nodeName.equals(nodeEnd)) {
                    upperNode.addElement(new Element(nodeName, nodeValue, upperNode.getTab(), attributes));
                } else if ("".equals(nodeEnd))
                {
                    Node newNode = new Element(nodeName,"", upperNode.getTab(),attributes);
                    index = parseData(nodeData, index + 1, newNode);
                    upperNode.addElement(newNode);
                }
            }
            index ++;
        }
        return index;

    }

    private static List<Attribute> getAttributes(String nodeAttributes) {

        if("".equals(nodeAttributes)){
            return null;
        }
        List<Attribute> result = new LinkedList<>();

        Pattern pattern= Pattern.compile("([a-zA-Z]{1}[a-z|A-Z|\\d|\\-]+)=\"(.+[^\"])");
        String[] attributes = Pattern.compile("\"[\\s]+").split(nodeAttributes);
        for (String at: attributes){
            Matcher m = pattern.matcher(at);
            if(m.find()){
                result.add(new Attribute(m.group(1),m.group(2)));
            }
        }
//        Matcher m = pattern1.matcher(nodeAttributes);
//        int index = 0;
//
//        while (m.find(index)){
//
//        }
        return result;
    }

}