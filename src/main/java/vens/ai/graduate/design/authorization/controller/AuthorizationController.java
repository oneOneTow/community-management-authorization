package vens.ai.graduate.design.authorization.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vens.ai.graduate.design.authorization.entity.TestUser;
import vens.ai.graduate.design.authorization.request.AuthRequest;
import vens.ai.graduate.design.authorization.request.TokenRequest;
import vens.ai.graduate.design.authorization.response.AuthResponse;
import vens.ai.graduate.design.authorization.response.TokenResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author vens
 * @date 2018-03-22 22:43
 **/
@Path("/endpoint")
public interface AuthorizationController {
    /**
     * 登录接口
     * @param authRequest
     * @return AuthResponse
     */
    @Path("/login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    AuthResponse login(AuthRequest authRequest);
    /**
     *
     * @param authRequest
     * @return AuthResponse
     */
    @Path("/verify")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    AuthResponse checkToken();
    /**
     *
     * @param authRequest
     * @return AuthResponse
     */
    @Path("/get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    List<TestUser> getAll();
}
