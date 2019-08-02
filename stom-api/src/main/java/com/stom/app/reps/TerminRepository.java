package com.stom.app.reps;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stom.app.jpa.Termin;

public interface TerminRepository extends JpaRepository<Termin, Integer> {

}
