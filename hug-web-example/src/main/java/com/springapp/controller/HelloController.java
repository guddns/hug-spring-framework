package com.springapp.controller;

import com.springapp.service.WatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
@Slf4j
public class HelloController
{
	@Autowired
	private WatchService watchService;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		List<Map> watchList = watchService.selectAll();
		log.info("watchList: {}", watchList);

		model.addAttribute("message", "Hello world!");
		return "index";
	}

}