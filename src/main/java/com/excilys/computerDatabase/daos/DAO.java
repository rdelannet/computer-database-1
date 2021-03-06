package com.excilys.computerDatabase.daos;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerDatabase.models.Page;
import com.zaxxer.hikari.HikariDataSource;





@Repository
public abstract class DAO<T> {
	protected static Logger logger = LoggerFactory.getLogger(DAO.class);
	protected static Connection connect = null;
	
	@Autowired
	protected HikariDataSource dataSource;

	public DAO(){
	}
	



	protected Connection getConnection() throws SQLException {
		if(connect==null || connect.isClosed()) {
			connect = dataSource.getConnection();
		}
		return connect;
	}
	/**
	 * GetAll database
	 * @return list of object from database 
	 */
	public abstract ArrayList<T> getAll();

	/**
	 * Create a new object in database
	 * @param obj to create
	 * @return obj created
	 */
	public abstract T create(T obj);

	/**
	 * delete object in database
	 * @param object to delete
	 * @return boolean (true if deleted)
	 */
	public abstract boolean delete(Long id);

	/**
	 * update an object in database
	 * @param obj to update
	 * @return Obj updated
	 */
	public abstract T update(T obj);

	/**
	 * find an object from id
	 * @param id
	 * @return T obj find
	 */
	public abstract T find(Long id);

	/**
	 * Method to get max id in database
	 * @return int maxId
	 */
	public abstract Long maxId() throws SQLException;


	/**
	 * Get nbr of object in database
	 * @return nbr of object
	 */
	public abstract Long size();
	
	
	public abstract Long sizeWithSearch(String search);
	/**
	 * 
	 * Method get a precise page from database
	 * @param debut start of the page(offset)
	 * @param number number of element to return
	 * @return List<T> page
	 */
	public abstract ArrayList<T> getPage(Page page);
	
	public abstract ArrayList<T> getPageWithSearch(Page page);
	



	public static void close(AutoCloseable connection) {
		if(connection!=null) {
			try {
				connection.close();

			}catch(Exception e) {
			}
		}
	}





}