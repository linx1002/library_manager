package com.example.service;

import java.util.List;

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
	
	public void addLog(Log logs) {
	    logRepository.save(logs);
	}
	
	public List<Log> getAllLogs() {
	    return logRepository.findAll();
	}
	
	public List<Log> getLogsByUserId(Long userId) {
	    return logRepository.findByUserId(userId);
	}
	
	public List<Log> getLogsByLibraryId(Long libraryId) {
	    return logRepository.findByLibraryId(libraryId);
	}

	public void save(Log log) {
		 logRepository.save(log);
	}

}
