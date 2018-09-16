package vens.ai.graduate.design.authorization;


import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;

/**
 * @author vens
 * @date 2018-05-01 16:02
 **/
@SpringBootApplication
@MapperScan(basePackages = "vens.ai.graduate.design.authorization.mapper")
public class AuthorizationApplication {

    private final static Logger logger= LoggerFactory.getLogger(AuthorizationApplication.class);
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AuthorizationApplication.class, args);
        DataSource dataSource=context.getBean(DataSource.class);
        logger.info("什么数据源：{}_______________",dataSource.getClass());
    }

}
