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
import com.stom.app.jpa.Pacijent;
import com.stom.app.jpa.Status;
import com.stom.app.jpa.Zub;
import com.stom.app.reps.PacijentRepository;
import com.stom.app.reps.StatusRepository;
import com.stom.app.reps.ZubRepository;

@RestController
@CrossOrigin
public class ZubRestController {
	
	@Autowired
	private ZubRepository zubRepository;

	@Autowired
	private StatusRepository statusRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private PacijentRepository pacijentRepository;
	
	@RequestMapping(value = "zubNextId", method = RequestMethod.GET)
	public int getNextId() {		
        Integer sledeci = jdbcTemplate.queryForObject("SELECT max(id)+1 "
													+ "FROM zub", Integer.class);
        if (sledeci == null)
        	sledeci = new Integer(1);
        return sledeci.intValue();
	}
	
	@RequestMapping(value = "zubPoPacijentu/{idPacijenta}", method = RequestMethod.GET)
	public Collection<Zub> getZubZaPacijentId(@PathVariable("idPacijenta") int idPacijenta) {
		Pacijent pacijent = pacijentRepository.findById(idPacijenta).orElseThrow(() -> new RuntimeException());
		Collection<Zub> zubi = zubRepository.findByPacijent(pacijent);
		if (zubi.isEmpty()) {						
			for (int i = 1; i < 5; i++) {
				for (int j = 1; j < 9; j++) {
					int oznakaZuba = 10*i+j;
					Zub z = new Zub();
					z.setId(this.getNextId());
					z.setZub(oznakaZuba);
					z.setPacijent(pacijent);
					Status status = statusRepository.getOne(1);
					z.setStatus1(status);
					z.setStatus2(status);
					zubRepository.saveAndFlush(z);
				}
			}
		}
		return zubi;
	}
		
	@RequestMapping(value = "zub", method = RequestMethod.GET)
	public Collection<Zub> getAll() {
		return zubRepository.findAll();
	}
	
	@RequestMapping(value = "zub/{id}", method = RequestMethod.GET)
    public ResponseEntity<Zub> get(@PathVariable("id") int id) {
		Optional<Zub> obj = zubRepository.findById(id);
        return new ResponseEntity(obj, HttpStatus.OK);
    }
		
	@RequestMapping(value = "zub", method = RequestMethod.POST)
    public ResponseEntity<Void> insertZub(@RequestBody Zub obj) {
        if (zubRepository.existsById(obj.getId())) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }		
		zubRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

	@CrossOrigin
	@RequestMapping(value = "zub/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateZub(@PathVariable("id") Integer id, @RequestBody Zub obj) {
        if (!zubRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        zubRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@RequestMapping(value = "zub/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteZub(@PathVariable("id") Integer id) {
        if (!zubRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }		
		zubRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }	
}
