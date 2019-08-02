package com.stom.app.reps;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stom.app.jpa.Pacijent;
import com.stom.app.jpa.Snimak;

public interface SnimakRepository extends JpaRepository <Snimak, Integer> {

	Snimak findByOpis(String opis);

	Collection<Snimak> findByPacijent(Pacijent pacijent);

}
