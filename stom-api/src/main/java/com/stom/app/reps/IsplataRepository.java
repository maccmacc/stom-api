package com.stom.app.reps;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stom.app.jpa.Isplata;

public interface IsplataRepository extends JpaRepository<Isplata, Integer> {
	
	public List<Isplata> findAllByOrderByIdDesc();

}
