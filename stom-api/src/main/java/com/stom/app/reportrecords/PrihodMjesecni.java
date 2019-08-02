package com.stom.app.reportrecords;

import java.math.BigDecimal;

public class PrihodMjesecni {

	private int mjesec;
	private int godina;
	private BigDecimal prihod;
	
	public PrihodMjesecni() {
	}

	public int getMjesec() {
		return mjesec;
	}

	public void setMjesec(int mjesec) {
		this.mjesec = mjesec;
	}

	public int getGodina() {
		return godina;
	}

	public void setGodina(int godina) {
		this.godina = godina;
	}

	public BigDecimal getPrihod() {
		return prihod;
	}

	public void setPrihod(BigDecimal prihod) {
		this.prihod = prihod;
	}
}
