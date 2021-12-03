package app.test.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;

@JacksonXmlRootElement(localName = "response")
public class Response {
    @JacksonXmlProperty(localName = "result-code")
    private String resultCode;

    @JacksonXmlProperty(localName = "extra")
    @JacksonXmlElementWrapper(localName = "extra", useWrapping = false)
    private List<ExtraTag> extraTags;

    public List<ExtraTag> getExtraTags() {
        return extraTags;
    }

    public void setExtraTags(List<ExtraTag> extraTags) {
        this.extraTags = extraTags;
    }



    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
}
