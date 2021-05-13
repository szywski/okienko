/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package szymon.obecnosc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Szymon Chmielewski
 */
public class DAO {
    Connection conn = null;
    Statement st;
    ResultSet rs = null;

    public DAO() throws SQLException {
	
	
    }
  
    
    private boolean polacz(){
	
	try {
	    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    // String dbURL =
	    // "jdbc:sqlserver://localhost\\localhost\\MSSQLSERVER01:53116","admin","admin";
	    this.conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\localhost\\MSSQLSERVER01:53116",
		    "admin", "admin");
	    if (conn != null) {
		System.out.println("Connected");
	    }

	    System.out.println("Połącono z DB");
	} catch (Exception e) {
	    System.out.println("brak polaczenia");
	    e.printStackTrace();
	    return false;
	}
	return true;
    }
    
    private boolean rozlacz(){
	
	try{
	    
	    if (this.rs != null)
		this.rs.close();
	    if (this.st != null)
		this.st.close();
	    if (this.conn != null)
		this.conn.close();
	    
	}catch(Exception ex){
	    ex.printStackTrace();
	    return false;
	}
	return true;
    }
    
    public boolean insert(ObecnoscData obj){
	
	obj.setIdPracownika(0);
	
	String getDateQuery = "SELECT GETDATE();";
	if (polacz()){
	try {
	    
	    
	    this.st = this.conn.createStatement();
	    this.rs = this.st.executeQuery(getDateQuery);
	    obj.setDateIn(this.rs.getDate("GET"));
	    System.out.println(obj.dateIn);
	    

	} catch (SQLException ex) {
	   // Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
	      ex.printStackTrace();
	}
	}	
	
	
	String query = "INSERT INTO E_OBECNOSC ('ob_spr_id','ob_data','ob_data_wyj') VALUES('"+obj.idPracownika+"','"+obj.dateIn+"','"+obj.dateOut+"')";
	try {
	    Statement st = this.conn.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    
	    
	    
	} catch (SQLException ex) {
	    //Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
	}
	
	
	
	
	
    return true;
    }
    
    
    
}

