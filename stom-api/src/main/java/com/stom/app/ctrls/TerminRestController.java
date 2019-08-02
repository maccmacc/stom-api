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
import com.stom.app.jpa.Termin;
import com.stom.app.reps.TerminRepository;

@RestController
@CrossOrigin
public class TerminRestController {
	
	@Autowired
	private TerminRepository terminRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "terminNextId", method = RequestMethod.GET)
	public int getNextId() {		
        Integer sledeci = jdbcTemplate.queryForObject("SELECT max(id)+1 "
													+ "FROM termin", Integer.class);
        if (sledeci == null)
        	sledeci = new Integer(1);
        return sledeci.intValue();
	}
	
	@RequestMapping(value = "termin", method = RequestMethod.GET)
	public Collection<Termin> getAll() {
		return terminRepository.findAll();
		
	}
	
	@RequestMapping(value = "termin/{id}", method = RequestMethod.GET)
    public ResponseEntity<Termin> get(@PathVariable("id") int id) {
		Optional<Termin> obj = terminRepository.findById(id);
        return new ResponseEntity(obj, HttpStatus.OK);
    }
	
	@CrossOrigin
	@RequestMapping(value = "termin", method = RequestMethod.POST)
    public ResponseEntity<Void> insertTermin(@RequestBody Termin obj) {
        if (terminRepository.existsById(obj.getId())) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }		
		terminRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@CrossOrigin
	@RequestMapping(value = "termin/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateTermin(@PathVariable("id") Integer id, @RequestBody Termin obj) {
        if (!terminRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        terminRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@CrossOrigin
	@RequestMapping(value = "termin/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteTermin(@PathVariable("id") Integer id) {
        if (!terminRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }		
		terminRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
