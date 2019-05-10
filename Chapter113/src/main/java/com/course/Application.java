package com.course;

import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.dao.Dao;
import org.mybatis.dao.DaoConfig;
import org.mybatis.dao.DaoPlugin;
import org.mybatis.dao.DataBase;
import org.mybatis.dao.mapper.DaoMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PreDestroy;

@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.course")
@EnableTransactionManagement
@MapperScan({"com.course","org.mybatis.dao.mapper"})
public class Application {
    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        Application.context = SpringApplication.run(Application.class,args);
    }
    @PreDestroy
    public void close(){
        Application.context.close();
    }
    @Bean(name = "dao")
    @Primary
    public Dao dao(@Qualifier("daoMapper") DaoMapper daoMapper) throws Exception {

        return new Dao(daoMapper,new DaoConfig() {

            @Override
            public DataBase getDataBase() {
                // TODO Auto-generated method stub
                return DataBase.MYSQL;
            }

        });
    }
    @Bean(name = "interceptors")
    @Primary
    public Interceptor[] interceptors(){
        return new Interceptor[]{new DaoPlugin()/*,new PagePlugin()*/};
    }
}
