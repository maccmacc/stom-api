package com.stom.app.jpa;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.*;


/**
 * The persistent class for the zub database table.
 * 
 */
@Entity
@NamedQuery(name="Zub.findAll", query="SELECT z FROM Zub z")
public class Zub implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="status_pov_desno_akt")
	private Integer statusPovDesnoAkt;

	@Column(name="status_pov_desno_ini")
	private Integer statusPovDesnoIni;

	@Column(name="status_pov_dole_akt")
	private Integer statusPovDoleAkt;

	@Column(name="status_pov_dole_ini")
	private Integer statusPovDoleIni;

	@Column(name="status_pov_gore_akt")
	private Integer statusPovGoreAkt;

	@Column(name="status_pov_gore_ini")
	private Integer statusPovGoreIni;

	@Column(name="status_pov_lijevo_akt")
	private Integer statusPovLijevoAkt;

	@Column(name="status_pov_lijevo_ini")
	private Integer statusPovLijevoIni;

	@Column(name="status_pov_sredina_akt")
	private Integer statusPovSredinaAkt;

	@Column(name="status_pov_sredina_ini")
	private Integer statusPovSredinaIni;

	private Integer zub;

	//bi-directional many-to-one association to Pacijent
	@ManyToOne
	@JoinColumn(name="pacijent")
	private Pacijent pacijent;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="status_akt")
	private Status status1;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="status_ini")
	private Status status2;

	public Zub() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatusPovDesnoAkt() {
		return this.statusPovDesnoAkt;
	}

	public void setStatusPovDesnoAkt(Integer statusPovDesnoAkt) {
		this.statusPovDesnoAkt = statusPovDesnoAkt;
	}

	public Integer getStatusPovDesnoIni() {
		return this.statusPovDesnoIni;
	}

	public void setStatusPovDesnoIni(Integer statusPovDesnoIni) {
		this.statusPovDesnoIni = statusPovDesnoIni;
	}

	public Integer getStatusPovDoleAkt() {
		return this.statusPovDoleAkt;
	}

	public void setStatusPovDoleAkt(Integer statusPovDoleAkt) {
		this.statusPovDoleAkt = statusPovDoleAkt;
	}

	public Integer getStatusPovDoleIni() {
		return this.statusPovDoleIni;
	}

	public void setStatusPovDoleIni(Integer statusPovDoleIni) {
		this.statusPovDoleIni = statusPovDoleIni;
	}

	public Integer getStatusPovGoreAkt() {
		return this.statusPovGoreAkt;
	}

	public void setStatusPovGoreAkt(Integer statusPovGoreAkt) {
		this.statusPovGoreAkt = statusPovGoreAkt;
	}

	public Integer getStatusPovGoreIni() {
		return this.statusPovGoreIni;
	}

	public void setStatusPovGoreIni(Integer statusPovGoreIni) {
		this.statusPovGoreIni = statusPovGoreIni;
	}

	public Integer getStatusPovLijevoAkt() {
		return this.statusPovLijevoAkt;
	}

	public void setStatusPovLijevoAkt(Integer statusPovLijevoAkt) {
		this.statusPovLijevoAkt = statusPovLijevoAkt;
	}

	public Integer getStatusPovLijevoIni() {
		return this.statusPovLijevoIni;
	}

	public void setStatusPovLijevoIni(Integer statusPovLijevoIni) {
		this.statusPovLijevoIni = statusPovLijevoIni;
	}

	public Integer getStatusPovSredinaAkt() {
		return this.statusPovSredinaAkt;
	}

	public void setStatusPovSredinaAkt(Integer statusPovSredinaAkt) {
		this.statusPovSredinaAkt = statusPovSredinaAkt;
	}

	public Integer getStatusPovSredinaIni() {
		return this.statusPovSredinaIni;
	}

	public void setStatusPovSredinaIni(Integer statusPovSredinaIni) {
		this.statusPovSredinaIni = statusPovSredinaIni;
	}

	public Integer getZub() {
		return this.zub;
	}

	public void setZub(Integer zub) {
		this.zub = zub;
	}

	public Pacijent getPacijent() {
		return this.pacijent;
	}

	public void setPacijent(Pacijent pacijent2) {
		this.pacijent = pacijent2;
	}

	public Status getStatus1() {
		return this.status1;
	}

	public void setStatus1(Status status1) {
		this.status1 = status1;
	}

	public Status getStatus2() {
		return this.status2;
	}

	public void setStatus2(Status status2) {
		this.status2 = status2;
	}

}