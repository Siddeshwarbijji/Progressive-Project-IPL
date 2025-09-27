package com.wecp.progressive.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wecp.progressive.config.DatabaseConnectionManager;
import com.wecp.progressive.entity.Team;

public class TeamDAOImpl implements TeamDAO{

    @Override
    public int addTeam(Team team) {

        String query = "insert into team (team_name, location, owner_name, establishment_year) values (?, ?, ?, ?)";
        try{
            PreparedStatement ps = DatabaseConnectionManager.getConnection().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,team.getTeamName());
            ps.setString(2,team.getLocation());
            ps.setString(3,team.getOwnerName());
            ps.setInt(4,team.getEstablishmentYear());
            int n = ps.executeUpdate();
            if(n > 0){
                ResultSet rs = ps.getGeneratedKeys();
                while(rs.next()){
                    team.setTeamId(rs.getInt(1));
                    return rs.getInt(1);
                }
            }


        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void deleteTeam(int teamId) {
        String query = "delete from team where team_id = ?";
        try{
            PreparedStatement ps = DatabaseConnectionManager.getConnection().prepareStatement(query);
            ps.setInt(1,teamId);
            ps.executeUpdate();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Team> getAllTeams() {

        String query = "select * from team";
        List<Team> teams = new ArrayList<>();
        try{
            PreparedStatement ps = DatabaseConnectionManager.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Team team = new Team();
                team.setTeamId(rs.getInt(1));
                team.setTeamName(rs.getString(2));
                team.setLocation(rs.getString(3));
                team.setOwnerName(rs.getString(4));
                team.setEstablishmentYear(rs.getInt(5));
                teams.add(team);
            }

        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return teams;
    }

    @Override
    public Team getTeamById(int teamId) {

        String query = "select * from team where team_id = ?";
        try{
            PreparedStatement ps = DatabaseConnectionManager.getConnection().prepareStatement(query);
            ps.setInt(1,teamId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Team team = new Team();
                team.setTeamId(rs.getInt(1));
                team.setTeamName(rs.getString(2));
                team.setLocation(rs.getString(3));
                team.setOwnerName(rs.getString(4));
                team.setEstablishmentYear(rs.getInt(5));
                return team;
            }


        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateTeam(Team team) {

        String query = "update team set team_name = ?, location = ?, owner_name = ?, establishment_year = ?";
        try{
            PreparedStatement ps = DatabaseConnectionManager.getConnection().prepareStatement(query);
            ps.setString(1,team.getTeamName());
            ps.setString(2,team.getLocation());
            ps.setString(3,team.getOwnerName());
            ps.setInt(4,team.getEstablishmentYear());
            ps.executeUpdate();

        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    


}
