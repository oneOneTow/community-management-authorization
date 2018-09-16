package vens.ai.graduate.design.authorization.controller;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.apache.cxf.rs.security.cors.LocalPreflight;
import org.springframework.web.bind.annotation.RequestHeader;
import vens.ai.graduate.design.authorization.controller.request.RegisterRequestVo;
import vens.ai.graduate.design.authorization.controller.request.TestRequest;
import vens.ai.graduate.design.authorization.controller.response.LoginResponse;
import vens.ai.graduate.design.authorization.controller.response.RegisterResponse;
import vens.ai.graduate.design.authorization.controller.request.LoginRequestVo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author vens
 * @date 2018-03-22 22:43
 **/
@Path("/endpoint")
public interface AuthorizationController {
    /**
     * 登录接口
     * @param loginRequestVo
     * @return AuthResponse
     */
    @Path("/login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    LoginResponse login(LoginRequestVo loginRequest) throws Exception;
    /**
     *注册接口
     * @param registerRequest
     * @return AuthResponse
     */
    @Path("/register")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    RegisterResponse register(RegisterRequestVo registerRequest)throws Exception;

    @Path("/upload")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    RegisterResponse test(TestRequest request)throws Exception;

    /**
     * 刷新token
     * @param token
     * @return
     * @throws Exception
     */
    @Path("/get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    RegisterResponse freshToken()throws Exception;
    //RegisterResponse freshToken(@RequestHeader("auth_token")String token)throws Exception;

    /**
     * This method will do a preflight check itself
     * @return
     */
//    @OPTIONS
//    @Path("/test")
//    @LocalPreflight
//    Response options();
}
