package com.example.mybatis.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.example.mybatis.mapper.mysql", sqlSessionTemplateRef = "mySqlSessionTemplate")
public class MySqlDataSourceConfig {

    @Value("${spring.datasource.mysql.mapperLocations}")
    private String mySqlMapperLocations;

    /**
     * 配置MySql 数据源
     */
    @Bean(name = "mySqlDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    @Primary
    public DataSource mySqlDatasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mySqlConfiguration")
    @ConfigurationProperties(prefix = "mybatis.configuration")
    @Primary
    public org.apache.ibatis.session.Configuration configuration() {
        return new org.apache.ibatis.session.Configuration();
    }

    /**
     * 配置MySql Session工厂
     */
    @Bean(name = "mySqlSessionFactory")
    @Primary
    public SqlSessionFactory mySqlSessionFactory(@Qualifier("mySqlDatasource") DataSource dataSource, @Qualifier("mySqlConfiguration") org.apache.ibatis.session.Configuration configuration) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setConfiguration(configuration);
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mySqlMapperLocations));
        return bean.getObject();
    }

    /**
     * 配置MySql 事务管理器
     */
    @Bean(name = "mySqlTransactionManger")
    @Primary
    public DataSourceTransactionManager mySqlTransactionManger(@Qualifier("mySqlDatasource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 配置MySql 模板
     */
    @Bean(name = "mySqlSessionTemplate")
    @Primary
    public SqlSessionTemplate mySqlSessionTemplate(@Qualifier("mySqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
