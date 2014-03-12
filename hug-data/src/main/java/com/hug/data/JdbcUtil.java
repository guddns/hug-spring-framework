package com.hug.data;

import com.jolbox.bonecp.BoneCPDataSource;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;

/**
 * Jdbc 관련 유틸 클래스
 *
 * @author 김형운 (guddns@gmail.com)
 * @since 2014. 3. 6.
 */
@Slf4j
public class JdbcUtil
{
	public JdbcUtil() {
	}

	/**
	 * DataSource 를 반환한다.
	 *
	 * @param driverClass
	 * @param url
	 * @param username
	 * @param passwd
	 * @return
	 */
	public static DataSource getDataSource(String driverClass, String url, String username, String passwd) {
		return getTomcatDataSource(driverClass, url, username, passwd);
	}

	/**
	 * BoneCP DataSource 를 반환한다.
	 *
	 * @param driverClass
	 * @param url
	 * @param username
	 * @param passwd
	 * @return
	 */
	public static DataSource getBoneCpDataSource(String driverClass, String url, String username, String passwd) {
		if (log.isDebugEnabled()) {
			log.debug("Build BoneCP DataSource");
			log.debug("driverClass=[{}], url=[{}], username=[{}], passwd=[{}]", driverClass, url, username, passwd);
		}

		BoneCPDataSource ds = new BoneCPDataSource();
		ds.setDriverClass(driverClass);
		ds.setJdbcUrl(url);
		ds.setUsername(username);
		ds.setPassword(passwd);
		ds.setPartitionCount(3);
		ds.setMaxConnectionsPerPartition(200);
		ds.setMinConnectionsPerPartition(10);
		ds.setAcquireIncrement(5);
		ds.setIdleConnectionTestPeriodInMinutes(1);
		return ds;
	}

	/**
	 * Tomcat DataSource 를 반환한다.
	 *
	 * @param driverClass
	 * @param url
	 * @param username
	 * @param passwd
	 * @return
	 */
	public static DataSource getTomcatDataSource(String driverClass, String url, String username, String passwd) {
		if (log.isDebugEnabled()) {
			log.debug("Build Tomcat DataSource");
			log.debug("driverClass=[{}], url=[{}], username=[{}], passwd=[{}]", driverClass, url, username, passwd);
		}

		org.apache.tomcat.jdbc.pool.PoolProperties p = new org.apache.tomcat.jdbc.pool.PoolProperties();
		p.setDriverClassName(driverClass);
		p.setUrl(url);
		p.setUsername(username);
		p.setPassword(passwd);
		p.setJmxEnabled(true);
		p.setTestWhileIdle(true);
		p.setTestOnBorrow(true);
		p.setValidationQuery("SELECT 1");
		p.setTestOnReturn(false);
		p.setValidationInterval(30000);
		p.setTimeBetweenEvictionRunsMillis(30000);
		p.setMaxActive(200);
		p.setInitialSize(10);
		p.setMaxWait(10000);
		p.setRemoveAbandonedTimeout(60);
		p.setMinEvictableIdleTimeMillis(30000);
		p.setMinIdle(10);

		DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource(p);
		return ds;
	}

	/**
	 * Hsql DB 에 대한 DataSource 를 반환한다.
	 */
	public static DataSource getEmbeddedHsqlDataSource() {
		return getDataSource("org.hsqldb.jdbcDriver", "jdbc:hsqldb:mem:test;MVCC=TRUE;", "sa", "");
	}

	/**
	 * H2 DB 에 대한 DataSource 를 반환한다.
	 */
	public static DataSource getEmbeddedH2DataSource() {
		return getDataSource("org.h2.Driver", "jdbc:h2:file:target/db/test;DB_CLOSE_DELAY=-1;MVCC=TRUE;", "sa", "");
	}
}
