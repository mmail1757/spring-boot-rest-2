package app.test.model;

public enum RequestType {
    CREATE_AGT("CREATE-AGT"),
    GET_BALANCE("GET-BALANCE");

    private String code;

    RequestType(String code) {
        this.code = code;
    }

    public String getCode(){ return code;}
}
