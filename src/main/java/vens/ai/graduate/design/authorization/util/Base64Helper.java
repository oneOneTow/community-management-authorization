package vens.ai.graduate.design.authorization.util;

import vens.ai.graduate.design.authorization.constant.BaseConstant;
import vens.ai.graduate.design.authorization.exception.BaseException;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author vens
 * @date 2018-05-07 9:08
 **/
public class Base64Helper {
    public static Base64.Decoder decoder = Base64.getDecoder();
    public static Base64.Encoder encoder = Base64.getEncoder();

    public static String encode(String srcStr) throws UnsupportedEncodingException {
        if(0==srcStr.length()||null==srcStr){
            return srcStr;}
        byte[] srcStrByte = srcStr.getBytes("ISO_8859_1");
        String result = encoder.encodeToString(srcStrByte);
        return result;
    }
    public static String encode(byte[] srcStr) throws Exception {
        if(null==srcStr){
            String errorMsg="被编码字符串为空";
            throw new BaseException(errorMsg, BaseConstant.SRC_IS_NULL);}
        String result = encoder.encodeToString(srcStr);
        return result;
    }

    public static String decde(String srcStr) throws UnsupportedEncodingException {

        if(0==srcStr.length()||null==srcStr){
            return srcStr;}
        String result=new String(decoder.decode(srcStr), "ISO_8859_1");
        return result;
    }
}
