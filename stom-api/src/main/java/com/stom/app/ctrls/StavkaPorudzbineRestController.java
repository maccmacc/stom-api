package com.stom.app.ctrls;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.stom.app.jpa.Porudzbina;
import com.stom.app.jpa.StavkaPorudzbine;
import com.stom.app.reps.PorudzbinaRepository;
import com.stom.app.reps.StavkaPorudzbineRepository;

@RestController
public class StavkaPorudzbineRestController {
	
	@Autowired
	PorudzbinaRepository porudzbinaRepository;
	
	@Autowired
	StavkaPorudzbineRepository stavkaPorudzbineRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "stavkaPorudzbineNextId", method = RequestMethod.GET)
	public int getNextId() {		
        Integer sledeci = jdbcTemplate.queryForObject("SELECT max(id)+1 "
													+ "FROM stavka_porudzbine", Integer.class);
        if (sledeci == null)
        	sledeci = new Integer(1);
        return sledeci.intValue();
	}
	
	@RequestMapping(value = "stavkaPorudzbineNextRedniBroj/{idPorudzbine}", method = RequestMethod.GET)
	public int getNextRedniBroj(@PathVariable("idPorudzbine") int idPorudzbine) {		
        Integer sledeci = jdbcTemplate.queryForObject("SELECT max(redni_broj)+1 "
													+ "FROM stavka_porudzbine "
													+ "where porudzbina="+idPorudzbine, Integer.class);
        if (sledeci == null)
        	sledeci = new Integer(1);
        return sledeci.intValue();
	}
	
	@RequestMapping(value="stavkaPorudzbine", method=RequestMethod.GET)
	public Collection<StavkaPorudzbine> getAll(){
		return stavkaPorudzbineRepository.findAll();
	}
	
	@RequestMapping(value="stavkaPorudzbine/{id}",method=RequestMethod.GET)
	public ResponseEntity<StavkaPorudzbine> get(@PathVariable("id") int id){
		Optional<StavkaPorudzbine> obj=stavkaPorudzbineRepository.findById(id);
		return new ResponseEntity(obj, HttpStatus.OK);
	}
	
	@RequestMapping(value="stavkaPorudzbine/stavkeZaPorudzbinaId/{id}", method=RequestMethod.GET)
	public Collection<StavkaPorudzbine> stavkaPoPoPorudzbiniId(@PathVariable("id") int id){
		Optional<Porudzbina> p= porudzbinaRepository.findById(id);
		return stavkaPorudzbineRepository.findByPorudzbina(p);
	}
	
	@CrossOrigin
	@RequestMapping(value="stavkaPorudzbine", method=RequestMethod.POST)
	public ResponseEntity<Void> insertStavkaPorudzbine(@RequestBody StavkaPorudzbine obj){
		if (stavkaPorudzbineRepository.existsById(obj.getId())){
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}				
		stavkaPorudzbineRepository.save(obj);
		Porudzbina p = obj.getPorudzbina();
		Porudzbina izBaze = porudzbinaRepository.findById(p.getId()).orElseThrow(() -> new RuntimeException());
		izBaze.setIznos(izBaze.getUkupno());		
		porudzbinaRepository.save(izBaze);	
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value="stavkaPorudzbine/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> updateStavkaPorudzbine(@PathVariable("id") Integer id, @RequestBody StavkaPorudzbine obj){
		if (!stavkaPorudzbineRepository.existsById(id)){
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}		
		stavkaPorudzbineRepository.save(obj);
		Porudzbina p = obj.getPorudzbina();
		Porudzbina izBaze = porudzbinaRepository.findById(p.getId()).orElseThrow(() -> new RuntimeException());
		izBaze.setIznos(izBaze.getUkupno());		
		porudzbinaRepository.save(izBaze);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
		
	@CrossOrigin
	@RequestMapping(value="stavkaPorudzbine/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteStavkaPorudzbine(@PathVariable("id") int id){
		if (!stavkaPorudzbineRepository.existsById(id)) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		int idPorudzbine = stavkaPorudzbineRepository.findById(id).orElseThrow(() -> new RuntimeException()).getPorudzbina().getId();
		stavkaPorudzbineRepository.deleteById(id);						
		Porudzbina izBaze = porudzbinaRepository.findById(idPorudzbine).orElseThrow(() -> new RuntimeException());
		izBaze.setIznos(izBaze.getUkupno());		
		porudzbinaRepository.save(izBaze);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}