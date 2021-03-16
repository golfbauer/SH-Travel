package de.hhn.se.labswp.wstgsh.webapi.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReisepunktService {

    private final ReisepunktDAO reisepunktDAO;

    @Autowired
    public ReisepunktService(ReisepunktDAO reisepunktDAO) {
        this.reisepunktDAO = reisepunktDAO;
    }

    public List<Reisepunkt> getReisepunkte() {
        return reisepunktDAO.findAll();
    }
}
