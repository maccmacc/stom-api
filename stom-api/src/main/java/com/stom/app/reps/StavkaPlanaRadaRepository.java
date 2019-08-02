package com.stom.app.reps;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stom.app.jpa.PlanRada;
import com.stom.app.jpa.StavkaPlanaRada;

public interface StavkaPlanaRadaRepository extends JpaRepository<StavkaPlanaRada, Integer>{
	
	public Collection<StavkaPlanaRada> findByPlanRada(Optional<PlanRada> p); 

}
