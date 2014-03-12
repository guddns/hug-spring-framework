package com.springapp.config;

import com.hug.core.context.message.EnableMessageSource;
import com.hug.data.mybatis.EnableMybatis;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactory;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

/**
 * <p>Description: </p>
 *
 * @author Kim Hyeong Un
 * @since 2014. 3. 3.
 */
@Configuration
@EnableMessageSource(basenames = {"classpath:i18n/messages", "classpath:i18n/errors"})
@EnableMybatis
public class RootConfig
{
	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseFactory factory = new EmbeddedDatabaseFactory();
		factory.setDatabaseName("sample");
		factory.setDatabaseType(EmbeddedDatabaseType.H2);

		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("db/migration/V1__schema.sql"));
		populator.addScript(new ClassPathResource("db/migration/V2__data.sql"));
		factory.setDatabasePopulator(populator);

		return factory.getDatabase();
	}
}
