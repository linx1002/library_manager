package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Log;
import com.example.repository.LogRepository;

@Service
public class LogService {
	
	private final LogRepository logRepository;
	
	@Autowired
	public LogService(LogRepository logRepository) {
		this.logRepository = logRepository;
	}

}
