package com.stom.app.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the stavka_plana_rada database table.
 * 
 */
@Entity
@Table(name="stavka_plana_rada")
@NamedQuery(name="StavkaPlanaRada.findAll", query="SELECT s FROM StavkaPlanaRada s")
public class StavkaPlanaRada implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private BigDecimal iznos;	

	private BigDecimal popust;

	private BigDecimal cena;

	@Column(name="redni_broj")
	private Integer redniBroj;

	//bi-directional many-to-one association to Dijagnoza
	@ManyToOne
	@JoinColumn(name="dijagnoza")
	private Dijagnoza dijagnoza;

	//bi-directional many-to-one association to PlanRada
	@ManyToOne
	@JoinColumn(name="plan_rada")
	private PlanRada planRada;

	//bi-directional many-to-one association to VrstaIntervencije
	@ManyToOne
	@JoinColumn(name="vrsta_intervencije")
	private VrstaIntervencije vrstaIntervencije;

	private Integer zub;

	public StavkaPlanaRada() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getIznos() {
		return this.iznos;
	}

	public void setIznos(BigDecimal iznos) {
		this.iznos = iznos;
	}

	public BigDecimal getPopust() {
		return this.popust;
	}

	public void setPopust(BigDecimal popust) {
		this.popust = popust;
	}

	public Integer getRedniBroj() {
		return this.redniBroj;
	}

	public void setRedniBroj(Integer redniBroj) {
		this.redniBroj = redniBroj;
	}

	public Dijagnoza getDijagnoza() {
		return this.dijagnoza;
	}

	public void setDijagnoza(Dijagnoza dijagnoza) {
		this.dijagnoza = dijagnoza;
	}

	public PlanRada getPlanRada() {
		return this.planRada;
	}

	public void setPlanRada(PlanRada planRada) {
		this.planRada = planRada;
	}

	public VrstaIntervencije getVrstaIntervencije() {
		return this.vrstaIntervencije;
	}

	public void setVrstaIntervencije(VrstaIntervencije vrstaIntervencije) {
		this.vrstaIntervencije = vrstaIntervencije;
	}

	public Integer getZub() {
		return this.zub;
	}

	public void setZub(Integer zub) {
		this.zub = zub;
	}

	public BigDecimal getCena() {
		return this.cena;
	}

	public void setCena(BigDecimal cena) {
		this.cena = cena;
	}

}