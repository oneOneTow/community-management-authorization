package vens.ai.graduate.design.authorization.filter;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import vens.ai.graduate.design.authorization.constant.BaseConstant;
import vens.ai.graduate.design.authorization.exception.BaseException;
import vens.ai.graduate.design.authorization.token.AuthorityToken;

import javax.ws.rs.ext.Provider;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author vens
 * @date 2018-05-13 10:30
 **/
@Provider
public class TokenVerfyFilter extends AbstractPhaseInterceptor {
    private final static Logger logger= LoggerFactory.getLogger(TokenVerfyFilter.class);
    @Autowired
    AuthorityToken authorityToken;
    public TokenVerfyFilter(){
        super(Phase.PRE_INVOKE);
    }
    @Override
    public void handleMessage(Message message) throws Fault{
        Map<String, List<String>> headers = (Map<String, List<String>>)message.get(Message.PROTOCOL_HEADERS);
        String uri=(String)message.get(Message.REQUEST_URI);
        boolean isNeedToken=true;
        if(BaseConstant.NO_TOKEN_URI.contains(uri)){
            isNeedToken=false;
        }
        if(isNeedToken) {
            List<String> token = headers.get("token");
            if (null == token.get(0) && "".equals(token.get(0))) {
                String errorMsg = "token 无效";
                throw new Fault(new BaseException(errorMsg, BaseConstant.INVALID_TOKEN));
            }
            try {
                if (authorityToken.verfyToken(token.get(0)) && authorityToken.tokenIfTimeout(token.get(0))) {
                    String errorMsg = "token 无效";
                    throw new Fault(new BaseException(errorMsg, BaseConstant.INVALID_TOKEN));
                }
            } catch (UnsupportedEncodingException e) {
                logger.info("UnsupportedEncodingException in TokenVeryFilter:");
                e.printStackTrace();
            }
        }

    }
}
