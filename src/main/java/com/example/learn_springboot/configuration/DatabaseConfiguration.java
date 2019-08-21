package com.example.learn_springboot.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
// @PropertySource("classpath:/application.properties")
@EnableTransactionManagement
public class DatabaseConfiguration {
	@Bean
	// @ConfigurationProperties(prefix = "spring.datasource")
	@Primary
	public DataSource dataSource() {
		DataSource dataSource = DataSourceBuilder.create().url("jdbc:h2:mem:testdb").driverClassName("org.h2.Driver")
				.username("sa").password("").build();
		return dataSource;
	}

	// @Autowired
	// private ApplicationContext applicationContext;

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext applicationContext)
			throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource((javax.sql.DataSource) dataSource);
		// sqlSessionFactoryBean.setMapperLocations(applicationContext.getResource("/mapper/*.xml"));
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));

		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}