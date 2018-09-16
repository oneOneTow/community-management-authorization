package vens.ai.graduate.design.authorization.controller.request;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author vens
 * @date 2018-04-19 21:45
 **/
@XmlRootElement
public class LoginRequestVo implements Serializable{
    /**
     * 判断是面部识别登录还是密码
     */
    private String flag;
    private String studentId;
    private String password;
    /**
     * base64图片字符串
     */
    private String picture;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
