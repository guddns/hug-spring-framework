package com.springapp.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author 김형운 (guddns@gmail.com)
 * @since 2014. 3. 7.
 */
@Repository
public interface WatchMapper
{
	public List<Map> selectAll();
}
