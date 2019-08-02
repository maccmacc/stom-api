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
import com.stom.app.jpa.Materijal;
import com.stom.app.reps.MaterijalRepository;

@RestController
public class MaterijalRestController {
	
	@Autowired
	private MaterijalRepository materijalRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "materijalNextId", method = RequestMethod.GET)
	public int getNextId() {		
        Integer sledeci = jdbcTemplate.queryForObject("SELECT max(id)+1 "
													+ "FROM materijal", Integer.class);
        if (sledeci == null)
        	sledeci = new Integer(1);
        return sledeci.intValue();
	}
	
	@RequestMapping(value = "materijal", method = RequestMethod.GET)
	public Collection<Materijal> getAll() {
		return materijalRepository.findAll();
	}
	
	@RequestMapping(value = "materijal/{id}", method = RequestMethod.GET)
    public ResponseEntity<Materijal> get(@PathVariable("id") int id) {
        Optional<Materijal> obj = materijalRepository.findById(id);
        return new ResponseEntity(obj, HttpStatus.OK);
    }
		
	@CrossOrigin
	@RequestMapping(value = "materijal", method = RequestMethod.POST)
    public ResponseEntity<Void> insertMaterijal(@RequestBody Materijal obj) {
        if (!materijalRepository.existsById(obj.getId())) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }		
		materijalRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@CrossOrigin
	@RequestMapping(value = "materijal/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateMaterijal(@PathVariable("id") int id, @RequestBody Materijal obj) {
        if (!materijalRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        materijalRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@CrossOrigin
	@RequestMapping(value = "materijal/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteMaterijal(@PathVariable("id") Integer id) {
        if (!materijalRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }		
		materijalRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}