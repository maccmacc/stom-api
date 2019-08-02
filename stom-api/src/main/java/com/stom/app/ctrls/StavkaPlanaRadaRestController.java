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

import com.stom.app.jpa.PlanRada;
import com.stom.app.jpa.StavkaPlanaRada;
import com.stom.app.reps.PlanRadaRepository;
import com.stom.app.reps.StavkaPlanaRadaRepository;

@RestController
@CrossOrigin
public class StavkaPlanaRadaRestController {
	
	@Autowired
	PlanRadaRepository planRadaRepository;
	
	@Autowired
	StavkaPlanaRadaRepository stavkaPlanaRadaRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "stavkaPlanaRadaNextId", method = RequestMethod.GET)
	public int getNextId() {		
        Integer sledeci = jdbcTemplate.queryForObject("SELECT max(id)+1 "
													+ "FROM stavka_plana_rada", Integer.class);
        if (sledeci == null)
        	sledeci = new Integer(1);
        return sledeci.intValue();
	}
	
	@RequestMapping(value = "stavkaPlanaRadaNextRedniBroj/{idPlanaRada}", method = RequestMethod.GET)
	public int getNextRedniBroj(@PathVariable("idPlanaRada") int idPlanaRada) {		
        Integer sledeci = jdbcTemplate.queryForObject("SELECT max(redni_broj)+1 "
													+ "FROM stavka_plana_rada "
													+ "where plan_rada="+idPlanaRada, Integer.class);
        if (sledeci == null)
        	sledeci = new Integer(1);
        return sledeci.intValue();
	}
	
	@RequestMapping(value="stavkaPlanaRada/stavkeZaPlanRadaId/{id}", method=RequestMethod.GET)
	public Collection<StavkaPlanaRada> stavkaPoPlanRadaId(@PathVariable("id") int id){		
		Optional<PlanRada> p = planRadaRepository.findById(id);
		return stavkaPlanaRadaRepository.findByPlanRada(p);		
	}
		
	@RequestMapping(value="stavkaPlanaRada", method=RequestMethod.GET)
	public Collection<StavkaPlanaRada> getAll(){		
		return stavkaPlanaRadaRepository.findAll();
	}
	
	@RequestMapping(value="stavkaPlanaRada/{id}",method=RequestMethod.GET)
	public ResponseEntity<StavkaPlanaRada> get(@PathVariable("id") int id) {
		Optional<StavkaPlanaRada> obj = stavkaPlanaRadaRepository.findById(id);
		return new ResponseEntity(obj, HttpStatus.OK);
	}
	
	@RequestMapping(value = "stavkaPlanaRada", method = RequestMethod.POST)
    public ResponseEntity<Void> insertStavkaPlanaRada(@RequestBody StavkaPlanaRada obj) {
        if (stavkaPlanaRadaRepository.existsById(obj.getId())) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
		stavkaPlanaRadaRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

	@RequestMapping(value = "stavkaPlanaRada/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateStavkaPlanaRada(@PathVariable("id") Integer id, @RequestBody StavkaPlanaRada obj) {
        if (!stavkaPlanaRadaRepository.existsById(id)) { 
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        stavkaPlanaRadaRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

	@RequestMapping(value="stavkaPlanaRada/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteStavkaPlanaRada(@PathVariable("id") int id) {
        if (!stavkaPlanaRadaRepository.existsById(id)) { 
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
		stavkaPlanaRadaRepository.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
