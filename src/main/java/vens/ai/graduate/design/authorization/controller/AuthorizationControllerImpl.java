package vens.ai.graduate.design.authorization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vens.ai.graduate.design.authorization.controller.request.RegisterRequestVo;
import vens.ai.graduate.design.authorization.controller.response.LoginResponse;
import vens.ai.graduate.design.authorization.controller.response.RegisterResponse;
import vens.ai.graduate.design.authorization.controller.request.LoginRequestVo;
import vens.ai.graduate.design.authorization.exception.BaseException;
import vens.ai.graduate.design.authorization.service.serviceImpl.AuthorizationServiceImpl;
import vens.ai.graduate.design.authorization.util.Base64Helper;

import javax.imageio.stream.FileImageOutputStream;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import java.io.File;

/**
 * @author vens
 * @date 2018-05-03 9:26
 **/
@Component("authorizationControllerImpl")
public class AuthorizationControllerImpl implements AuthorizationController{
    @Context
    private HttpHeaders headers;

    @Autowired
    AuthorizationServiceImpl authorizationService;


    @Override
    public LoginResponse login(LoginRequestVo loginRequest) throws Exception{
        LoginResponse response=new LoginResponse();

        try{
            response=authorizationService.login(loginRequest);
            response.setCode("200");
        }catch(BaseException e){
            response.setCode(e.getErrorCode());
        }
        return response;
    }

    @Override
    public RegisterResponse register(RegisterRequestVo registerRequest) throws Exception{
        RegisterResponse response=new RegisterResponse();
        try {
            response = authorizationService.register(registerRequest);
            response.setCode("200");
        }catch(BaseException e){
            response.setCode(e.getErrorCode());
        }
        return response;
    }


    @Override
    public RegisterResponse freshToken() throws Exception {
        RegisterResponse response=new RegisterResponse();
        response.setCode("123456789");
        return response;
    }

    @Override
    public boolean checkToken(String token) {

        return false;
    }


}
