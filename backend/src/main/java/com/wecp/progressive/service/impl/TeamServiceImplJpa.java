package com.wecp.progressive.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecp.progressive.entity.Team;
import com.wecp.progressive.exception.TeamAlreadyExistsException;
import com.wecp.progressive.exception.TeamDoesNotExistException;
import com.wecp.progressive.repository.CricketerRepository;
import com.wecp.progressive.repository.TeamRepository;
import com.wecp.progressive.service.TeamService;

@Service
public class TeamServiceImplJpa  implements TeamService{
    
    private TeamRepository teamRepository;
    @Autowired
    private CricketerRepository cricketerRepository;

    @Autowired
    public TeamServiceImplJpa(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
        
    }

    public int addTeam(Team team){

        // if(teamRepository.existsById(team.getTeamId())){
        //     throw new TeamAlreadyExistsException("");
        // }    
        // Optional<Team> teamop = teamRepository.findById(team.getTeamId());
        Team teamObj = teamRepository.findByTeamName(team.getTeamName());
        if(teamObj != null && teamObj.getTeamName().equalsIgnoreCase(team.getTeamName())){
            throw new TeamAlreadyExistsException("Team already exists.");
        } 
        teamObj = teamRepository.save(team);
        return teamObj.getTeamId();

    }

    public void deleteTeam(int teamId){
        
        if(!teamRepository.existsById(teamId)){
            throw new TeamDoesNotExistException("Team with given "+teamId +"does not exist");
        }
        teamRepository.deleteById(teamId);

    }

    public List<Team> getAllTeams(){
        return teamRepository.findAll();
    }

    public List<Team> getAllTeamsSortedByName(){
        
        List<Team> teams = teamRepository.findAll();
        teams.sort(Comparator.comparing(Team::getTeamName));
        return teams;
        
        
    }

    public Team getTeamById(int teamId){
        
        if(!teamRepository.existsById(teamId)){
            throw new TeamDoesNotExistException("Team with given " + teamId +"does not exist");
        }
        return teamRepository.findByTeamId(teamId);
    }

    public void updateTeam(Team team){

        if(!teamRepository.existsById(team.getTeamId())){
            throw new TeamDoesNotExistException("Team with given " + team.getTeamId()+"does not exist");
        }
        Team teamObj1 = teamRepository.findByTeamName(team.getTeamName());
        if(teamObj1!= null && teamObj1.getTeamId()!= team.getTeamId() && teamObj1.getTeamName().equalsIgnoreCase(team.getTeamName())){
            throw new TeamAlreadyExistsException("Team already exists.");
        }        
        Team teamObj = teamRepository.findById(team.getTeamId()).get();
        teamObj.setTeamId(team.getTeamId());
        teamObj.setTeamName(team.getTeamName());
        teamObj.setOwnerName(team.getOwnerName());
        teamObj.setLocation(team.getLocation());
        teamObj.setEstablishmentYear(team.getEstablishmentYear());
        teamRepository.save(teamObj);
    }

}