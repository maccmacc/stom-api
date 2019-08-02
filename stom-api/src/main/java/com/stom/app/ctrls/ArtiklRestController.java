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

import com.stom.app.jpa.Artikl;
import com.stom.app.reps.ArtiklRepository;

@RestController
@CrossOrigin
public class ArtiklRestController {
	
	@Autowired
	private ArtiklRepository artiklRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "artiklNextId", method = RequestMethod.GET)
	public int getNextId() {		
        Integer sledeci = jdbcTemplate.queryForObject("SELECT max(id)+1 "
													+ "FROM artikl", Integer.class);
        if (sledeci == null)
        	sledeci = new Integer(1);
        return sledeci.intValue();
	}
	
	@RequestMapping(value = "artikl", method = RequestMethod.GET)
	public Collection<Artikl> getAll() {
		return artiklRepository.findAll();
	}
	
	@RequestMapping(value = "artikl/{id}", method = RequestMethod.GET)
    public ResponseEntity<Artikl> get(@PathVariable("id") int id) {
		Optional<Artikl> obj = artiklRepository.findById(id);
        return new ResponseEntity(obj, HttpStatus.OK);
    }
	
	@RequestMapping(value = "artikl", method = RequestMethod.POST)
    public ResponseEntity<Void> insertArtikl(@RequestBody Artikl obj) {
        if (artiklRepository.existsById(obj.getId())) { 
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
		artiklRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@RequestMapping(value = "artikl/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateArtikl(@PathVariable("id") Integer id, @RequestBody Artikl obj) {
        if (!artiklRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        artiklRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@RequestMapping(value = "artikl/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteArtikl(@PathVariable("id") Integer id) {
        if (!artiklRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
		artiklRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
