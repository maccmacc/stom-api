package com.stom.app.reps;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.stom.app.jpa.Pacijent;
import org.springframework.stereotype.Repository;

@Repository
public interface PacijentRepository extends JpaRepository<Pacijent, Integer> {
	
	public List<Pacijent> findAllByOrderByIdDesc();
	Page<Pacijent> findByImeContainingIgnoreCase(String ime, Pageable p);
	Page<Pacijent> findByPrezimeContainingIgnoreCase(String prezime, Pageable p);
	Pacijent findOneById(Integer id);
	

}
