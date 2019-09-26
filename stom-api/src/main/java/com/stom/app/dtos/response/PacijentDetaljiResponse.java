package com.stom.app.dtos.response;

import com.stom.app.jpa.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PacijentDetaljiResponse {

    private String ime;

    private String prezime;

    private String adresa;

    private Date datumRodjenja;


    private String kontakt;

    private String napomena;


    private String lookup;

    private List<IzvrsenaIntervencija> izvrsenaIntervencijas;

    private List<PlanRada> planRadas;

    private List<Racun> racuns;

    private List<Snimak> snimaks;

    private List<Termin> termins;

    private List<Zub> zubs;

    private String email;

    private Date datumUpisa;

}
