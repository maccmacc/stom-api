package com.stom.app.reps;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stom.app.jpa.Zaposleni;

public interface ZaposleniRepository extends JpaRepository<Zaposleni, Integer> {

	public Zaposleni findByUsername(String username);
	
}
