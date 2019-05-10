package com.zhidao.cm.api.app.config;

import org.apache.ibatis.plugin.Interceptor;

import org.mybatis.dao.Dao;
import org.mybatis.dao.DaoConfig;
import org.mybatis.dao.DaoPlugin;
import org.mybatis.dao.DataBase;
import org.mybatis.dao.mapper.DaoMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Define MyBatisConfig.
 * <p>Created with IntelliJ IDEA on 04/11/2017 18:36.</p>
 *
 * @author yangyanju [yangyanju@yxqiche.com]
 * @version 1.0
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.course","org.mybatis.dao.mapper"})
public class MyBatisConfig {
    @Bean(name = "dao")
    @Primary
    public Dao dao(@Qualifier("daoMapper")DaoMapper daoMapper) throws Exception {

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
