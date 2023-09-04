package com.example.cycleProject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Cycle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String brandname;
	
	private int counter;

	private boolean isAvailable;
	
	public boolean isAvailable() {
        return isAvailable;
    }

	public void setAvailable(boolean b) {
		this.isAvailable = b;
		
	}
	
	public int getCounter() {
	        return counter;
	}

    public void setCounter(int counter) {
        this.counter = counter;
    }

}
