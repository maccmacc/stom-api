package com.stom.app.reps;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stom.app.jpa.Ordinacija;

public interface OrdinacijaRepository extends JpaRepository<Ordinacija, Integer> {

}
