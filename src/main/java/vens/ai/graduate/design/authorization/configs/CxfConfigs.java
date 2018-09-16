package vens.ai.graduate.design.authorization.configs;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vens.ai.graduate.design.authorization.controller.AuthorizationControllerImpl;
import vens.ai.graduate.design.authorization.filter.CorsFilter;
import vens.ai.graduate.design.authorization.filter.TokenVerfyFilter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author vens
 * @date 2018-05-04 13:43
 **/
@Configuration
public class CxfConfigs {
    @Autowired
    private Bus bus;
    @Autowired
    AuthorizationControllerImpl controller;

    @Bean
    public Server rsServer(){
        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
        endpoint.setBus(bus);
        endpoint.setAddress("/");
        endpoint.setServiceBeans(Arrays.<Object>asList(controller));
        endpoint.setProvider(new JacksonJsonProvider());
        endpoint.setInInterceptors(Arrays.asList(getTokenVeryFilter()));
        endpoint.setFeatures(Arrays.asList(new Swagger2Feature()));
        return endpoint.create();
    }
    @Bean
    public Filter getCorsFilter(){
        Filter filter=new CorsFilter();
        return filter;
    }
    @Bean
    public Interceptor getTokenVeryFilter(){
        Interceptor interceptor=new TokenVerfyFilter();
        return interceptor;
    }
}
