package com.stom.app.reps;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stom.app.jpa.Pacijent;
import com.stom.app.jpa.PlanRada;

public interface PlanRadaRepository extends JpaRepository<PlanRada, Integer> {

	public Collection <PlanRada> findByPacijent (Optional<Pacijent> pacijent);
}
