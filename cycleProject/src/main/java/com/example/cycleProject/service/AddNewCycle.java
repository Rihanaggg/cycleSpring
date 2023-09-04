 package com.example.cycleProject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cycleProject.model.Cycle;
import com.example.cycleProject.repository.CycleRepository;

@Service
public class AddNewCycle {
	@Autowired
	CycleRepository cycleRepo;
	
	
	public List<Cycle> getAllCycles() {
		ArrayList<Cycle> cycleList = new ArrayList<>();
		cycleRepo.findAll().forEach(Cycle->cycleList.add(Cycle));
		
		return cycleList;
	}
	
	public Cycle getCycleById(Long id) {
		return cycleRepo.findById(id).get();
	}	



	public Cycle borrow(Long id) {
	    Cycle cycle = getCycleById(id);
	    if (cycle != null && cycle.isAvailable()) {
	        cycle.setAvailable(false);
	        cycle.setCounter(cycle.getCounter()-1);
	        cycleRepo.save(cycle); 
	        return cycle;
	    }
	    return null;
	}

	public Cycle Returncycle(Long id) {
	    Cycle cycle = getCycleById(id);
	    if (cycle != null && !cycle.isAvailable()) {
	        cycle.setAvailable(true);
	        cycle.setCounter(cycle.getCounter()+1);
	        cycleRepo.save(cycle); 
	        return cycle;
	    }
	    return null;
	}

	
	public Cycle saveCycle(Cycle cycle) {
		if (cycle != null ) {
			cycleRepo.save(cycle);
			return cycle;
		}
		return null;
	}
	
	


}