package com.springapp.service;

import com.springapp.mapper.WatchMapper;
import com.sun.xml.internal.xsom.impl.scd.Iterators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author 김형운 (guddns@gmail.com)
 * @since 2014. 3. 7.
 */
@Service
@Transactional
public class WatchService
{
	@Autowired
	private WatchMapper watchMapper;

	public List<Map> selectAll() {
		return watchMapper.selectAll();
	}
}
