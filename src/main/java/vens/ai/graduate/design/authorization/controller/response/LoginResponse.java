package vens.ai.graduate.design.authorization.controller.response;

/**
 * @author vens
 * @date 2018-05-07 9:00
 **/
public class LoginResponse {
    private String info;
    private String token;
    private String code;
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
