package com.centime.database.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.centime.database.entity.HierEntity;
import com.centime.database.model.HierResponse;
import com.centime.database.repository.DatabaseRepository;

@RestController
public class DatabseController {
	
	@Autowired
	DatabaseRepository hierRepo;
	
	@GetMapping(value="welcome")
	public ResponseEntity<?> welcome() {
		
		return ResponseEntity.ok("welcome to databse service");
	}
	
	@PostMapping(value="api/centime/add-data")
	public ResponseEntity<?> addTableData(@RequestBody List<HierEntity> request) {
//		List<HierEntity> entitys = new ArrayList<HierEntity>();
//		while(request.size() >0) {
//			for(HierRequest hier: request) {
//				HierEntity entity = new HierEntity();
//				entity.setId(hier.getId());
//				entity.setParentid(hier.getParentId());
//				entity.setName(hier.getName());
//				entity.setColor(hier.getColor());
//				entitys.add(entity);
//			}
//		}
		List<HierEntity> response = hierRepo.saveAll(request);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value="api/centime/get-hier-byid/{id}")
	public ResponseEntity<?> getHierById(@PathVariable int id) {
		
//		List<HierResponse> response = new ArrayList<HierResponse>();
		
		Optional<HierEntity> response =  hierRepo.findById(id);
		
		if(response.isPresent()) {
			HierEntity subByData = response.get();
			List<HierEntity> subData1 = hierRepo.getSubDataByParentId(subByData.getId());
			List<HierEntity> subData = getRecursiveData(subData1);
			subByData.setSubClass(subData);
			return ResponseEntity.ok(subByData);
		}
		
		return ResponseEntity.ok("invalid Id");
	}
	
	@GetMapping(value="api/centime/get-hier")
	public ResponseEntity<?> getHier() {
		List<HierEntity> respone =  hierRepo.findAll();
		
		List<HierEntity> subData = getRecursiveData(respone);
		
		if(respone.size() < 1) {
			return ResponseEntity.ok("no Data present in table");
		}
		return ResponseEntity.ok(subData);
	}
	
	public List<HierEntity> getRecursiveData(List<HierEntity> request) {
		
		for(HierEntity sub: request) {
			List<HierEntity> subData = hierRepo.getSubDataByParentId(sub.getId());
			if(subData.size()>0) {
				sub.setSubClass(subData);
				getRecursiveData(subData);
			}
		}
		return request;
	}
	

}
