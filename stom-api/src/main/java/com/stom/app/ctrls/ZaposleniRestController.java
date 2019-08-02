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
import com.stom.app.jpa.Zaposleni;
import com.stom.app.reps.ZaposleniRepository;

@RestController
public class ZaposleniRestController {
	
	@Autowired
	private ZaposleniRepository zaposleniRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "zaposleniNextId", method = RequestMethod.GET)
	public int getNextId() {		
        Integer sledeci = jdbcTemplate.queryForObject("SELECT max(id)+1 "
													+ "FROM zaposleni", Integer.class);
        if (sledeci == null)
        	sledeci = new Integer(1);
        return sledeci.intValue();
	}
	
	@RequestMapping(value = "zaposleni", method = RequestMethod.GET)
	public Collection<Zaposleni> getAll() {
		return zaposleniRepository.findAll();
	}
	
	@RequestMapping(value = "zaposleni/{id}", method = RequestMethod.GET)
    public ResponseEntity<Zaposleni> get(@PathVariable("id") int id) {
		Optional<Zaposleni> obj = zaposleniRepository.findById(id);
        return new ResponseEntity(obj, HttpStatus.OK);
    }
	
	@CrossOrigin
	@RequestMapping(value = "zaposleni", method = RequestMethod.POST)
    public ResponseEntity<Void> insertZaposleni(@RequestBody Zaposleni obj) {
        if (zaposleniRepository.existsById(obj.getId())) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
		zaposleniRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@CrossOrigin
	@RequestMapping(value = "zaposleni/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateZaposleni(@PathVariable("id") int id, @RequestBody Zaposleni obj) {
        if (!zaposleniRepository.existsById(obj.getId())) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        zaposleniRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }	
	
	@CrossOrigin
	@RequestMapping(value = "zaposleni/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteZaposleni(@PathVariable("id") Integer id) {
        if (!zaposleniRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
		zaposleniRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}