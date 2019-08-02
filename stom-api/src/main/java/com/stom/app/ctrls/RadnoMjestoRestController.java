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
import com.stom.app.jpa.RadnoMjesto;
import com.stom.app.reps.RadnoMjestoRepository;

@RestController
@CrossOrigin
public class RadnoMjestoRestController {
	
	@Autowired
	private RadnoMjestoRepository radnoMjestoRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "radnoMjestoNextId", method = RequestMethod.GET)
	public int getNextId() {		
        Integer sledeci = jdbcTemplate.queryForObject("SELECT max(id)+1 "
													+ "FROM radno_mjesto", Integer.class);
        if (sledeci == null)
        	sledeci = new Integer(1);
        return sledeci.intValue();
	}
	
	@RequestMapping(value = "radnoMjesto", method = RequestMethod.GET)
	public Collection<RadnoMjesto> getAll() {
		return radnoMjestoRepository.findAll();
	}
	
	@RequestMapping(value = "radnoMjesto/{id}", method = RequestMethod.GET)
    public ResponseEntity<RadnoMjesto> get(@PathVariable("id") int id) {
        Optional<RadnoMjesto> obj = radnoMjestoRepository.findById(id);
        return new ResponseEntity(obj, HttpStatus.OK);
    }
		
	@RequestMapping(value = "radnoMjesto", method = RequestMethod.POST)
    public ResponseEntity<Void> inserRadnoMjesto(@RequestBody RadnoMjesto obj) {
        if (radnoMjestoRepository.existsById(obj.getId())) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }		
		radnoMjestoRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@RequestMapping(value = "radnoMjesto/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateRadnoMjesto(@PathVariable("id") int id, @RequestBody RadnoMjesto obj) {
        if (!radnoMjestoRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        radnoMjestoRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@RequestMapping(value = "radnoMjesto/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteRadnoMjesto(@PathVariable("id") Integer id) {
        if (!radnoMjestoRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }		
		radnoMjestoRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
