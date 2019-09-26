package com.stom.app.service.imp;

import com.stom.app.dtos.response.PacijentDetaljiResponse;
import com.stom.app.jpa.IzvrsenaIntervencija;
import com.stom.app.jpa.Pacijent;
import com.stom.app.reps.PacijentRepository;
import com.stom.app.service.IPacijentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PacijentService implements IPacijentService {

    private final PacijentRepository _pacijentRespository;

    public PacijentService(PacijentRepository pacijentRepository) {
        _pacijentRespository = pacijentRepository;
    }

    @Override
    @Transactional
    public PacijentDetaljiResponse getPacijentById(Integer id) {
        Pacijent pacijent = _pacijentRespository.findOneById(id);
        if (pacijent == null) {
            return null;
        }

        return buildPacijentDetaljiResponse(pacijent);
    }

    private PacijentDetaljiResponse buildPacijentDetaljiResponse(Pacijent pacijent) {
//        pacijent.getIzvrsenaIntervencijas().stream()
//                .forEach(intercija -> intercija.setPacijent(null));
//
//        pacijent.getPlanRadas().stream().forEach(planRada -> planRada.setPacijent(null));
//
//        pacijent.getRacuns().stream().forEach(racun -> racun.setPacijent(null));
//
//        pacijent.getSnimaks().stream().forEach(snimak -> snimak.setPacijent(null));
//
//        pacijent.getTermins().stream().forEach(termin -> termin.setPacijent(null));
//
//        pacijent.getZubs().stream().forEach(zub -> zub.setPacijent(null));

        return PacijentDetaljiResponse.builder().ime(pacijent.getIme()).prezime(pacijent.getPrezime())
                .adresa(pacijent.getAdresa()).datumRodjenja(pacijent.getDatumRodjenja())
                .kontakt(pacijent.getKontakt()).napomena(pacijent.getNapomena())
                .lookup(pacijent.getLookup()).izvrsenaIntervencijas(pacijent.getIzvrsenaIntervencijas())
                .planRadas(pacijent.getPlanRadas()).racuns(pacijent.getRacuns())
                .snimaks(pacijent.getSnimaks()).termins(pacijent.getTermins())
                .zubs(pacijent.getZubs()).datumUpisa(pacijent.getDatumUpisa()).email(pacijent.getEmail()).build();
    }
}
