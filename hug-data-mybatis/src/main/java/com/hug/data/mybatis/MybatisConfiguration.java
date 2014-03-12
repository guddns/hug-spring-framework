package com.hug.data.mybatis;

import com.google.common.base.Strings;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Mybatis 설정 정보
 *
 * @author 김형운 (guddns@gmail.com)
 * @since 2014. 3. 6.
 */
@Configuration
@EnableTransactionManagement
public class MybatisConfiguration implements ApplicationContextAware, ImportAware
{
	private ApplicationContext applicationContext;

	@Autowired
	private DataSource dataSource;
	private String configLocation;
	private String mapperLocations;


	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		checkNotNull(this.dataSource);
		checkNotNull(this.configLocation);

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(this.dataSource);
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource(configLocation));
		if (mapperLocations != null) {
			sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources(mapperLocations));
		}
		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}

	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager() {
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(dataSource);
		return dataSourceTransactionManager;
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void setImportMetadata(AnnotationMetadata annotationMetadata) {
		AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(EnableMybatis.class.getName()));
		this.configLocation = Strings.emptyToNull(annotationAttributes.getString("configLocation"));
		this.mapperLocations = Strings.emptyToNull(annotationAttributes.getString("mapperLocations"));
	}
}
