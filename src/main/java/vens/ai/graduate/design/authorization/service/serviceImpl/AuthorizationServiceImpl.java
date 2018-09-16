package vens.ai.graduate.design.authorization.service.serviceImpl;

import ma.glasnost.orika.MapperFactory;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vens.ai.graduate.design.authorization.constant.BaseConstant;
import vens.ai.graduate.design.authorization.controller.request.LoginRequestVo;
import vens.ai.graduate.design.authorization.controller.request.RegisterRequestVo;
import vens.ai.graduate.design.authorization.controller.response.LoginResponse;
import vens.ai.graduate.design.authorization.controller.response.RegisterResponse;
import vens.ai.graduate.design.authorization.entity.User;
import vens.ai.graduate.design.authorization.exception.BaseException;
import vens.ai.graduate.design.authorization.gateway.FaceRecognitionGateway;
import vens.ai.graduate.design.authorization.mapper.UserMapper;
import vens.ai.graduate.design.authorization.service.AuthorizationService;
import vens.ai.graduate.design.authorization.token.AuthorityToken;
import vens.ai.graduate.design.authorization.util.Base64Helper;
import vens.ai.graduate.design.authorization.util.RsaAg;

import java.util.Map;

/**
 * @author vens
 * @date 2018-05-07 9:45
 **/
@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    private final static String  FACE_VERIFY="face";
    private final static String  WORD_VERIFY="word";
    private final static int THRESHID=50;
    private final static Logger logger= LoggerFactory.getLogger(AuthorizationServiceImpl.class);
    @Autowired
    MapperFactory mapperFactory;
    @Autowired
    UserMapper userMapper;
    @Autowired
    AuthorityToken authToken;
    @Autowired
    FaceRecognitionGateway faceGateway;
    @Override
    public LoginResponse login(LoginRequestVo loginRequest)  throws Exception{
        LoginResponse response=new LoginResponse();
        if(FACE_VERIFY.equals(loginRequest.getFlag())){
            User user=userMapper.findByStuId(loginRequest.getStudentId());
            byte[][] images=this.makeImages(loginRequest.getPicture(),user.getPicture());
            logger.info("call 百度 api: {}",user.getStudentId());
            JSONObject result = faceGateway.faceMatch(images);
            logger.info("call 百度 api 结果：{}",result.toString());
            if(result!=null
                    && null!=result.get("score")
                    &&Integer.parseInt(String.valueOf(result.get("score")))>THRESHID){
                String token=authToken.generateToken(user.getStudentId());
                response.setToken(token);
                response.setInfo(user.getStudentId());
                return response;
            }
        }
        if(WORD_VERIFY.equals(loginRequest.getFlag())){
            if(null==loginRequest.getPassword()
                    || "".equals(loginRequest.getPassword())
                    ) {
                String errorMsg = "password or name is null";
                throw new BaseException(BaseConstant.INVALID_PASSWORD_OR_NAME,errorMsg);
            }
            //String password= RsaAg.decryptData(loginRequest.getPassword(),BaseConstant.EN_CODE);
            String password= loginRequest.getPassword();
            User user=userMapper.findByStuId(loginRequest.getStudentId());
            if(!password.equals(user.getPassword())){
                String errorMsg = "password is incorrect";
                throw new BaseException(BaseConstant.PASSWORD_INCORRECT,errorMsg);
            }
            String token=authToken.generateToken(user.getStudentId());
            response.setToken(token);
            response.setInfo(user.getStudentId());
            return response;
        }
        String errorMsg="don't exist login way";
        throw new BaseException(errorMsg,BaseConstant.BASE_ERROE_CODE);
    }

    @Override
    public RegisterResponse register(RegisterRequestVo registerRequest) throws Exception{
        RegisterResponse response=new RegisterResponse();
        User user=mapperFactory.getMapperFacade().map(registerRequest,User.class);
        //密碼解碼
        //String password=RsaAg.decryptData(registerRequest.getPassword(),BaseConstant.EN_CODE);
        String password=registerRequest.getPassword();
        user.setPassword(password);
        logger.info("password:{}",password);
        //mapper picture
        user.setPicture(registerRequest.getPicture().getBytes("ISO_8859_1"));
        if(null!=userMapper.findByStuId(user.getStudentId())){
            String errorMsg="student id already exist";
            throw new BaseException(errorMsg,BaseConstant.INVALID_STUDENT_ID);
        }
        if(null!=userMapper.findByName(user.getUserName())){
            String errorMsg="user name already exist";
            throw new BaseException(errorMsg,BaseConstant.INVALID_NAME);
        }
        if(userMapper.save(user)!=0){
            logger.info("注册一个用户：{}",user);
            response.setCode(BaseConstant.SUCCESS_RESPONSE);}
        return response;
    }

    @Override
    public LoginResponse freshToken(String token) throws Exception {
        LoginResponse response=new LoginResponse();
        Map<String,String> headers=authToken.getHeader(token);
        String studendId= headers.get("uid");
        response.setToken(authToken.generateToken(studendId));
        return response;
    }

    /**
     * 将图片转化为百度API接受的参数
     * @param tarImage
     * @param srcImage
     * @return
     * @throws Exception
     */
    private byte[][] makeImages(String tarImage,byte[] srcImage)throws Exception{
       byte[][] images = new byte[2][];
       images[0]= Base64Helper.decde(tarImage).getBytes("utf-8");
       images[1]= Base64Helper.decde(srcImage.toString()).getBytes("utf-8");
       return images;
    }

}
