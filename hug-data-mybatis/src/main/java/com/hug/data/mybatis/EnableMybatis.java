package com.hug.data.mybatis;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Description: </p>
 *
 * @author 김형운 (guddns@gmail.com)
 * @since 2014. 3. 10.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MybatisConfiguration.class)
public @interface EnableMybatis
{
	/**
	 * Mybatis 의 설정 파일
	 * ex)classpath:mybatis/mybatis-config.xml, WEB-INF/mybatis-configuration.xml
	 */
	String configLocation() default "classpath:mybatis/mybatis-config.xml";

	/**
	 * Mapper 파일의 경로
	 * 패턴 형식을 취한다.
	 * ex)classpath*:mybatis/mapper/*-mapper.xml
	 */
	String mapperLocations() default "classpath:mybatis/mapper/**/*.xml";
}
