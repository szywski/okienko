/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package szymon.obecnosc;

import java.sql.Connection;
//import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
//import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
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

    private boolean polacz() {

	try {
	    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    // String dbURL =
	    // "jdbc:sqlserver://localhost\\localhost\\MSSQLSERVER01:53116","admin","admin";
	    this.conn = DriverManager.getConnection(
		    "jdbc:sqlserver://localhost\\localhost\\MSSQLSERVER01:53116;databaseName=E_OBECNOSCI", "admin",
		    "admin");
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

    private boolean rozlacz() {

	try {

	    if (this.rs != null)
		this.rs.close();
	    if (this.st != null)
		this.st.close();
	    if (this.conn != null)
		this.conn.close();

	} catch (Exception ex) {
	    ex.printStackTrace();
	    return false;
	}
	return true;
    }

    public boolean rejestracjaWejscia(ObecnoscData obj) {

	obj.setIdPracownika(0);
	obj.setDateIn(getServerTime());

	

	String insertDaneWejsciaQuery = "INSERT INTO E_OBECNOSCI (ob_spr_id,ob_data) VALUES(4,'" + obj.dateIn + "')";
	try {
	    if (polacz()) {
		this.st = this.conn.createStatement();
		this.st.execute(insertDaneWejsciaQuery);

	    }
	} catch (SQLException ex) {
	    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
	    ex.printStackTrace();
	} finally {
	    rozlacz();
	}

	return true;
    }

    public void updateWyjscie(ObecnoscData obj) {
	obj.setDateOut(getServerTime());
	try {
	    String getObId = "SELECT TOP 1 * FROM E_OBECNOSCI WHERE ob_spr_id = " + obj.getIdPracownika()
		    + " ORDER BY ob_id DESC";
	    String updateWyjscie = "UPDATE E_OBECNOSCI SET ob_data_wyj ="+obj.dateOut+"  WHERE ob_id = 3";
	    if (polacz()) {
		this.st = this.conn.createStatement();
		ResultSet rs = st.executeQuery(getObId);
		this.st = this.conn.createStatement();

	    }
	} catch (SQLException ex) {
	    ex.printStackTrace();
	} finally {
	}

    }

    public Date getServerTime() {

	Date czasWejscia = new Date();
	String getDateQuery = "SELECT GETDATE();";
	if (polacz()) {
	    try {
		this.st = this.conn.createStatement();
		this.rs = this.st.executeQuery(getDateQuery);
		this.rs.next();
		Timestamp formatterDaty = rs.getTimestamp("");
		System.out.println(formatterDaty + " timestamp");
		if (formatterDaty != null) {

		    czasWejscia = formatterDaty;

		}

	    } catch (SQLException ex) {
		// Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
		ex.printStackTrace();
	    } finally {
		rozlacz();
	    }

	}
	return czasWejscia;
    }

}

