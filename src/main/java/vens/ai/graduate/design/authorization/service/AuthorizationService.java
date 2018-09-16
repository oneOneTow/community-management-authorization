package vens.ai.graduate.design.authorization.service;

import vens.ai.graduate.design.authorization.controller.request.LoginRequestVo;
import vens.ai.graduate.design.authorization.controller.request.RegisterRequestVo;
import vens.ai.graduate.design.authorization.controller.response.LoginResponse;
import vens.ai.graduate.design.authorization.controller.response.RegisterResponse;

/**
 * @author
 * @date 2018-03-22 22:46
 **/
public interface AuthorizationService {
    LoginResponse login(LoginRequestVo loginRequest) throws Exception;
    RegisterResponse register(RegisterRequestVo registerRequest)throws Exception;
    LoginResponse freshToken(String token) throws Exception;
}
