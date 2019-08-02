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
import com.stom.app.jpa.Ordinacija;
import com.stom.app.reps.OrdinacijaRepository;

@RestController
@CrossOrigin
public class OrdinacijaRestController {
	
	@Autowired
	private OrdinacijaRepository ordinacijaRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "ordinacijaNextId", method = RequestMethod.GET)
	public int getNextId() {		
        Integer sledeci = jdbcTemplate.queryForObject("SELECT max(id)+1 "
													+ "FROM ordinacija", Integer.class);
        if (sledeci == null)
        	sledeci = new Integer(1);
        return sledeci.intValue();
	}
	
	@RequestMapping(value = "ordinacija", method = RequestMethod.GET)
	public Collection<Ordinacija> getAll() {
		return ordinacijaRepository.findAll();
	}
	
	@RequestMapping(value = "ordinacija/{id}", method = RequestMethod.GET)
    public ResponseEntity<Ordinacija> get(@PathVariable("id") int id) {
		Optional<Ordinacija> obj = ordinacijaRepository.findById(id);
        return new ResponseEntity(obj, HttpStatus.OK);
    }
		
	@RequestMapping(value = "ordinacija", method = RequestMethod.POST)
    public ResponseEntity<Void> insertOrdinacija(@RequestBody Ordinacija obj) {
        if (ordinacijaRepository.existsById(obj.getId())) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }		
		ordinacijaRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@RequestMapping(value = "ordinacija/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateOrdinacija(@PathVariable("id") int id, @RequestBody Ordinacija obj) {
        if (!ordinacijaRepository.existsById(obj.getId())) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        ordinacijaRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

	@RequestMapping(value = "ordinacija/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        if (!ordinacijaRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }		
		ordinacijaRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
