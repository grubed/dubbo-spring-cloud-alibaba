package com.una.alibabausers.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author Una Ma
 * @className MybatisPlusConfig
 * @description mybatis-plus配置类
 * @date 2020/1/27 4:23 下午
 */
@Configuration
@MapperScan("com.una.alibabausers.mybatis.mapper")
public class MybatisPlusConfig {

//    @Autowired
//    private PaginationInterceptor paginationInterceptor;

    /**
     * @param sqlSessionFactory SqlSessionFactory
     * @return SqlSessionTemplate
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 从配置文件获取属性构造datasource，注意前缀，这里用的是druid，根据自己情况配置,
     * 原生datasource前缀取"spring.datasource"
     *
     * @return
     */
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }

//    /**
//     * 构造datasource代理对象，替换原来的datasource
//     * @param druidDataSource
//     * @return
//     */
//    @Primary
//    @Bean("dataSource")
//    public DataSourceProxy dataSourceProxy(DataSource druidDataSource) {
//        return new DataSourceProxy(druidDataSource);
//    }
//
//    /**
//     * 集成seata分布式事务
//     * @param dataSourceProxy
//     * @return sqlSessionFactory
//     * @throws Exception
//     */
//    @Bean(name = "sqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactoryBean(DataSourceProxy dataSourceProxy) throws Exception {
//        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
//        bean.setDataSource(dataSourceProxy);
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        // bean.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));
//        bean.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));
//
//        SqlSessionFactory factory = null;
//        try {
//            //解决集成seata后分页插件失效
//            Interceptor[] plugins = {paginationInterceptor};
//            bean.setPlugins(plugins);
//            factory = bean.getObject();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return factory;
//    }

    /**
     * 未集成分布式事务
     * @param druidDataSource
     * @return sqlSessionFactory
     * @throws Exception
     */
//    @Bean(name = "sqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactoryBean(DataSource druidDataSource) throws Exception {
//        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
//        bean.setDataSource(druidDataSource);
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        // bean.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));
//        bean.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));
//
//        SqlSessionFactory factory = null;
//        try {
//            Interceptor[] plugins = {paginationInterceptor};
//            bean.setPlugins(plugins);
//            factory = bean.getObject();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return factory;
//    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
