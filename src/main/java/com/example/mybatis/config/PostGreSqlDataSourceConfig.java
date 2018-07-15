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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.example.mybatis.mapper.postgresql", sqlSessionTemplateRef = "postGreSqlSessionTemplate")
public class PostGreSqlDataSourceConfig {

    @Value("${spring.datasource.postgresql.mapperLocations}")
    private String postGreSqlMapperLocations;

    /**
     * 配置PostGreSql 数据源
     */
    @Bean(name = "postGreSqlDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.postgresql")
    public DataSource postGreSqlDatasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "postGreSqlConfiguration")
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration configuration() {
        return new org.apache.ibatis.session.Configuration();
    }

    /**
     * 配置PostGreSql Session工厂
     */
    @Bean(name = "postGreSqlSessionFactory")
    public SqlSessionFactory postGreSqlSessionFactory(@Qualifier("postGreSqlDatasource") DataSource dataSource, @Qualifier("postGreSqlConfiguration") org.apache.ibatis.session.Configuration configuration) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setConfiguration(configuration);
        bean.setDataSource(dataSource);
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        return bean.getObject();
    }

    /**
     * 配置PostGreSql 事务管理器
     */
    @Bean(name = "postGreSqlTransactionManger")
    public DataSourceTransactionManager postGreSqlTransactionManger(@Qualifier("postGreSqlDatasource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 配置PostGreSql 模板
     */
    @Bean(name = "postGreSqlSessionTemplate")
    public SqlSessionTemplate postGreSqlSessionTemplate(@Qualifier("postGreSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
