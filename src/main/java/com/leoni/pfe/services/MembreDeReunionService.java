package com.leoni.pfe.services;

import com.leoni.pfe.accessingdatajpa.MembreDeReunion;
import com.leoni.pfe.accessingdatajpa.Repository.MembreDeReunionRepository;
import com.leoni.pfe.accessingdatajpa.dto.StatsParReunion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Service
public class MembreDeReunionService {

    private final MembreDeReunionRepository membreDeReunionRepository;
    private final ReunionService reunionService;

    @Autowired //injection de dependance
    public MembreDeReunionService(MembreDeReunionRepository membreDeReunionRepository, ReunionService reunionService) {
        this.membreDeReunionRepository = membreDeReunionRepository;
        this.reunionService = reunionService;
    }

    //afficher la liste des membres
    public List<MembreDeReunion> findAllMembreDeReunion() {
        return membreDeReunionRepository.findAll();
    }

    //find by idMembre
    public boolean findByIdMembre(int idMembre) {
        List<MembreDeReunion> list = membreDeReunionRepository.findByIdMembre(idMembre);
        return !list.isEmpty();
    }

    // update is_present=true

    /**
     *
     * @param idReunion
     * @param idMembre
     * @return
     */
    @Transactional
    public int membreIsPresent(long idReunion, long idMembre) {
        return membreDeReunionRepository.memebreIsPresent(idReunion, idMembre);
    }

    //ajouter une membre
    public MembreDeReunion saveMembre(int idMembre, int idReunion) {
        MembreDeReunion membreDeReunion = new MembreDeReunion();
        membreDeReunion.setIdMembre(idMembre);
        membreDeReunion.setIdReunion(idReunion);
        return membreDeReunionRepository.save(membreDeReunion);
    }

    public StatsParReunion statsParReunion(long idReunion) {
        StatsParReunion statsParReunion = new StatsParReunion();
        statsParReunion.setIdReunion(idReunion);

        List<MembreDeReunion> list = membreDeReunionRepository.findByIdReunion(idReunion);
        if (list != null && !list.isEmpty()) {
            statsParReunion.setNbInvite(list.size());
            int present = 0, abscent = 0;
            for (MembreDeReunion mbr : list) {
                if (mbr.getIsPresent() == 1) {
                    present++;
                } else {
                    abscent++;
                }
            }
            statsParReunion.setNbPresent(present);
            statsParReunion.setNbAbscent(abscent);
        }

        return statsParReunion;
    }

    public List<MembreDeReunion> findAllForReunionList(List<Long> ids) {
        return membreDeReunionRepository.findByIdReunionIn(ids);
    }
}