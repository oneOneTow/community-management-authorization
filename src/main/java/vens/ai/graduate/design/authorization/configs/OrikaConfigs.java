package vens.ai.graduate.design.authorization.configs;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vens.ai.graduate.design.authorization.controller.request.RegisterRequestVo;
import vens.ai.graduate.design.authorization.entity.User;

/**
 * @author vens
 * @date 2018-05-07 10:19
 **/
@Configuration
public class OrikaConfigs {
    @Bean
    public MapperFactory getFactory(){

        MapperFactory mapperFactory=new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(RegisterRequestVo.class, User.class)
                .exclude("picture")
                .exclude("password")
                .byDefault()
                .register();
        return mapperFactory;
    }
}
