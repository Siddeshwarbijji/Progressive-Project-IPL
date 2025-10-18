package com.wecp.progressive.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wecp.progressive.entity.Cricketer;
@Repository
public interface CricketerRepository extends JpaRepository<Cricketer,Integer> {
    public Cricketer findByCricketerId(int cricketerId);

    public List<Cricketer> findByTeam_TeamId(int teamId);

    public long countByTeam_TeamId(int teamId);

    @Modifying
    @Transactional
    @Query("Delete from Cricketer c where c.team.teamId = :teamId")
    public Void deleteByTeamId(int teamId);


}
