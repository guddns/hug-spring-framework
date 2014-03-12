package com.hug.core.context.message;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import static com.google.common.base.Preconditions.checkState;

/**
 * <p>Description: </p>
 *
 * @author Kim Hyeong Un
 * @since 2014. 2. 21.
 */
@Configuration
public class MessageSourceConfiguration implements ApplicationContextAware, ImportAware
{
	protected ApplicationContext applicationContext;

	private String defaultEncoding;
	private String[] basenames;


	@Bean(name = "messageSource")
	public MessageSource messageSource() {
		checkState(this.basenames.length > 0);

		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setDefaultEncoding(this.defaultEncoding);
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setCacheSeconds(50);
		messageSource.setBasenames(this.basenames);
		return messageSource;
	}

	@Bean
	public MessageSourceAccessor messageSourceAccessor() {
		return new MessageSourceAccessor(this.messageSource());
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void setImportMetadata(AnnotationMetadata annotationMetadata) {
		AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(EnableMessageSource.class.getName()));
		this.defaultEncoding = annotationAttributes.getString("defaultEncoding");
		this.basenames = annotationAttributes.getStringArray("basenames");
	}

}
