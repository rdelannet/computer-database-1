package com.excilys.computerDatabase.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.mapper.CompanyMapper;

public class CompanyDao extends DAO<Company> {
	
	
	private final static String INSERT = "INSERT INTO company(name) VALUES(?);";
	private final static String DELETE = "DELETE FROM company WHERE id = ?;";
	private final static String UPDATE = "UPDATE company SET name = ? WHERE id = ?;";
	private final static String FINDALL = "select * from company";
	private final static String FIND = "select * from company where id=";
	private final static String MAXID = "select MAX(id) from company ";
	private final static String SIZE = "select count(*) from company";
	private final static String GET_PAGE = "select * from company LIMIT ?,?";
	



	
	@Override
	public ArrayList<Company> getAll() {
		// TODO Auto-generated method stub
		ArrayList<Company> comps = new ArrayList<Company>();
		Statement myStmt;
		try {
			myStmt = connect.createStatement();
			ResultSet myRs = myStmt.executeQuery(FINDALL);
			while(myRs.next()) {
				comps.add(CompanyMapper.mapCompany(myRs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return comps;
	}
	
	@Override
	public Company create(Company obj) {
		try {
	           
            PreparedStatement preparedStatement = connect.prepareStatement(INSERT);
            preparedStatement.setString(1, obj.getName());
            preparedStatement.executeUpdate();
            return find(maxId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}

	@Override
	public boolean delete(Company obj) {
		try {
	           
            PreparedStatement preparedStatement = connect.prepareStatement(DELETE);
            preparedStatement.setInt(1, obj.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public Company update(Company obj) {
		try {
            PreparedStatement preparedStatement = connect.prepareStatement(UPDATE);
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setInt(2, obj.getId());
            preparedStatement.executeUpdate();
            return find(obj.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}

	@Override
	public Company find(int id) {
		// TODO Auto-generated method stub
		Company company = null;
		Statement myStmt;
		try {
			myStmt = connect.createStatement();
			ResultSet myRs = myStmt.executeQuery(FIND+id);
			myRs.next();
			company= CompanyMapper.mapCompany(myRs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return company;
	}

	@Override
	public int maxId() throws SQLException{
		Statement myStmt;
		myStmt = connect.createStatement();
		ResultSet myRs = myStmt.executeQuery(MAXID);
		myRs.next();
		return myRs.getInt("MAX(id)");
	}
	@Override
	public int size() {
		Statement myStmt;
		try {
			myStmt = connect.createStatement();
			ResultSet myRs = myStmt.executeQuery(SIZE);
			myRs.next();
			return myRs.getInt("count(*)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return 0;
		}
	}

	@Override
	public ArrayList<Company> getPage(int debut, int number) {
		ArrayList<Company> comps = new ArrayList<Company>();
		try {
			PreparedStatement preparedStatement = connect.prepareStatement(GET_PAGE);
	        preparedStatement.setInt(1, debut);
	        preparedStatement.setInt(2, number);
			ResultSet myRs = preparedStatement.executeQuery();
			while(myRs.next()) {
				comps.add(CompanyMapper.mapCompany(myRs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return comps;
	}

	

}
