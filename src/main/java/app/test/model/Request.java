package app.test.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;

@JacksonXmlRootElement(localName = "request")
public class Request {
    @JacksonXmlProperty(localName = "request-type")
    private String requestType;
    @JacksonXmlProperty(localName = "extra")
    @JacksonXmlElementWrapper(localName = "extra", useWrapping = false)
    private List<ExtraTag> extraTags;


    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public List<ExtraTag> getExtraTags() {
        return extraTags;
    }

    public void setExtraTags(List<ExtraTag> extraTags) {
        this.extraTags = extraTags;
    }
}