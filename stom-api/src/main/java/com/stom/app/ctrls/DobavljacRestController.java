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
import com.stom.app.jpa.Dobavljac;
import com.stom.app.reps.DobavljacRepository;

@RestController
@CrossOrigin
public class DobavljacRestController {
	
	@Autowired
	private DobavljacRepository dobavljacRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "dobavljacNextId", method = RequestMethod.GET)
	public int getNextId() {		
        Integer sledeci = jdbcTemplate.queryForObject("SELECT max(id)+1 "
													+ "FROM dobavljac", Integer.class);
        if (sledeci == null)
        	sledeci = new Integer(1);
        return sledeci.intValue();
	}
	
	
	@RequestMapping(value = "dobavljac", method = RequestMethod.GET)
	public Collection<Dobavljac> getAll() {
		return dobavljacRepository.findAll();
	}
	
	@RequestMapping(value = "dobavljac/{id}", method = RequestMethod.GET)
    public ResponseEntity<Dobavljac> get(@PathVariable("id") int id) {
		Optional<Dobavljac> obj = dobavljacRepository.findById(id);
        return new ResponseEntity(obj, HttpStatus.OK);
    }
		
	@RequestMapping(value = "dobavljac", method = RequestMethod.POST)
    public ResponseEntity<Void> insertDobavljac(@RequestBody Dobavljac obj) {
        if (dobavljacRepository.existsById(obj.getId())) { 
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
		dobavljacRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@RequestMapping(value = "dobavljac/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateDobavljac(@PathVariable("id") Integer id, @RequestBody Dobavljac obj) {
        if (!dobavljacRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        dobavljacRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@RequestMapping(value = "dobavljac/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteDobavljac(@PathVariable("id") Integer id) {
        if (!dobavljacRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
		dobavljacRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
