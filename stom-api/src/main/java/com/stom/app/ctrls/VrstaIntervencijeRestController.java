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
import com.stom.app.jpa.VrstaIntervencije;
import com.stom.app.reps.VrstaIntervencijeRepository;

@RestController
@CrossOrigin
public class VrstaIntervencijeRestController {

	@Autowired
	private VrstaIntervencijeRepository vrstaIntervencijeRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "vrstaIntervencijeNextId", method = RequestMethod.GET)
	public int getNextId() {		
        Integer sledeci = jdbcTemplate.queryForObject("SELECT max(id)+1 "
													+ "FROM vrsta_intervencije", Integer.class);
        if (sledeci == null)
        	sledeci = new Integer(1);        
        return sledeci.intValue();
	}

	@RequestMapping(value = "vrstaIntervencije", method = RequestMethod.GET)
	public Collection<VrstaIntervencije> getAll() {
		return vrstaIntervencijeRepository.findAll();
	}

	@RequestMapping(value = "vrstaIntervencije/{id}", method = RequestMethod.GET)
    public ResponseEntity<VrstaIntervencije> get(@PathVariable("id") int id) {
		Optional<VrstaIntervencije> obj = vrstaIntervencijeRepository.findById(id);
        return new ResponseEntity(obj, HttpStatus.OK);
    }
	
	@RequestMapping(value = "vrstaIntervencije", method = RequestMethod.POST)
    public ResponseEntity<Void> insertVrstaIntervencije(@RequestBody VrstaIntervencije obj) {
        if (vrstaIntervencijeRepository.existsById(obj.getId())) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
		vrstaIntervencijeRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@RequestMapping(value = "vrstaIntervencije/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateVrstaIntervencije(@PathVariable("id") Integer id, @RequestBody VrstaIntervencije obj) {
        if (!vrstaIntervencijeRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        vrstaIntervencijeRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@RequestMapping(value = "vrstaIntervencije/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteVrstaIntervencije(@PathVariable("id") Integer id) {
        if (!vrstaIntervencijeRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }		
		vrstaIntervencijeRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
