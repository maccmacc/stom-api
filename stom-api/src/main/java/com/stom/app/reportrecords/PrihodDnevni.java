package com.stom.app.reportrecords;

import java.math.BigDecimal;
import java.sql.Date;

public class PrihodDnevni {

	private Date datum;
	private BigDecimal prihod;
	
	public PrihodDnevni() {
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public BigDecimal getPrihod() {
		return prihod;
	}

	public void setPrihod(BigDecimal prihod) {
		this.prihod = prihod;
	}
}
