package com.epam.xmlRegex;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Belarus on 25.11.2015.
 */
public class Node {

    private List<Node> elements;
    private List<Attribute> attributes;
    private String value  = "";
    private String name = "";
    private String tab = "\t";
    public Node(){

    }
    public Node(String name) {
        this.name = name;
    }
    public Node(String name, String value, String tab) {
        this.name = name;
        this.value = value;
        this.tab = tab+"\t";
    }

    public void addElement(Node element){
        if(elements == null){
            elements = new LinkedList<>();
        }
        elements.add(element);
    }

    public void addAttribute(Attribute at){
        if(attributes == null){
            attributes = new LinkedList<>();
        }
        attributes.add(at);
    }

    @Override
    public String toString(){    //better don't look at it :((

        String result = "";

        String attributesString ="";
        if (attributes != null) {
            for (Attribute at : attributes){
                attributesString = attributesString+at.getName()+"="+at.getValue()+";";
            }
            attributesString = " ("+attributesString.substring(0,attributesString.length()-1)+")";
        }

        if(elements==null){
            result = "|"+this.tab+name + " - " + value+attributesString;
            result = result + "\r\n"+attributesString;
        }else {
            result = result+"|" + this.tab+"<"+ name+attributesString+"------------------> \r\n";
            for (Node n: elements){
                result = result+n.toString()+"\r\n";
            }
            result = result + "|"+this.tab+"<------------------"+ name+"> \r\n";
        }
        return result.trim();
    }

    public String getTab(){
        return tab;
    }
}
