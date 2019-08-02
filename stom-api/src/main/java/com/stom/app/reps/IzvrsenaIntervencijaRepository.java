package com.stom.app.reps;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.stom.app.jpa.IzvrsenaIntervencija;
import com.stom.app.jpa.Pacijent;

public interface IzvrsenaIntervencijaRepository extends JpaRepository<IzvrsenaIntervencija, Integer> {

	public List<IzvrsenaIntervencija> findAllByOrderByIdDesc();
	public Page<IzvrsenaIntervencija> findAllByOrderByIdDesc(Pageable p);
	
	public Collection <IzvrsenaIntervencija> findByPacijentOrderByDatumDesc (Optional<Pacijent> pacijent);
}
