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

import com.stom.app.jpa.Pacijent;
import com.stom.app.jpa.PlanRada;
import com.stom.app.reps.PacijentRepository;
import com.stom.app.reps.PlanRadaRepository;

@RestController
public class PlanRadaRestController {
	
	@Autowired
	private PlanRadaRepository planRadaRepository;

	@Autowired
	private PacijentRepository pacijentRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "planRadaNextId", method = RequestMethod.GET)
	public int getNextId() {		
        Integer sledeci = jdbcTemplate.queryForObject("SELECT max(id)+1 "
													+ "FROM plan_rada", Integer.class);
        if (sledeci == null)
        	sledeci = new Integer(1);
        return sledeci.intValue();
	}
	
	@RequestMapping(value = "planRada", method = RequestMethod.GET)
	public Collection<PlanRada> getAll() {
		return planRadaRepository.findAll();
	}
	
	@RequestMapping(value = "planRada/{id}", method = RequestMethod.GET)
    public ResponseEntity<PlanRada> get(@PathVariable("id") int id) {
		Optional<PlanRada> obj = planRadaRepository.findById(id);
        return new ResponseEntity(obj, HttpStatus.OK);
    }
	
	@RequestMapping(value = "planRadaZaPacijentId/{idPacijenta}", method = RequestMethod.GET)
	public Collection<PlanRada> getPlanRadaZaPacijentId(@PathVariable("idPacijenta") int idPacijenta) {
		Optional<Pacijent> pacijent = pacijentRepository.findById(idPacijenta);
		return planRadaRepository.findByPacijent(pacijent);
	}
		
	@CrossOrigin
	@RequestMapping(value = "planRada", method = RequestMethod.POST)
    public ResponseEntity<Void> insertPlanRada(@RequestBody PlanRada obj) {
        if (planRadaRepository.existsById(obj.getId())) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }		
		planRadaRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@CrossOrigin
	@RequestMapping(value = "planRada/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updatePlanRada(@PathVariable("id") Integer id, @RequestBody PlanRada obj) {
        if (!planRadaRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        planRadaRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@CrossOrigin
	@RequestMapping(value = "planRada/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePlanRada(@PathVariable("id") Integer id) {
        if (!planRadaRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }		
		planRadaRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }	
}
