package com.epam.xmlRegex;

/**
 * Created by Belarus on 25.11.2015.
 */
public class Attribute {
    private String name;
    private String value;
    public Attribute(String name, String value) {
        this.name  = name;
        this.value = value;

    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
