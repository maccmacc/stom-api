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
public class PlanRadaReportController {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ApplicationContext appContext;
	
	@RequestMapping(value = "/reportPlanRada/{idPlanaRada}", method = RequestMethod.GET)
	@ResponseBody
	public void getReport(@PathVariable("idPlanaRada") Integer idPlanaRada, HttpServletResponse response) throws Exception {	
		Resource resource = appContext.getResource("classpath:/reports/PlanRada.jasper");
		InputStream jasperStream = resource.getInputStream();
	    Map<String,Object> params = new HashMap<>();
	    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);	    
	    //response.setContentType("application/pdf");	    
	    Statement st = jdbcTemplate.getDataSource().getConnection().createStatement();
	    ResultSet rs = st.executeQuery("select p.id, p.datum, pac.ime || ' ' || pac.prezime as pacijent, 'Dr ' || zap.ime || ' ' || zap.prezime as zaposleni, "+
										"	stavka_plana_rada.redni_broj, dijagnoza.opis as dijagnoza, vi.opis as vrsta_intervencije, stavka_plana_rada.zub, "+
										"	stavka_plana_rada.cena, stavka_plana_rada.popust, stavka_plana_rada.iznos "+
										"from plan_rada p, pacijent pac, zaposleni zap, vrsta_intervencije vi, stavka_plana_rada "+  
										"left join dijagnoza on stavka_plana_rada.dijagnoza=dijagnoza.id "+
										"where p.id=stavka_plana_rada.plan_rada and p.id="+idPlanaRada+
										" and stavka_plana_rada.vrsta_intervencije=vi.id and p.zaposelni=zap.id and p.pacijent=pac.id "+	    								 
										"order by stavka_plana_rada.redni_broj");
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
