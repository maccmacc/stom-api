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
import com.stom.app.jpa.Status;
import com.stom.app.reps.StatusRepository;

@RestController
@CrossOrigin
public class StatusRestController {
	
	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "statusNextId", method = RequestMethod.GET)
	public int getNextId() {		
        Integer sledeci = jdbcTemplate.queryForObject("SELECT max(id)+1 "
													+ "FROM status", Integer.class);
        if (sledeci == null)
        	sledeci = new Integer(1);
        return sledeci.intValue();
	}
	
	@RequestMapping(value = "status", method = RequestMethod.GET)
	public Collection<Status> getAll() {
		return statusRepository.findAll();
	}
	
	@RequestMapping(value = "status/{id}", method = RequestMethod.GET)
    public ResponseEntity<Status> get(@PathVariable("id") int id) {
        Optional<Status> obj = statusRepository.findById(id);
        return new ResponseEntity(obj, HttpStatus.OK);
    }
		
	@RequestMapping(value = "status", method = RequestMethod.POST)
    public ResponseEntity<Void> insertStatus(@RequestBody Status obj) {
        if (statusRepository.existsById(obj.getId())) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
		statusRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@RequestMapping(value = "status/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateStatus(@PathVariable("id") Integer id, @RequestBody Status obj) {
        if (!statusRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        statusRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@RequestMapping(value = "status/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteStatus(@PathVariable("id") Integer id) {
        if (!statusRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }	
		statusRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
