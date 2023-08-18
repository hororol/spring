package com.example.springboot230703.conf;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MyConf {
    @Autowired
    DataSource dataSource;

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setMapperLocations(
                applicationContext.getResources("classpath:mybatis/sql/*.xml")
        );
        return sqlSessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(
            @Autowired SqlSessionFactory sqlSessionFactory)
            throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

//    @Bean
//    public DataSource dataSource(){
//        BasicDataSource dbs = new BasicDataSource();
//        dbs.setUrl("jdbc:mysql://localhost:3307/mydb");
//        dbs.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dbs.setUsername("root");
//        dbs.setPassword("1234");
//        return dbs;
//    }

}
