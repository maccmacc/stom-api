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
import com.stom.app.jpa.Snimak;
import com.stom.app.reps.PacijentRepository;
import com.stom.app.reps.SnimakRepository;

@RestController
@CrossOrigin
public class SnimakRestController {

	@Autowired
	private SnimakRepository snimakRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private PacijentRepository pacijentRepository;	

	@RequestMapping(value = "snimakNextId", method = RequestMethod.GET)
	public int getNextId() {		
		Integer sledeci = jdbcTemplate.queryForObject("SELECT max(id)+1 "
													+ "FROM snimak", Integer.class);
		if (sledeci == null)
			sledeci = new Integer(1);
		return sledeci.intValue();
	}

	@RequestMapping(value = "snimak", method = RequestMethod.GET)
	public Collection<Snimak> getAll() {		
		return snimakRepository.findAll();
	}	

	@RequestMapping(value = "snimak/{id}", method = RequestMethod.GET)
	public ResponseEntity<Snimak> get(@PathVariable int id) {		
        Optional<Snimak> obj = snimakRepository.findById(id);
        return new ResponseEntity(obj, HttpStatus.OK);
	}	

	@RequestMapping(value = "snimakZaPacijentId/{idPacijenta}", method = RequestMethod.GET)
	public Collection<Snimak> getSnimakZaPacijentId(@PathVariable int idPacijenta) {
		Optional<Pacijent> pacijent = pacijentRepository.findById(idPacijenta);
		return snimakRepository.findByPacijent(pacijent.get());
	}

	@RequestMapping(value = "snimak", method = RequestMethod.POST)
	public ResponseEntity<Void> insertSnimak(@RequestBody Snimak obj) {
		if (snimakRepository.existsById(obj.getId())) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}		
		snimakRepository.save(obj);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "snimak/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateSnimak(@PathVariable("id") int id, @RequestBody Snimak obj) {
		if (!snimakRepository.existsById(id)) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		snimakRepository.save(obj);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}	

	@RequestMapping(value = "snimak/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteSnimak(@PathVariable int id) {		
		if (!snimakRepository.existsById(id)) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		snimakRepository.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}	
}
