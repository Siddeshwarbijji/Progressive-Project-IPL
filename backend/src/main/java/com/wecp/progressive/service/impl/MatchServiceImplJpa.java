package com.wecp.progressive.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecp.progressive.entity.Match;
import com.wecp.progressive.exception.NoMatchesFoundException;
import com.wecp.progressive.repository.MatchRepository;
import com.wecp.progressive.service.MatchService;

@Service
public class MatchServiceImplJpa implements MatchService {
    
    private MatchRepository matchRepository;

    
    @Autowired
    public MatchServiceImplJpa(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<Match> getAllMatches() throws SQLException{
        return matchRepository.findAll();
    }

    public Match getMatchById(int matchId) throws SQLException{
        return matchRepository.findByMatchId(matchId);
    }

    public Integer addMatch(Match match) throws SQLException{
        Match matchObj = matchRepository.save(match);
        return matchObj.getMatchId();
    }

    public void updateMatch(Match match) throws SQLException{
        Match matchObj = matchRepository.findById(match.getMatchId()).get();
        matchObj.setMatchId(match.getMatchId());
        matchObj.setMatchDate(match.getMatchDate());
        matchObj.getFirstTeam().setTeamId(match.getFirstTeam().getTeamId());
        matchObj.getSecondTeam().setTeamId(match.getSecondTeam().getTeamId());
        matchObj.setVenue(match.getVenue());
        matchObj.setResult(match.getResult());
        matchObj.setStatus(match.getStatus());
        matchObj.getWinnerTeam().setTeamId(match.getWinnerTeam().getTeamId());

        matchObj = matchRepository.save(matchObj);
    }

    public void deleteMatch(int matchId) throws SQLException{
        matchRepository.deleteById(matchId);
    }

    public List<Match> getAllMatchesByStatus(String status) throws SQLException{

        //return matchRepository.findAllByStatus(status);
        List<Match> listOfMatchesByStatus = matchRepository.findAllByStatus(status);
        if(listOfMatchesByStatus.isEmpty()){
            throw new NoMatchesFoundException("No matches found with given status.");
        }
        return listOfMatchesByStatus;
    }


    
}