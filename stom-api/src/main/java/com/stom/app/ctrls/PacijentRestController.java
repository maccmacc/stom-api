package com.stom.app.ctrls;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.stom.app.dtos.response.PacijentDetaljiResponse;
import com.stom.app.service.IPacijentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.stom.app.jpa.Pacijent;
import com.stom.app.reportrecords.NoviPacijentiPoAdresiMjesecno;
import com.stom.app.reps.PacijentRepository;

@RestController
@CrossOrigin
public class PacijentRestController {
	
	@Autowired
	private PacijentRepository pacijentRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private IPacijentService _pacijentService;
	
	@RequestMapping(value = "pacijentNextId", method = RequestMethod.GET)
	public int getNextId() {		
        Integer sledeci = jdbcTemplate.queryForObject("SELECT max(id)+1 "
													+ "FROM pacijent", Integer.class);
        if (sledeci == null)
        	sledeci = new Integer(1);
        return sledeci.intValue();
	}

	@RequestMapping(value = "noviPacijentiPoAdresiMjesecno", method = RequestMethod.GET)
	public List<NoviPacijentiPoAdresiMjesecno> getNoviPacijentiPoAdresiMjesecno() {		
        String upit = "select EXTRACT(MONTH from datum_upisa) mjesec, EXTRACT(YEAR from datum_upisa) godina, adresa, count(*) novih_pacijenata "
        		+ "from pacijent "
        		+ "group by mjesec, godina, adresa "
        		+ "order by godina desc, mjesec desc, adresa";             
        List<NoviPacijentiPoAdresiMjesecno> novi = jdbcTemplate.query(upit,
        		new RowMapper<NoviPacijentiPoAdresiMjesecno>() {
            		public NoviPacijentiPoAdresiMjesecno mapRow(ResultSet rs, int rowNum) throws SQLException {
            			NoviPacijentiPoAdresiMjesecno n = new NoviPacijentiPoAdresiMjesecno();
		                n.setMjesec(rs.getInt(1));
		                n.setGodina(rs.getInt(2));
		                n.setAdresa(rs.getString(3));
		                n.setNovihPacijenata(rs.getInt(4));
		                return n;
		            }
		        });
        return novi;
	}	
	
	@RequestMapping(value = "pacijent", method = RequestMethod.GET)
	public Collection<Pacijent> getAll() {
		//return pacijentRepository.findAll();
		return pacijentRepository.findAllByOrderByIdDesc();
	}

	@CrossOrigin
	@GetMapping(value = "pacijentPage", params = { "page", "size" })
	public Page<Pacijent> getPacijent(@RequestParam("page") int page, @RequestParam("size") int size) {		
		return pacijentRepository.findAll(PageRequest.of(page, size));
	}
	
	@RequestMapping(value = "pacijentImeLike/{ime}Page", method = RequestMethod.GET)
	public Page<Pacijent> getPacijentImeLike(@PathVariable String ime, Pageable p) {
		return pacijentRepository.findByImeContainingIgnoreCase(ime, p);
	}

	@RequestMapping(value = "pacijentPrezimeLike/{prezime}Page", method = RequestMethod.GET)
	public Page<Pacijent> getPacijentPrezimeLike(@PathVariable String prezime, Pageable p) {
		return pacijentRepository.findByPrezimeContainingIgnoreCase(prezime, p);
	}
	
	@RequestMapping(value = "pacijent/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pacijent> get(@PathVariable("id") int id) {
		Optional<Pacijent> obj = pacijentRepository.findById(id);
        return new ResponseEntity(obj, HttpStatus.OK);
    }
		
	@RequestMapping(value = "pacijent", method = RequestMethod.POST)
    public ResponseEntity<Void> insertPacijent(@RequestBody Pacijent obj) {
        if (pacijentRepository.existsById(obj.getId())) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }		
		pacijentRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

	@RequestMapping(value = "pacijent/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updatePacijent(@PathVariable("id") int id, @RequestBody Pacijent obj) {
        if (!pacijentRepository.existsById(obj.getId())) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        pacijentRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@RequestMapping(value = "pacijent/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePacijent(@PathVariable("id") Integer id) {
        if (!pacijentRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }		
		pacijentRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("pacijenti/{id}")
	public ResponseEntity getPacijentDetalji(@PathVariable Integer id) {
		PacijentDetaljiResponse response = _pacijentService.getPacijentById(id);
		if (response == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<PacijentDetaljiResponse>(response, HttpStatus.OK);
	}
}
