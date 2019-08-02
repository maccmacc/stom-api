package com.stom.app.ctrls;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.stom.app.jpa.Termin;
import com.stom.app.reps.TerminRepository;

@RestController
@CrossOrigin
@RequestMapping("termin")
public class TerminRestController {
	
	@Autowired
	private TerminRepository terminRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private int getNextId() {		
        Integer sledeci = jdbcTemplate.queryForObject("SELECT max(id)+1 "
													+ "FROM termin", Integer.class);
        if (sledeci == null)
        	sledeci = new Integer(1);
        return sledeci.intValue();
	}
	
	@GetMapping
	public Collection<Termin> getAll() {
		return terminRepository.findAll();
	}
	
	@GetMapping(value = "/{id}")
    public ResponseEntity<Termin> get(@PathVariable("id") int id) {
		Optional<Termin> obj = terminRepository.findById(id);
        return new ResponseEntity(obj.get(), HttpStatus.OK);
    }
	
	@PostMapping
    public ResponseEntity<Void> insertTermin(@RequestBody Termin obj) {
		obj.setId(this.getNextId());
		terminRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateTermin(@PathVariable("id") Integer id, @RequestBody Termin obj) {
        if (!terminRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        terminRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTermin(@PathVariable("id") Integer id) {
        if (!terminRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }		
		terminRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
