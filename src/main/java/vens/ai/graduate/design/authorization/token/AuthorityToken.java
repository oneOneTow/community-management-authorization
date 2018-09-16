package vens.ai.graduate.design.authorization.token;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.stereotype.Service;
import vens.ai.graduate.design.authorization.constant.BaseConstant;
import vens.ai.graduate.design.authorization.util.Base64Helper;
import vens.ai.graduate.design.authorization.util.Hs256;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vens
 * @date 2018-05-07 16:00
 **/
@Service
public class AuthorityToken {
    /**
     * 生成token
     * @param stuId
     * @return
     * @throws Exception
     */
    public String generateToken(String stuId) throws Exception{
        Map<String,String> headers=new HashMap<>();
        headers.put("type","vens");
        headers.put("alg","HS246");
        Map<String,String> payloads=new HashMap<>();
        payloads.put("uid",stuId);
        String exp=String.valueOf(System.currentTimeMillis());
        payloads.put("exp",exp);
        String header= Base64Helper.encode(mapToJson(headers));
        String payload= Base64Helper.encode(mapToJson(payloads));
        String signatureStr=header+"."+payload;
        String signature=Hs256.returnSign(signatureStr);
        String token=header+"."+payload+"."+signature;
        return token;
    }

    /**
     * 验证token是否合法
     * @param token
     * @return
     */
    public boolean verfyToken(String token) throws UnsupportedEncodingException {
        boolean isCurrentToken=false;
        if(null!=token&&""!=token) {
            String[] tokens = token.split(".");
            String signature = Hs256.returnSign(tokens[0] + "." + tokens[1]);
            isCurrentToken=signature.equals(tokens[2])&&tokenIfTimeout(tokens[1]);
        }
        return isCurrentToken;
    }
    public String mapToJson(Map<String,String> map){
        String json="";
        if(map.isEmpty()){
            return json;
        }
        json="{";
        for(Map.Entry<String,String> entry : map.entrySet()){
            json=json+"\""+entry.getKey()+"\""+":"+"\""+entry.getValue()+"\""+";";
        }
        json=json+"}";
        return json;
    }
    public Map jsonToMap(String str){
        ObjectMapper mapper=new ObjectMapper();
        Map<String,String> map= null;
        try {
            map = mapper.readValue(Base64Helper.encode(str),Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 判断token是否超时
     * @param payload(base64)
     * @return
     * @throws UnsupportedEncodingException
     */
    public boolean tokenIfTimeout(String payload) throws UnsupportedEncodingException {
        boolean isTimeout=false;
        Map<String,String> json=jsonToMap(payload);
        Long exp=Long.parseLong(json.get("exp"));
        if ((System.currentTimeMillis()-exp)> BaseConstant.TOKEN_EFFICTIVE_TIME){
            isTimeout=true;
        }
        return isTimeout;
    }

    /**
     * 获取token的header
     * @param token
     * @return
     */
    public Map<String,String> getHeader(String token){
        String[] tokens = token.split(".");
        Map<String,String> headers=jsonToMap(tokens[0]);
        return headers;
    }

    /**
     * 获取token的payload
     * @param token
     * @return
     */
    public Map<String,String> getPayload(String token){
        String[] tokens = token.split(".");
        Map<String,String> headers=jsonToMap(tokens[1]);
        return headers;
    }
}
