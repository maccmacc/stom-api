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
import com.stom.app.jpa.Slika;
import com.stom.app.reps.SlikaRepository;

@RestController
@CrossOrigin
public class SlikaRestController {
	
	@Autowired
	private SlikaRepository slikaRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "slikaNextId", method = RequestMethod.GET)
	public int getNextId() {		
        Integer sledeci = jdbcTemplate.queryForObject("SELECT max(id)+1 "
													+ "FROM slika", Integer.class);
        if (sledeci == null)
        	sledeci = new Integer(1);
        return sledeci.intValue();
	}
	
	@RequestMapping(value = "slika", method = RequestMethod.GET)
	public Collection<Slika> getAll() {
		return slikaRepository.findAll();
	}
	
	@RequestMapping(value = "slika/{id}", method = RequestMethod.GET)
    public ResponseEntity<Slika> get(@PathVariable("id") int id) {
        Optional<Slika> obj = slikaRepository.findById(id);
        return new ResponseEntity(obj, HttpStatus.OK);
    }
	
	@RequestMapping(value = "slika", method = RequestMethod.POST)
    public ResponseEntity<Void> insertSlika(@RequestBody Slika obj) {
        if (slikaRepository.existsById(obj.getId())) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
		slikaRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@RequestMapping(value = "slika/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateSlika(@PathVariable("id") int id, @RequestBody Slika obj) {
        if (!slikaRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        slikaRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@RequestMapping(value = "slika/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteSlika(@PathVariable("id") Integer id) {
        if (!slikaRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }		
		slikaRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
