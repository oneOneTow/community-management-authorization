package vens.ai.graduate.design.authorization.configs;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 配置c3p0数据源
 * @author vens
 * @date 2018-05-05 19:19
 **/
@Configuration
public class DataSourceConfigs {
    @Bean(name="dataSource")
    @Primary //区分主数据源
    @ConfigurationProperties("c3p0")
    public DataSource getDataSource(){
        return DataSourceBuilder.create().type(ComboPooledDataSource.class).build();
    }
//配sqlSessionFactory会报mapperMethod not fond exception
//    @Bean
//    public SqlSessionFactoryBean sqlSessionFactoryBean(){
//        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
//        sqlSessionFactory.setDataSource(getDataSource());
//        return sqlSessionFactory;
//    }
}
