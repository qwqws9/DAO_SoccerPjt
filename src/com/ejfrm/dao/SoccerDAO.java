package com.ejfrm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ejfrm.vo.SoccerVO;

public class SoccerDAO {

	private SoccerDAO() {
	
	}
	
	private static SoccerDAO instance = new SoccerDAO();
	
	public static SoccerDAO getInstance() {
		return instance;
	}
	
	public Connection getConnection() {
		Connection con =null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:/comp/env/jdbc/Oracle");
			con = ds.getConnection();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return con;
		
	}
	
	public void closeConnection(Connection con) {
		if(con !=null) {
			try{
				con.close();
			}catch(Exception e) {
				
			}
		}
	}
	
	public List<SoccerVO> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SoccerVO> list = new ArrayList<>();
		
		String sql = "select * from soccer order by code";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				SoccerVO sVo = new SoccerVO();
				sVo.setCode(rs.getInt("code"));
				sVo.setTeamname(rs.getString("teamname"));
				sVo.setCountry(rs.getString("country"));
				sVo.setHomeground(rs.getString("homeground"));
				sVo.setCoach(rs.getString("coach"));
				sVo.setPlayers(rs.getString("players"));
				sVo.setPicture(rs.getString("picture"));
				list.add(sVo);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con);
		}
		return list;
	}
	
	public void insertTeam(SoccerVO sVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into soccer values(seq_soccer.nextval,?,?,?,?,?,?)";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sVo.getTeamname());
			pstmt.setString(2, sVo.getCountry());
			pstmt.setString(3, sVo.getHomeground());
			pstmt.setString(4, sVo.getCoach());
			pstmt.setString(5, sVo.getPlayers());
			pstmt.setString(6, sVo.getPicture());
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con);
		}
	}
	
	public SoccerVO selectCode(int code) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SoccerVO sVo = new SoccerVO();
		String sql = "select * from soccer where code=?";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				sVo.setTeamname(rs.getString("teamname"));
				sVo.setCountry(rs.getString("country"));
				sVo.setHomeground(rs.getString("homeground"));
				sVo.setCoach(rs.getString("coach"));
				sVo.setPlayers(rs.getString("players"));
				sVo.setPicture(rs.getString("picture"));
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con);
		}
		return sVo;
	}
	
	public void updateTeam(SoccerVO sVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update soccer set teamname=?, country=?, homeground=?,"
					+ " coach=?, players=?,picture=? where code = ?";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sVo.getTeamname());
			pstmt.setString(2, sVo.getCountry());
			pstmt.setString(3, sVo.getHomeground());
			pstmt.setString(4, sVo.getCoach());
			pstmt.setString(5, sVo.getPlayers());
			pstmt.setString(6, sVo.getPicture());
			pstmt.setInt(7, sVo.getCode());
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con);
		}
	}
	
	public void DeleteTeam(int code) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "delete soccer where code = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			closeConnection(con);
		}
	}
	
	
}
