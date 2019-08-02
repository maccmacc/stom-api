package com.stom.app.ctrls;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.stom.app.jpa.IzvrsenaIntervencija;
import com.stom.app.jpa.Pacijent;
import com.stom.app.reportrecords.PacijentPlacenoPopust;
import com.stom.app.reportrecords.PrihodDnevni;
import com.stom.app.reportrecords.PrihodMjesecni;
import com.stom.app.reportrecords.PrihodPoRadnomMjestu;
import com.stom.app.reps.IzvrsenaIntervencijaRepository;
import com.stom.app.reps.PacijentRepository;

@RestController
@CrossOrigin
public class IzvrsenaIntervencijaRestController {
	
	@Autowired
	private IzvrsenaIntervencijaRepository izvrsenaIntervencijaRepository;

	@Autowired
	private PacijentRepository pacijentRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "izvrsenaIntervencijaNextId", method = RequestMethod.GET)
	public int getNextId() {		
        Integer sledeci = jdbcTemplate.queryForObject("SELECT max(id)+1 "
													+ "FROM izvrsena_intervencija", Integer.class);
        if (sledeci == null)
        	sledeci = new Integer(1);
        return sledeci.intValue();
	}
	
	@RequestMapping(value = "izvrsenaIntervencijaZaPacijentId/{idPacijenta}", method = RequestMethod.GET)
	public Collection<IzvrsenaIntervencija> getIzvrsenaIntervencijaZaPacijentId(@PathVariable("idPacijenta") int idPacijenta) {
		Optional<Pacijent> pacijent = pacijentRepository.findById(idPacijenta);
		return izvrsenaIntervencijaRepository.findByPacijentOrderByDatumDesc(pacijent);
	}
	
	@RequestMapping(value = "prihodPoRadnomMjestu", method = RequestMethod.GET)
	public Collection<PrihodPoRadnomMjestu> getPrihodPoRadomMjestu() {
		String upit = "select rm.naziv, EXTRACT(MONTH from datum) mjesec, EXTRACT(YEAR from datum) godina, sum(placeno) "
				+ "from izvrsena_intervencija, radno_mjesto rm "
				+ "where izvrsena_intervencija.radno_mjesto=rm.id "
				+ "group by rm.naziv, EXTRACT(MONTH from datum), EXTRACT(YEAR from datum)  "
				+ "order by EXTRACT(YEAR from datum) desc, EXTRACT(MONTH from datum) desc, naziv";
		List<PrihodPoRadnomMjestu> prihodi = jdbcTemplate.query(upit,
        		new RowMapper<PrihodPoRadnomMjestu>() {
            		public PrihodPoRadnomMjestu mapRow(ResultSet rs, int rowNum) throws SQLException {
            			PrihodPoRadnomMjestu p = new PrihodPoRadnomMjestu();
            			p.setRadnoMjesto(rs.getString(1));
		                p.setMjesec(rs.getInt(2));
		                p.setGodina(rs.getInt(3));
		                p.setPrihod(rs.getBigDecimal(4));
		                return p;
		            }
		        });
        return prihodi;
		
	}
	
	@RequestMapping(value = "prihodPoMjesecima", method = RequestMethod.GET)
	public List<PrihodMjesecni> getPrihodPoMjesecima() {		
        String upit = "select prihod.mjesec, prihod.godina, prihod.iznos - coalesce(rashod.iznos,0) prihod "
        		+ "from (select EXTRACT(MONTH from ii.datum) mjesec, EXTRACT(YEAR from ii.datum) godina, sum(ii.placeno) iznos "
        		+ "        		from izvrsena_intervencija ii "
        		+ "        		group by mjesec, godina) prihod "
        		+ "left outer join (select EXTRACT(MONTH from datum) mjesec, EXTRACT(YEAR from datum) godina, sum(coalesce(iznos,0)) iznos "
        		+ "					from isplata "
        		+ "                 group by mjesec, godina) rashod "
        		+ "ON prihod.mjesec=rashod.mjesec and prihod.godina=rashod.godina "
        		+ "order by prihod.godina desc, prihod.mjesec desc";             
        List<PrihodMjesecni> prihodi = jdbcTemplate.query(upit,
        		new RowMapper<PrihodMjesecni>() {
            		public PrihodMjesecni mapRow(ResultSet rs, int rowNum) throws SQLException {
            			PrihodMjesecni p = new PrihodMjesecni();
		                p.setMjesec(rs.getInt(1));
		                p.setGodina(rs.getInt(2));
		                p.setPrihod(rs.getBigDecimal(3));
		                return p;
		            }
		        });
        return prihodi;
	}
	
	@RequestMapping(value = "prihodPoDanima", method = RequestMethod.GET)
	public List<PrihodDnevni> getPrihodPoDanima() {		
        String upit = "select ii.datum, ii.iznos - coalesce(rashod.iznos, 0) prihod "
        		+ "from (select datum, sum(placeno) iznos "
        		+ "      from izvrsena_intervencija "
        		+ "      group by datum) ii "
        		+ "left outer join (select datum, sum(iznos) iznos "
        		+ "                 from isplata "
        		+ "                 group by datum) rashod "
        		+ "on ii.datum=rashod.datum "
        		+ "order by ii.datum desc";             
        List<PrihodDnevni> prihodi = jdbcTemplate.query(upit,
        		new RowMapper<PrihodDnevni>() {
            		public PrihodDnevni mapRow(ResultSet rs, int rowNum) throws SQLException {
            			PrihodDnevni p = new PrihodDnevni();
		                p.setDatum(rs.getDate(1));
		                p.setPrihod(rs.getBigDecimal(2));
		                return p;
		            }
		        });
        return prihodi;
	}

	@RequestMapping(value = "pacijentPlacenoPopust", method = RequestMethod.GET)
	public List<PacijentPlacenoPopust> pacijentPlacenoPopust() {		
        String upit = "select p.ime, p.prezime, sum(ii.placeno) placeno, sum(ii.popust) popust, sum(ii.popust)/sum(ii.iznos) udio "
        		+ "from izvrsena_intervencija ii, pacijent p "
        		+ "where ii.pacijent=p.id and ii.vrsta_intervencije<200 "
        		+ "group by p.ime, p.prezime "
        		+ "order by sum(ii.placeno) desc";             
        List<PacijentPlacenoPopust> prihodi = jdbcTemplate.query(upit,
        		new RowMapper<PacijentPlacenoPopust>() {
            		public PacijentPlacenoPopust mapRow(ResultSet rs, int rowNum) throws SQLException {
            			PacijentPlacenoPopust p = new PacijentPlacenoPopust();
		                p.setIme(rs.getString(1));
		                p.setPrezime(rs.getString(2));
		                p.setPlaceno(rs.getBigDecimal(3));
		                p.setPopust(rs.getBigDecimal(4));
		                p.setUdio(rs.getBigDecimal(5).setScale(2, BigDecimal.ROUND_HALF_EVEN));
		                return p;
		            }
		        });
        return prihodi;
	}
	
	@RequestMapping(value = "izvrsenaIntervencija", method = RequestMethod.GET)
	public Page<IzvrsenaIntervencija> getAll(Pageable p) {
		return izvrsenaIntervencijaRepository.findAll(p);
	}
	
	@RequestMapping(value = "izvrsenaIntervencija/{id}", method = RequestMethod.GET)
    public ResponseEntity<IzvrsenaIntervencija> get(@PathVariable("id") int id) {
		Optional<IzvrsenaIntervencija> obj = izvrsenaIntervencijaRepository.findById(id);
        return new ResponseEntity(obj, HttpStatus.OK);
    }
		
	@RequestMapping(value = "izvrsenaIntervencija", method = RequestMethod.POST)
    public ResponseEntity<Void> insertIzvrsenaIntervencija(@RequestBody IzvrsenaIntervencija obj) {
        if (izvrsenaIntervencijaRepository.existsById(obj.getId())) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }		
		izvrsenaIntervencijaRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@RequestMapping(value = "izvrsenaIntervencija/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateIzvrsenaIntervencija(@PathVariable("id") int id, @RequestBody IzvrsenaIntervencija obj) {
        if (!izvrsenaIntervencijaRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        izvrsenaIntervencijaRepository.save(obj);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@RequestMapping(value = "izvrsenaIntervencija/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteIzvrsenaIntervencija(@PathVariable("id") Integer id) {
        if (!izvrsenaIntervencijaRepository.existsById(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }		
		izvrsenaIntervencijaRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }	
}
