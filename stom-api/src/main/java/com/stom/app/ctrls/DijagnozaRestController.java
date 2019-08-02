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
import com.stom.app.jpa.Dijagnoza;
import com.stom.app.reps.DijagnozaRepository;

@RestController
@CrossOrigin
public class DijagnozaRestController {
	
	@Autowired
	private DijagnozaRepository dijagnozaRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "dijagnozaNextId", method = RequestMethod.GET)
	public int getNextId() {		
        Integer sledeci = jdbcTemplate.queryForObject("SELECT max(id)+1 "
													+ "FROM dijagnoza", Integer.class);
        if (sledeci == null)
        	sledeci = new Integer(1);
        return sledeci.intValue();
	}
	
	@RequestMapping(value = "dijagnoza", method = RequestMethod.GET)
	public Collection<Dijagnoza> getAll() {
		return dijagnozaRepository.findAll();
	}
	
	@RequestMapping(value = "dijagnoza/{id}", method = RequestMethod.GET)
    public ResponseEntity<Dijagnoza> get(@PathVariable("id") int id) {
        Optional<Dijagnoza> obj = dijagnozaRepository.findById(id);
        return new ResponseEntity(obj, HttpStatus.OK);
    }
		
	@RequestMapping(value = "dijagnoza", method = RequestMethod.POST)
    public ResponseEntity<Void> insertDijagnoza(@RequestBody Dijagnoza obj) {
        if (dijagnozaRepository.existsById(obj.getId())) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
		dijagnozaRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@RequestMapping(value = "dijagnoza/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateDijagnoza(@PathVariable("id") Integer id, @RequestBody Dijagnoza obj) {
        if (!dijagnozaRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        dijagnozaRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@RequestMapping(value = "dijagnoza/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteDijagnoza(@PathVariable("id") Integer id) {
        if (!dijagnozaRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } 
		dijagnozaRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
