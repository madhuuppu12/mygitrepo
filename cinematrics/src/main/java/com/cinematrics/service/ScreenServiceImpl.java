package com.cinematrics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinematrics.dao.ScreenDao;

@Service
public class ScreenServiceImpl implements ScreenService {
	@Autowired
	ScreenDao screenDao;
	
	
	private void save() {
		// TODO Auto-generated method stub

	}

}
