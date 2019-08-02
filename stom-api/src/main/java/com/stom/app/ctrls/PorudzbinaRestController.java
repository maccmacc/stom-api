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
import com.stom.app.jpa.Porudzbina;
import com.stom.app.reps.PorudzbinaRepository;


@RestController
@CrossOrigin
public class PorudzbinaRestController {
	
	@Autowired
	PorudzbinaRepository porudzbinaRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "porudzbinaNextId", method = RequestMethod.GET)
	public int getNextId() {		
        Integer sledeci = jdbcTemplate.queryForObject("SELECT max(id)+1 "
													+ "FROM porudzbina", Integer.class);
        if (sledeci == null)
        	sledeci = new Integer(1);
        return sledeci.intValue();
	}
	
	@RequestMapping(value= "porudzbina", method=RequestMethod.GET)
	public Collection<Porudzbina> getAll(){		
		return porudzbinaRepository.findAll();		
	}
	
	@RequestMapping(value="porudzbina/{id}", method=RequestMethod.GET)
	public ResponseEntity<Porudzbina> get(@PathVariable("id") int id){
		Optional<Porudzbina> obj=porudzbinaRepository.findById(id);
		return new ResponseEntity(obj,HttpStatus.OK);
	}

	@RequestMapping(value="porudzbina", method=RequestMethod.POST)
	public ResponseEntity<Void> insertPorudzbina(@RequestBody Porudzbina obj){
		if (porudzbinaRepository.existsById(obj.getId())){
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}			
		porudzbinaRepository.save(obj);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="porudzbina/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> updatePorudzbina(@PathVariable("id") Integer id, @RequestBody Porudzbina obj){
		if(!porudzbinaRepository.existsById(id)){
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}		
		porudzbinaRepository.save(obj);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="porudzbina/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletePorudzbina(@PathVariable("id") int id) {		
		if(!porudzbinaRepository.existsById(id)){
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}				
		porudzbinaRepository.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}	
}
