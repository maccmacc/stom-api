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
public class StatusiReportController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ApplicationContext appContext;
	
	@RequestMapping(value = "/reportStatusi/{idPacijenta}", method = RequestMethod.GET)
	@ResponseBody
	public void getReport(@PathVariable("idPacijenta") Integer idPacijenta, HttpServletResponse response) throws Exception {		

		//InputStream jasperStream = this.getClass().getResourceAsStream("/reports/StatusReport.jasper");
		Resource resource = appContext.getResource("classpath:/reports/StatusReport.jasper");
		InputStream jasperStream = resource.getInputStream();
		Map<String,Object> params = new HashMap<>();


		Statement st1 = jdbcTemplate.getDataSource().getConnection().createStatement();
		
		//INICIJALNO STANJE STANJE ZUBA
				//SLIKA ZA ZUB 11ini
				ResultSet rs11ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=11 and p.id="+idPacijenta );
				rs11ini.next();
				String slika11ini = rs11ini.getString(1);	    
				// putanja do slike koja prikazuje status zuba 11
				params.put("P_SLIKA11ini", slika11ini);
				//SLIKA ZA ZUB 12ini
				ResultSet rs12ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=12 and p.id="+idPacijenta );
				rs12ini.next();
				String slika12ini = rs12ini.getString(1);	    
				params.put("P_SLIKA12ini", slika12ini);
				//SLIKA ZA ZUB 13ini
				ResultSet rs13ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=13 and p.id="+idPacijenta );
				rs13ini.next();
				String slika13ini = rs13ini.getString(1);	    
				params.put("P_SLIKA13ini", slika13ini);
				//SLIKA ZA ZUB 14ini
				ResultSet rs14ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=14 and p.id="+idPacijenta );
				rs14ini.next();
				String slika14ini = rs14ini.getString(1);	    
				params.put("P_SLIKA14ini", slika14ini);
				//SLIKA ZA ZUB 15ini
				ResultSet rs15ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=15 and p.id="+idPacijenta );
				rs15ini.next();
				String slika15ini = rs15ini.getString(1);	    
				params.put("P_SLIKA15ini", slika15ini);
				//SLIKA ZA ZUB 16ini
				ResultSet rs16ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=16 and p.id="+idPacijenta );
				rs16ini.next();
				String slika16ini = rs16ini.getString(1);	    
				params.put("P_SLIKA16ini", slika16ini);
				//SLIKA ZA ZUB 17ini
				ResultSet rs17ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=17 and p.id="+idPacijenta );
				rs17ini.next();
				String slika17ini = rs17ini.getString(1);	    
				params.put("P_SLIKA17ini", slika17ini);
				//SLIKA ZA ZUB 18ini
				ResultSet rs18ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=18 and p.id="+idPacijenta );
				rs18ini.next();
				String slika18ini = rs18ini.getString(1);	    
				params.put("P_SLIKA18ini", slika18ini);		
				//SLIKA ZA ZUB 21ini
				ResultSet rs21ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=21 and p.id="+idPacijenta );
				rs21ini.next();
				String slika21ini = rs21ini.getString(1);	    
				params.put("P_SLIKA21ini", slika21ini);
				//SLIKA ZA ZUB 22ini
				ResultSet rs22ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=22 and p.id="+idPacijenta );
				rs22ini.next();
				String slika22ini = rs22ini.getString(1);	    
				params.put("P_SLIKA22ini", slika22ini);
				//SLIKA ZA ZUB 23ini
				ResultSet rs23ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=23 and p.id="+idPacijenta );
				rs23ini.next();
				String slika23ini = rs23ini.getString(1);	    
				params.put("P_SLIKA23ini", slika23ini);
				//SLIKA ZA ZUB 24ini
				ResultSet rs24ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=24 and p.id="+idPacijenta );
				rs24ini.next();
				String slika24ini = rs24ini.getString(1);	    
				params.put("P_SLIKA24ini", slika24ini);
				//SLIKA ZA ZUB 25ini
				ResultSet rs25ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=25 and p.id="+idPacijenta );
				rs25ini.next();
				String slika25ini = rs25ini.getString(1);	    
				params.put("P_SLIKA25ini", slika25ini);
				//SLIKA ZA ZUB 26ini
				ResultSet rs26ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=26 and p.id="+idPacijenta );
				rs26ini.next();
				String slika26ini = rs26ini.getString(1);	    
				params.put("P_SLIKA26ini", slika26ini);
				//SLIKA ZA ZUB 27ini
				ResultSet rs27ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=27 and p.id="+idPacijenta );
				rs27ini.next();
				String slika27ini = rs27ini.getString(1);	    
				params.put("P_SLIKA27ini", slika27ini);
				//SLIKA ZA ZUB 28ini
				ResultSet rs28ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=28 and p.id="+idPacijenta );
				rs28ini.next();
				String slika28ini = rs28ini.getString(1);	    
				params.put("P_SLIKA28ini", slika28ini);
				//SLIKA ZA ZUB 31ini
				ResultSet rs31ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=31 and p.id="+idPacijenta );
				rs31ini.next();
				String slika31ini = rs31ini.getString(1);	    
				params.put("P_SLIKA31ini", slika31ini);
				//SLIKA ZA ZUB 32ini
				ResultSet rs32ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=32 and p.id="+idPacijenta );
				rs32ini.next();
				String slika32ini = rs32ini.getString(1);	    
				params.put("P_SLIKA32ini", slika32ini);
				//SLIKA ZA ZUB 33ini
				ResultSet rs33ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=33 and p.id="+idPacijenta );
				rs33ini.next();
				String slika33ini = rs33ini.getString(1);	    
				params.put("P_SLIKA33ini", slika33ini);
				//SLIKA ZA ZUB 34ini
				ResultSet rs34ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=34 and p.id="+idPacijenta );
				rs34ini.next();
				String slika34ini = rs34ini.getString(1);	    
				params.put("P_SLIKA34ini", slika34ini);
				//SLIKA ZA ZUB 35ini
				ResultSet rs35ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=35 and p.id="+idPacijenta );
				rs35ini.next();
				String slika35ini = rs35ini.getString(1);	    
				params.put("P_SLIKA35ini", slika35ini);
				//SLIKA ZA ZUB 36ini
				ResultSet rs36ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=36 and p.id="+idPacijenta );
				rs36ini.next();
				String slika36ini = rs36ini.getString(1);	    
				params.put("P_SLIKA36ini", slika36ini);
				//SLIKA ZA ZUB 37ini
				ResultSet rs37ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=37 and p.id="+idPacijenta );
				rs37ini.next();
				String slika37ini = rs37ini.getString(1);	    
				params.put("P_SLIKA37ini", slika37ini);
				//SLIKA ZA ZUB 38ini
				ResultSet rs38ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=38 and p.id="+idPacijenta );
				rs38ini.next();
				String slika38ini = rs38ini.getString(1);	    
				params.put("P_SLIKA38ini", slika38ini);
				//SLIKA ZA ZUB 41ini
				ResultSet rs41ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=41 and p.id="+idPacijenta );
				rs41ini.next();
				String slika41ini = rs41ini.getString(1);	    
				params.put("P_SLIKA41ini", slika41ini);
				//SLIKA ZA ZUB 42ini
				ResultSet rs42ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=42 and p.id="+idPacijenta );
				rs42ini.next();
				String slika42ini = rs42ini.getString(1);	    
				params.put("P_SLIKA42ini", slika42ini);
				//SLIKA ZA ZUB 43ini
				ResultSet rs43ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=43 and p.id="+idPacijenta );
				rs43ini.next();
				String slika43ini = rs43ini.getString(1);	    
				params.put("P_SLIKA43ini", slika43ini);
				//SLIKA ZA ZUB 44ini
				ResultSet rs44ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=44 and p.id="+idPacijenta );
				rs44ini.next();
				String slika44ini = rs44ini.getString(1);	    
				params.put("P_SLIKA44ini", slika44ini);
				//SLIKA ZA ZUB 45ini
				ResultSet rs45ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=45 and p.id="+idPacijenta );
				rs45ini.next();
				String slika45ini = rs45ini.getString(1);	    
				params.put("P_SLIKA45ini", slika45ini);
				//SLIKA ZA ZUB 46ini
				ResultSet rs46ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=46 and p.id="+idPacijenta );
				rs46ini.next();
				String slika46ini = rs46ini.getString(1);	    
				params.put("P_SLIKA46ini", slika46ini);
				//SLIKA ZA ZUB 47ini
				ResultSet rs47ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=47 and p.id="+idPacijenta );
				rs47ini.next();
				String slika47ini = rs47ini.getString(1);	    
				params.put("P_SLIKA47ini", slika47ini);
				//SLIKA ZA ZUB 48ini
				ResultSet rs48ini = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_ini and p.id=z.pacijent and zub=48 and p.id="+idPacijenta );
				rs48ini.next();
				String slika48ini = rs48ini.getString(1);	    
				params.put("P_SLIKA48ini", slika48ini);

		
		
		//AKTUELNO STANJE ZUBA
		//SLIKA ZA ZUB 11
		ResultSet rs11 = st1.executeQuery("select 'public/'||naziv from  slika  sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=11 and p.id="+idPacijenta );
		rs11.next();
		String slika11 = rs11.getString(1);	    
		// putanja do slike koja prikazuje status zuba 11
		params.put("P_SLIKA11", slika11);
		//SLIKA ZA ZUB 12
		ResultSet rs12 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=12 and p.id="+idPacijenta );
		rs12.next();
		String slika12 = rs12.getString(1);	    
		params.put("P_SLIKA12", slika12);
		//SLIKA ZA ZUB 13
		ResultSet rs13 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=13 and p.id="+idPacijenta );
		rs13.next();
		String slika13 = rs13.getString(1);	    
		params.put("P_SLIKA13", slika13);
		//SLIKA ZA ZUB 14
		ResultSet rs14 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=14 and p.id="+idPacijenta );
		rs14.next();
		String slika14 = rs14.getString(1);	    
		params.put("P_SLIKA14", slika14);
		//SLIKA ZA ZUB 15
		ResultSet rs15 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=15 and p.id="+idPacijenta );
		rs15.next();
		String slika15 = rs15.getString(1);	    
		params.put("P_SLIKA15", slika15);
		//SLIKA ZA ZUB 16
		ResultSet rs16 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=16 and p.id="+idPacijenta );
		rs16.next();
		String slika16 = rs16.getString(1);	    
		params.put("P_SLIKA16", slika16);
		//SLIKA ZA ZUB 17
		ResultSet rs17 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=17 and p.id="+idPacijenta );
		rs17.next();
		String slika17 = rs17.getString(1);	    
		params.put("P_SLIKA17", slika17);
		//SLIKA ZA ZUB 18
		ResultSet rs18 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=18 and p.id="+idPacijenta );
		rs18.next();
		String slika18 = rs18.getString(1);	    
		params.put("P_SLIKA18", slika18);		
		//SLIKA ZA ZUB 21
		ResultSet rs21 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=21 and p.id="+idPacijenta );
		rs21.next();
		String slika21 = rs21.getString(1);	    
		params.put("P_SLIKA21", slika21);
		//SLIKA ZA ZUB 22
		ResultSet rs22 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=22 and p.id="+idPacijenta );
		rs22.next();
		String slika22 = rs22.getString(1);	    
		params.put("P_SLIKA22", slika22);
		//SLIKA ZA ZUB 23
		ResultSet rs23 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=23 and p.id="+idPacijenta );
		rs23.next();
		String slika23 = rs23.getString(1);	    
		params.put("P_SLIKA23", slika23);
		//SLIKA ZA ZUB 24
		ResultSet rs24 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=24 and p.id="+idPacijenta );
		rs24.next();
		String slika24 = rs24.getString(1);	    
		params.put("P_SLIKA24", slika24);
		//SLIKA ZA ZUB 25
		ResultSet rs25 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=25 and p.id="+idPacijenta );
		rs25.next();
		String slika25 = rs25.getString(1);	    
		params.put("P_SLIKA25", slika25);
		//SLIKA ZA ZUB 26
		ResultSet rs26 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=26 and p.id="+idPacijenta );
		rs26.next();
		String slika26 = rs26.getString(1);	    
		params.put("P_SLIKA26", slika26);
		//SLIKA ZA ZUB 27
		ResultSet rs27 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=27 and p.id="+idPacijenta );
		rs27.next();
		String slika27 = rs27.getString(1);	    
		params.put("P_SLIKA27", slika27);
		//SLIKA ZA ZUB 28
		ResultSet rs28 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=28 and p.id="+idPacijenta );
		rs28.next();
		String slika28 = rs28.getString(1);	    
		params.put("P_SLIKA28", slika28);
		//SLIKA ZA ZUB 31
		ResultSet rs31 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=31 and p.id="+idPacijenta );
		rs31.next();
		String slika31 = rs31.getString(1);	    
		params.put("P_SLIKA31", slika31);
		//SLIKA ZA ZUB 32
		ResultSet rs32 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=32 and p.id="+idPacijenta );
		rs32.next();
		String slika32 = rs32.getString(1);	    
		params.put("P_SLIKA32", slika32);
		//SLIKA ZA ZUB 33
		ResultSet rs33 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=33 and p.id="+idPacijenta );
		rs33.next();
		String slika33 = rs33.getString(1);	    
		params.put("P_SLIKA33", slika33);
		//SLIKA ZA ZUB 34
		ResultSet rs34 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=34 and p.id="+idPacijenta );
		rs34.next();
		String slika34 = rs34.getString(1);	    
		params.put("P_SLIKA34", slika34);
		//SLIKA ZA ZUB 35
		ResultSet rs35 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=35 and p.id="+idPacijenta );
		rs35.next();
		String slika35 = rs35.getString(1);	    
		params.put("P_SLIKA35", slika35);
		//SLIKA ZA ZUB 36
		ResultSet rs36 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=36 and p.id="+idPacijenta );
		rs36.next();
		String slika36 = rs36.getString(1);	    
		params.put("P_SLIKA36", slika36);
		//SLIKA ZA ZUB 37
		ResultSet rs37 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=37 and p.id="+idPacijenta );
		rs37.next();
		String slika37 = rs37.getString(1);	    
		params.put("P_SLIKA37", slika37);
		//SLIKA ZA ZUB 38
		ResultSet rs38 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=38 and p.id="+idPacijenta );
		rs38.next();
		String slika38 = rs38.getString(1);	    
		params.put("P_SLIKA38", slika38);
		//SLIKA ZA ZUB 41
		ResultSet rs41 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=41 and p.id="+idPacijenta );
		rs41.next();
		String slika41 = rs41.getString(1);	    
		params.put("P_SLIKA41", slika41);
		//SLIKA ZA ZUB 42
		ResultSet rs42 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=42 and p.id="+idPacijenta );
		rs42.next();
		String slika42 = rs42.getString(1);	    
		params.put("P_SLIKA42", slika42);
		//SLIKA ZA ZUB 43
		ResultSet rs43 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=43 and p.id="+idPacijenta );
		rs43.next();
		String slika43 = rs43.getString(1);	    
		params.put("P_SLIKA43", slika43);
		//SLIKA ZA ZUB 44
		ResultSet rs44 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=44 and p.id="+idPacijenta );
		rs44.next();
		String slika44 = rs44.getString(1);	    
		params.put("P_SLIKA44", slika44);
		//SLIKA ZA ZUB 45
		ResultSet rs45 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=45 and p.id="+idPacijenta );
		rs45.next();
		String slika45 = rs45.getString(1);	    
		params.put("P_SLIKA45", slika45);
		//SLIKA ZA ZUB 46
		ResultSet rs46 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=46 and p.id="+idPacijenta );
		rs46.next();
		String slika46 = rs46.getString(1);	    
		params.put("P_SLIKA46", slika46);
		//SLIKA ZA ZUB 47
		ResultSet rs47 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=47 and p.id="+idPacijenta );
		rs47.next();
		String slika47 = rs47.getString(1);	    
		params.put("P_SLIKA47", slika47);
		//SLIKA ZA ZUB 48
		ResultSet rs48 = st1.executeQuery("select 'public/'||naziv from slika sl, pacijent p, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and zub=48 and p.id="+idPacijenta );
		rs48.next();
		String slika48 = rs48.getString(1);	    
		params.put("P_SLIKA48", slika48);
		
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);	        
		Statement st = jdbcTemplate.getDataSource().getConnection().createStatement();
		ResultSet rs = st.executeQuery("select 'public/'||naziv, ime, prezime, adresa, kontakt, datum_rodjenja  from pacijent p, slika sl, zub z, status s where sl.id=s.slika and s.id=z.status_akt and p.id=z.pacijent and  p.id="+idPacijenta);	    
		generateReportPDF(response, jasperReport, params, new JRResultSetDataSource(rs));	




		// tako do 48
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
