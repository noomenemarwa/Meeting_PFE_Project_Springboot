package com.leoni.pfe.accessingdatajpa.Repository;

import com.leoni.pfe.accessingdatajpa.MembreDeReunion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface MembreDeReunionRepository extends JpaRepository<MembreDeReunion,Long> {

    //  find by IdMembre
    List<MembreDeReunion> findByIdMembre(long idMembre);

    List<MembreDeReunion> findByIdReunion(long idReunion);

    // update is_present=true
    @Query("update MembreDeReunion mr set mr.isPresent=1 where mr.idReunion=?1 and mr.idMembre=?2")
    @Modifying
    int memebreIsPresent(long idReunion, long idMembre);

}
