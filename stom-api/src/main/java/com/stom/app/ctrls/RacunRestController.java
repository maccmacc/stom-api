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
import com.stom.app.jpa.Racun;
import com.stom.app.reps.RacunRepository;

@RestController
@CrossOrigin
public class RacunRestController {
	
	@Autowired
	private RacunRepository racunRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "racunNextId", method = RequestMethod.GET)
	public int getNextId() {		
        Integer sledeci = jdbcTemplate.queryForObject("SELECT max(id)+1 "
													+ "FROM racun", Integer.class);
        if (sledeci == null)
        	sledeci = new Integer(1);
        return sledeci.intValue();
	}
	
	@RequestMapping(value = "racun", method = RequestMethod.GET)
	public Collection<Racun> getAll() {
		return racunRepository.findAll();
	}
	
	@RequestMapping(value = "racun/{id}", method = RequestMethod.GET)
    public ResponseEntity<Racun> get(@PathVariable("id") int id) {
		Optional<Racun> obj = racunRepository.findById(id);
        return new ResponseEntity(obj, HttpStatus.OK);
    }

	@RequestMapping(value = "racun", method = RequestMethod.POST)
    public ResponseEntity<Void> insertRacun(@RequestBody Racun obj) {
        if (racunRepository.existsById(obj.getId())) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
		racunRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@RequestMapping(value = "racun/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateRacun(@PathVariable("id") Integer id, @RequestBody Racun obj) {
        if (!racunRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        racunRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@RequestMapping(value = "racun/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteRacun(@PathVariable("id") Integer id) {
        if (!racunRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }		
		racunRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
