package com.concretepage.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.concretepage.bean.Person;

@Repository
@Transactional
public class EmployeeDAO {
	  private static final String KEY = "employees";
	  
	  @Resource(name="redisTemplate")
	  private HashOperations<String, Integer, Person> hashOps;	 
	  
	  public void addEmployee(Person person) {
		  hashOps.putIfAbsent(KEY, person.getId(), person);
	  }
	  public void updateEmployee(Person person) {
		  hashOps.put(KEY, person.getId(), person);
	  }	  
	  public Person getEmployee(Integer id) {
		  return hashOps.get(KEY, id);
	  }
	  public long getNumberOfEmployees() {
		  return hashOps.size(KEY);
	  }
	  public Map<Integer, Person> getAllEmployees() {
		  return hashOps.entries(KEY);
	  }
	  public long deleteEmployees(Integer... ids) {
		  return hashOps.delete(KEY, (Object)ids);
	  }	  		  
}