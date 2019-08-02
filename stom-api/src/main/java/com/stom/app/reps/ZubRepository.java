package com.stom.app.reps;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stom.app.jpa.Pacijent;
import com.stom.app.jpa.Zub;

public interface ZubRepository extends JpaRepository<Zub, Integer> {

	public Collection <Zub> findByPacijent (Pacijent pacijent);
}
