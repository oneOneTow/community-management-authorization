package vens.ai.graduate.design.authorization.constant;

/**
 * @author vens
 * @date 2018-05-07 12:27
 **/
public class BaseConstant {

    public final static String EN_CODE="ISO_8859_1";
    public final static String BASE_ERROE_CODE="505";
    public final static String SUCCESS_RESPONSE="200";
    public final static String INVALID_STUDENT_ID="600";
    public final static String INVALID_PASSWORD_OR_NAME="501";
    public final static String INVALID_NAME="502";
    public final static String PASSWORD_INCORRECT="503";
    public final static String SRC_IS_NULL="504";
    public final static String INVALID_TOKEN="505";
    /**
     * 设置token的有效时间
     */
    public final static long TOKEN_EFFICTIVE_TIME=30*60*1000L;
    /**
     * 不需要token的URI
     */
    public final static String NO_TOKEN_URI="/authorization/endpoint/login,/authorization/endpoint/register,/authorization/endpoint/upload,,/authorization/endpoint/test";
}
