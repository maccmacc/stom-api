package com.stom.app.service;

import com.stom.app.dtos.response.PacijentDetaljiResponse;

public interface IPacijentService {

    PacijentDetaljiResponse getPacijentById(Integer id);
}
