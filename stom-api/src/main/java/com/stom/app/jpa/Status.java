package com.stom.app.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the status database table.
 * 
 */
@Entity
@NamedQuery(name="Status.findAll", query="SELECT s FROM Status s")
public class Status implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String opis;

	@Column(name="za_povrsine")
	private Boolean zaPovrsine;

	@ManyToOne
	@JoinColumn(name="slika")
	private Slika slika;
	
	//bi-directional many-to-one association to VrstaIntervencije
	@OneToMany(mappedBy="status")
	@JsonIgnore
	private List<VrstaIntervencije> vrstaIntervencijes;

	//bi-directional many-to-one association to Zub
	@OneToMany(mappedBy="status1")
	@JsonIgnore
	private List<Zub> zubs1;

	//bi-directional many-to-one association to Zub
	@JsonIgnore
	@OneToMany(mappedBy="status2")
	private List<Zub> zubs2;

	public Status() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Boolean getZaPovrsine() {
		return this.zaPovrsine;
	}

	public void setZaPovrsine(Boolean zaPovrsine) {
		this.zaPovrsine = zaPovrsine;
	}

	public List<VrstaIntervencije> getVrstaIntervencijes() {
		return this.vrstaIntervencijes;
	}

	public void setVrstaIntervencijes(List<VrstaIntervencije> vrstaIntervencijes) {
		this.vrstaIntervencijes = vrstaIntervencijes;
	}

	public VrstaIntervencije addVrstaIntervencije(VrstaIntervencije vrstaIntervencije) {
		getVrstaIntervencijes().add(vrstaIntervencije);
		vrstaIntervencije.setStatus(this);

		return vrstaIntervencije;
	}

	public VrstaIntervencije removeVrstaIntervencije(VrstaIntervencije vrstaIntervencije) {
		getVrstaIntervencijes().remove(vrstaIntervencije);
		vrstaIntervencije.setStatus(null);

		return vrstaIntervencije;
	}

	public List<Zub> getZubs1() {
		return this.zubs1;
	}

	public void setZubs1(List<Zub> zubs1) {
		this.zubs1 = zubs1;
	}

	public Zub addZubs1(Zub zubs1) {
		getZubs1().add(zubs1);
		zubs1.setStatus1(this);

		return zubs1;
	}

	public Zub removeZubs1(Zub zubs1) {
		getZubs1().remove(zubs1);
		zubs1.setStatus1(null);

		return zubs1;
	}

	public List<Zub> getZubs2() {
		return this.zubs2;
	}

	public void setZubs2(List<Zub> zubs2) {
		this.zubs2 = zubs2;
	}

	public Zub addZubs2(Zub zubs2) {
		getZubs2().add(zubs2);
		zubs2.setStatus2(this);

		return zubs2;
	}

	public Zub removeZubs2(Zub zubs2) {
		getZubs2().remove(zubs2);
		zubs2.setStatus2(null);

		return zubs2;
	}
	
	public Slika getSlika() {
		return slika;
	}

	public void setSlika(Slika slika) {
		this.slika = slika;
	}
}