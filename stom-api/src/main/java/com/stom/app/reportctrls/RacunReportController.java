package com.stom.app.reportctrls;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
public class RacunReportController {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ApplicationContext appContext;
	
	@RequestMapping(value = "/reportRacun/{idRacuna}", method = RequestMethod.GET)
	@ResponseBody
	public void getReport(@PathVariable("idRacuna") Integer idRacuna, HttpServletResponse response) throws Exception {	
		Resource resource = appContext.getResource("classpath:/reports/Racun.jasper");
		InputStream jasperStream = resource.getInputStream();
	    Map<String,Object> params = new HashMap<>();
	    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);	    
	    //response.setContentType("application/pdf");	    
	    Statement st = jdbcTemplate.getDataSource().getConnection().createStatement();
	    ResultSet rs = st.executeQuery("select r.id, r.datum, pac.ime || ' ' || pac.prezime as pacijent, 'Dr ' || zap.ime || ' ' || zap.prezime as zaposleni, "+
										"	ii.id redni_broj, dijagnoza.opis as dijagnoza, vi.opis as vrsta_intervencije, ii.zub, "+
										"	ii.iznos cena, ii.popust, ii.placeno iznos "+
										"from racun r, pacijent pac, zaposleni zap, vrsta_intervencije vi, izvrsena_intervencija ii "+  
										"left join dijagnoza on ii.dijagnoza=dijagnoza.id "+
										"where r.id=ii.racun and r.id="+idRacuna+
										" and ii.vrsta_intervencije=vi.id and ii.zaposleni=zap.id and ii.pacijent=pac.id "+	    								 
										"order by ii.id");
		generateReportPDF(response, jasperReport, params, new JRResultSetDataSource(rs));	
	}
	
	private void generateReportPDF (HttpServletResponse resp, JasperReport jasperReport, Map<String,Object> params, JRDataSource dataSource)throws Exception {
        byte[] bytes = null;
        bytes = JasperRunManager.runReportToPdf(jasperReport, params, dataSource);
        resp.reset();
        resp.resetBuffer();
        resp.setContentType("application/pdf");
        resp.setContentLength(bytes.length);
        ServletOutputStream ouputStream = resp.getOutputStream();
        ouputStream.write(bytes, 0, bytes.length);
        ouputStream.flush();
        ouputStream.close();
    } 

}
