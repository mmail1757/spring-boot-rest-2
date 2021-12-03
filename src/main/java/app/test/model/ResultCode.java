package app.test.model;

public enum ResultCode {
    OK("0"),
    USER_ALREADY_EXISTS("1"),
    TECHNICAL_ERROR("2"),
    USER_NOT_FOUND("3"),
    INVALID_PASSWORD("4");

    private String code;

    ResultCode(String code) {
        this.code = code;
    }

    public String getCode(){ return code;}
}