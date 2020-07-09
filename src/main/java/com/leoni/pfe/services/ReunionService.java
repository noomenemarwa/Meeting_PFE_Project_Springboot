package com.leoni.pfe.services;

import com.leoni.pfe.accessingdatajpa.MembreDeReunion;
import com.leoni.pfe.accessingdatajpa.Repository.MembreDeReunionRepository;
import com.leoni.pfe.accessingdatajpa.Repository.ReunionRepository;
import com.leoni.pfe.accessingdatajpa.Reunion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ReunionService {

    private final ReunionRepository reunionRepository;
    private final MembreDeReunionRepository membreDeReunionRepository;
    private final EmailService emailService;

    @Autowired
    public ReunionService(ReunionRepository reunionRepository,
                          MembreDeReunionRepository membreDeReunionRepository,
                          EmailService emailService) {
        this.reunionRepository = reunionRepository;
        this.membreDeReunionRepository = membreDeReunionRepository;
        this.emailService = emailService;
    }

    // id reunion, id dep,

    // afficher la liste des reunions
    public List<Reunion> findAllReunion() {
        return reunionRepository.findAll();
    }

    public List<Reunion> findMyReunion(long ideMembre) {
        List<MembreDeReunion> list = membreDeReunionRepository.findByIdMembre(ideMembre);
        if (list != null && !list.isEmpty()) {
            List<Long> ids = list.stream().map(new Function<MembreDeReunion, Long>() {
                @Override
                public Long apply(MembreDeReunion membreDeReunion) {
                    return membreDeReunion.getIdReunion();
                }
            }).collect(Collectors.toList());
            System.out.println("ids: " + ids);
            return reunionRepository.findMyReunion(ids);
        } else {
            return new ArrayList<>(0);
        }

        // return reunionRepository.findAll();
    }

    //modifier une reunion
    @Transactional
    public boolean updateReunion(long id, String sujet, String date_deb, String date_fin) {
        String qrCode = generateQrCode(date_deb, date_fin);
        int updateCount = reunionRepository.updateReunion(id, sujet, qrCode, date_deb, date_fin);
        return updateCount > 0;
    }

    //supprimer une reunion
    @Transactional
    public void deleteReunion(long id) {
        reunionRepository.deleteById(id);
    }

    //ajouter une reunion
    @Transactional
    public Reunion saveReunion(String sujet, String date_deb, String date_fin) {
        Reunion reunion = new Reunion();
        reunion.setSujet(sujet);
        reunion.setQrcode(generateQrCode(date_deb, date_fin));
        reunion.setDate_deb(date_deb);
        reunion.setDate_fin(date_fin);
        return reunionRepository.save(reunion);
        /*if (reun.getId() > 0) {
            Feedback feedback = new Feedback("test nom", "")


            emailService.sendEmail()
        }*/
    }

    /**
     *
     * Retourne la liste des IDS des reunions entre 2 dates donnees.
     * @param startDate
     * @param endDate
     * @return
     */
    public List<Long> findReunionBetweenStartAndEndDate(String startDate, String endDate) {
        List<Long> listIds = new ArrayList<>(0);
        List<Reunion> list = reunionRepository.reunionBetweenStartAndEndDate(startDate, endDate);
        if (list != null && !list.isEmpty()) {
            for (Reunion r : list) {
                // System.out.println("reunion: " + r.toString());
                listIds.add(r.getId());
            }
        }
        return listIds;
    }


    //generer un Qrcode
    private String generateQrCode(String startDate, String endDate) {
        return startDate.concat(endDate)
                .concat(UUID.randomUUID().toString())
                .replace(" ", "");
    }


}
