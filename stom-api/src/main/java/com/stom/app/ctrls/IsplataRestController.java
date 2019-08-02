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
import com.stom.app.jpa.Isplata;
import com.stom.app.reps.IsplataRepository;

@RestController
public class IsplataRestController {
	
	@Autowired
	private IsplataRepository isplataRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "isplataNextId", method = RequestMethod.GET)
	public int getNextId() {		
        Integer sledeci = jdbcTemplate.queryForObject("SELECT max(id)+1 "
													+ "FROM isplata", Integer.class);
        if (sledeci == null)
        	sledeci = new Integer(1);
        return sledeci.intValue();
	}
	
	@RequestMapping(value = "isplata", method = RequestMethod.GET)
	public Collection<Isplata> getAll() {
		return isplataRepository.findAllByOrderByIdDesc();
		
	}
	
	@RequestMapping(value = "isplata/{id}", method = RequestMethod.GET)
    public ResponseEntity<Isplata> get(@PathVariable("id") int id) {
		
		Optional<Isplata> obj = isplataRepository.findById(id);
        return new ResponseEntity(obj, HttpStatus.OK);
    }
	
	@CrossOrigin
	@RequestMapping(value = "isplata", method = RequestMethod.POST)
    public ResponseEntity<Void> insertIsplata(@RequestBody Isplata obj) {
        if (isplataRepository.existsById(obj.getId())) { 
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }		
		isplataRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@CrossOrigin
	@RequestMapping(value = "isplata/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateIsplata(@PathVariable("id") int id, @RequestBody Isplata obj) {
        if (!isplataRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        isplataRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@CrossOrigin
	@RequestMapping(value = "isplata/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteIsplata(@PathVariable("id") Integer id) {
        if (!isplataRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
		isplataRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
