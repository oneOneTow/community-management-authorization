package vens.ai.graduate.design.authorization.gateway;


import com.baidu.aip.face.AipFace;
import org.json.JSONObject;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * call other api to achieve face recognize
 *
 * @author vens
 * @date 2018-03-22 22:54
 **/
@Component
@ConfigurationProperties(prefix = "gateway.baidu")
public class FaceRecognitionGateway{
    String appId;
    String apiKey;
    String secretKey;

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    private AipFace getAvailableAipFace(){
        return new AipFace(appId,apiKey,secretKey);
    }
    public JSONObject faceMatch(String sourceFace,String targetFace){
        HashMap<String,String> options=new HashMap<>(16);
        options.put("ext_fields", "qualities");
        options.put("image_liveness", ",faceliveness");
        options.put("types", "7,13");
        ArrayList<String> images=new ArrayList<>();
        images.add(sourceFace);
        images.add(targetFace);
        return this.getAvailableAipFace().match(images,options);
    }
    public JSONObject identifyUser(String groupId,String image){
        HashMap<String, String> options = new HashMap<>(16);
        options.put("ext_fields", "faceliveness");
        options.put("user_top_num", "3");
        //String groupId ="one";
        return this.getAvailableAipFace().identifyUser(groupId, image, options);
    }
}
