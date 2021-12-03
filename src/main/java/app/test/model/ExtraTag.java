package app.test.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class ExtraTag {
    @XmlAttribute(name = "name")
    @JacksonXmlProperty(localName = "name", isAttribute = true)
    private String name;

    @XmlValue
    @JacksonXmlText
    private String value;

    public ExtraTag() {

    }
    public ExtraTag(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
