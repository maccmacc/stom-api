package com.stom.app.reps;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stom.app.jpa.Porudzbina;
import com.stom.app.jpa.StavkaPorudzbine;

public interface StavkaPorudzbineRepository extends JpaRepository<StavkaPorudzbine, Integer>{
	
	public Collection<StavkaPorudzbine> findByPorudzbina(Optional<Porudzbina> p); 

}
