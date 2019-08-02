package com.stom.app.reportrecords;

import java.math.BigDecimal;

public class PacijentPlacenoPopust {
	private String ime;
	private String prezime;
	private BigDecimal placeno;
	private BigDecimal popust;
	private BigDecimal udio;
	
	public PacijentPlacenoPopust() {
	
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public BigDecimal getPlaceno() {
		return placeno;
	}

	public void setPlaceno(BigDecimal placeno) {
		this.placeno = placeno;
	}

	public BigDecimal getPopust() {
		return popust;
	}

	public void setPopust(BigDecimal popust) {
		this.popust = popust;
	}

	public BigDecimal getUdio() {
		return udio;
	}

	public void setUdio(BigDecimal udio) {
		this.udio = udio;
	}
}
