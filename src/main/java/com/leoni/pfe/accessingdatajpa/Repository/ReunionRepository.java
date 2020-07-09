package com.leoni.pfe.accessingdatajpa.Repository;

import com.leoni.pfe.accessingdatajpa.Reunion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReunionRepository extends JpaRepository<Reunion,Long> {

    //
    @Query(value = "select r from Reunion r where r.id in :ids order by r.date_deb desc")
    List<Reunion> findMyReunion(@Param("ids") List<Long> ids);

    @Query(value = "select * from reunion r where r.date_deb >= :startDate and r.date_deb <= :endDate ;", nativeQuery = true)
    List<Reunion> reunionBetweenStartAndEndDate(@Param("startDate") String startDate, @Param("endDate") String endDate);

    //modifer une réunion
    @Query(value = "update Reunion R set R.sujet=?2, R.qrcode=?3, R.date_deb=?4, R.date_fin=?5 where R.id=?1")
    @Modifying
    //int car renvoyer le nombre de modification
    int updateReunion(long id, String sujet, String qrCode, String date_deb,String date_fin);





}

