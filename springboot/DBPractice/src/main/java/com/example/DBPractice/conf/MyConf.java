package com.example.DBPractice.conf;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration // @Configuration 어노테이션을 사용하면 해당 클래스를 구성클래스로 지정한다. 구성클래스는 Spring컨테이너에게 Bean정의 및 구성 정보를 제공하는 역할을 수행한다. 일반적으로 @Configuration어노테이션은 @Bean어노테이션과 함께 사용된다.
public class MyConf {

    // @Autowired는 Spring 프레임워크에서 사용되는 주입(Injection) 어노테이션입니다. 이 어노테이션을 사용하면 Spring 컨테이너가 자동으로 필요한 의존성을 찾아서 해당 필드, 생성자, 메서드 매개변수에 주입해줍니다. 주입할 대상을 자동으로 찾을때, 해당 타입의 빈이 한개만 존재해야 한다. 여러개의 빈이 존재할 경우 추가적인 설정이 필요하다.
    @Autowired
    DataSource dataSource;
    // 위처럼 적으면 필드주입이 된다. DataSource타입의 빈을 찾아서 dataSource필드에 주입한다.

    @Autowired
    ApplicationContext applicationContext;

    @Bean // SqlSessionFactory 타입의 빈이 Spring컨테이너에 의해 생성되고 관리될 것임을 나타내는 어노테이션
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setMapperLocations( // setMapperLocations메소드는 MyBatis매퍼 파일의 위치를 설정한다.
                applicationContext.getResources("classpath:mybatis/sql/*.xml") // getResources() 메소드는 클래스 패스(classpath)에서 매퍼 파일(*.xml)을 찾아 리소스 배열로 반환한다. 이렇게 함으로써 MyBatis는 매퍼파일에 정의된 SQL문과 객체매핑을 사용할 수 있다.
        );
        return sqlSessionFactory.getObject(); // getObject()메소드는 SqlSessionFactoryBean에서 SqlSessionFactory객체를 가져옴.
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(
            @Autowired SqlSessionFactory sqlSessionFactory)
            throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
