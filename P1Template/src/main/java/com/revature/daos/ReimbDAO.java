package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.revature.controllers.AuthController;
import com.revature.models.ReimbDTO;
import com.revature.models.Reimbursement;
import com.revature.utils.ConnectionUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReimbDAO {
	
	public static Logger log = LogManager.getLogger();

	public ReimbDAO() {
		super();
	}
	
	AuthController ac = new AuthController();
	
	public Reimbursement getReimbByID(int id) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from reimbursements where reimb_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Reimbursement reimb = new Reimbursement(
						rs.getInt("reimb_id"),
						rs.getDouble("reimb_amt"),
						rs.getTimestamp("reimb_submitted"),
						rs.getInt("reimb_author"),
						rs.getInt("reimb_status_id"),
						rs.getInt("reimb_type_id"),
						rs.getString("reimb_description")
						);
				
				return reimb;
			}
			
		} catch(SQLException e) {
			System.out.println("Get Reimb by id failed");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean insertReimb(ReimbDTO r) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "insert into reimbursements (reimb_amt, reimb_submitted, reimb_author, reimb_status_id, reimb_type_id, reimb_description)"
					+ " values (?, ?, ?, ?, ?, ?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println(r.getReimb_amt());
			ps.setDouble(1, Double.parseDouble(r.getReimb_amt()));
			ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			ps.setInt(3, ac.currentUser.getUser_id());
			ps.setInt(4, 1);
			ps.setInt(5, 4);
			ps.setString(6, r.getReimb_description());
			
			System.out.println(ps);
			
			ps.executeUpdate();
			
			System.out.println("Reimbursement was added!");
			
			return true;
			
		} catch(SQLException e) {
			System.out.println("insert reimb failed");
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean editStatus(int reimb, int status) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "update reimbursements set reimb_status_id = ? where reimb_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setInt(2, reimb);
			ps.executeUpdate();
			
			System.out.println("Reimbursement " + reimb + " has been given status " + status);
			
			return true;
			
		} catch (SQLException e) {
			System.out.println("Reimb Status edit failed");
			e.printStackTrace();
		}
		
		return false;
	}
	
	public ArrayList<Reimbursement> getAllReimbursements(){
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from reimbursements;";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			ArrayList<Reimbursement> reimbs = new ArrayList<>();
			
			while(rs.next()) {
				Reimbursement r = new Reimbursement(
						rs.getInt("reimb_id"),
						rs.getDouble("reimb_amt"),
						rs.getTimestamp("reimb_submitted"),
						rs.getInt("reimb_author"),
						rs.getInt("reimb_status_id"),
						rs.getInt("reimb_type_id"),
						rs.getString("reimb_description")
						);
				reimbs.add(r);
			}
			
			return reimbs;
			
		} catch (SQLException e) {
			System.out.println("Get all reimbursments failed");
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public ArrayList<Reimbursement> getUserReimbursements(int author){
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from reimbursements where reimb_author = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, author);
			ResultSet rs = ps.executeQuery();
			
			ArrayList<Reimbursement> reimbs = new ArrayList<>();
			
			while(rs.next()) {
				Reimbursement r = new Reimbursement(
						rs.getInt("reimb_id"),
						rs.getDouble("reimb_amt"),
						rs.getTimestamp("reimb_submitted"),
						rs.getInt("reimb_author"),
						rs.getInt("reimb_status_id"),
						rs.getInt("reimb_type_id"),
						rs.getString("reimb_description")
						);
				reimbs.add(r);
			}
			
			return reimbs;
			
		} catch (SQLException e) {
			System.out.println("Get user reimbursments failed");
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
	public ArrayList<Reimbursement> getReimbsByStatus(int status){
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from reimbursements where reimb_status_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, status);
			ResultSet rs = ps.executeQuery();
			
			ArrayList<Reimbursement> reimbs = new ArrayList<>();
			
			while(rs.next()) {
				Reimbursement r = new Reimbursement(
						rs.getInt("reimb_id"),
						rs.getDouble("reimb_amt"),
						rs.getTimestamp("reimb_submitted"),
						rs.getInt("reimb_author"),
						rs.getInt("reimb_status_id"),
						rs.getInt("reimb_type_id"),
						rs.getString("reimb_description")
						);
				reimbs.add(r);
			}
			
			return reimbs;
			
		} catch (SQLException e) {
			System.out.println("Get reimbursments by status failed");
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	
	
	
	
	
}
