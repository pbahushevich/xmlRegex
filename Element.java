package com.epam.xmlRegex;

import java.util.List;

/**
 * Created by Belarus on 25.11.2015.
 */
public class Element extends Node {

    public Element(  String name,String value, String tab,List<Attribute> attributes) {
        super(name,value,tab);

        if(attributes!=null){
            for (Attribute at :attributes){
                this.addAttribute(at);
            }
        }
    }


    public Element(String value, String name, String tab) {

        super(name,value,tab);

    }


}
