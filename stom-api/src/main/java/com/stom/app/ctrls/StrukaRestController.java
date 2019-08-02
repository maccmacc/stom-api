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
import com.stom.app.jpa.Struka;
import com.stom.app.reps.StrukaRepository;

@RestController
public class StrukaRestController {
	
	@Autowired
	private StrukaRepository strukaRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "strukaNextId", method = RequestMethod.GET)
	public int getNextId() {		
        Integer sledeci = jdbcTemplate.queryForObject("SELECT max(id)+1 "
													+ "FROM struka", Integer.class);
        if (sledeci == null)
        	sledeci = new Integer(1);
        return sledeci.intValue();
	}
	
	@RequestMapping(value = "struka", method = RequestMethod.GET)
	public Collection<Struka> getAll() {
		return strukaRepository.findAll();
	}
	
	@RequestMapping(value = "struka/{id}", method = RequestMethod.GET)
    public ResponseEntity<Struka> get(@PathVariable("id") int id) {
        Optional<Struka> obj = strukaRepository.findById(id);
        return new ResponseEntity(obj, HttpStatus.OK);
    }
		
	@CrossOrigin
	@RequestMapping(value = "struka", method = RequestMethod.POST)
    public ResponseEntity<Void> insertStruka(@RequestBody Struka obj) {
        if (strukaRepository.existsById(obj.getId())) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }		
		strukaRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@CrossOrigin
	@RequestMapping(value = "struka/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateStruka(@PathVariable("id") Integer id, @RequestBody Struka obj) {
        if (!strukaRepository.existsById(obj.getId())) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        strukaRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@CrossOrigin
	@RequestMapping(value = "struka/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteStruka(@PathVariable("id") Integer id) {
        if (!strukaRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }		
		strukaRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
