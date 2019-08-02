package com.stom.app.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the snimak database table.
 * 
 */
@Entity
@NamedQuery(name="Snimak.findAll", query="SELECT s FROM Snimak s")
public class Snimak implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date datum;

	private String opis;
	
	private String putanja;

	//bi-directional many-to-one association to Pacijent
	@ManyToOne
	@JoinColumn(name="pacijent")
	private Pacijent pacijent;

	public Snimak() {
	}

	public Snimak(String originalFilename) {
		this.opis = originalFilename;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Pacijent getPacijent() {
		return this.pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}
	

	public String getPutanja() {
		return putanja;
	}

	public void setPutanja(String putanja) {
		this.putanja = putanja;
	}
}
