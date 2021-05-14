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
public class ObecnoscDataDAO {
    Connection conn = null;
    Statement st;
    ResultSet rs = null;
    boolean obecnosc = false;

    public ObecnoscDataDAO() throws SQLException {

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
	//ObecnoscDataDAOController controller = null;
	//if(controller.czyWszedl(obj, this)==false){
	
	obj.setIdPracownika(11);
	obj.setDateIn(getServerTime());

	String insertDaneWejsciaQuery = "INSERT INTO E_OBECNOSCI (ob_spr_id,ob_data) VALUES("+obj.idPracownika+",'" + obj.dateIn + "')";
	try {
	    if (polacz()) {
		this.st = this.conn.createStatement();
		this.st.execute(insertDaneWejsciaQuery);
		

	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ObecnoscDataDAO.class.getName()).log(Level.SEVERE, null, ex);
	    ex.printStackTrace();
	    return false;
	} finally {
	    rozlacz();
	    obecnosc = true;
	}
	//}
	return true;
    }

    public void rejestracjaWyjscia(ObecnoscData obj) {
	obj.setDateOut(getServerTime());
	System.out.println(obj.dateOut);
	try {

	    if (polacz()) {
		// wybieram najnowsze id(PK) logowania dla id pracownikad
		String getObId = "SELECT TOP 1 ob_id FROM E_OBECNOSCI WHERE ob_spr_id = " + obj.getIdPracownika()
			+ " ORDER BY ob_id DESC";
		this.st = this.conn.createStatement();
		ResultSet rs = st.executeQuery(getObId);
		
		rs.next();
		
		int id = rs.getInt("ob_id");
		System.out.println(id);

		// aktualizacja rekordu dla id logowania
		String updateWyjscie = "UPDATE E_OBECNOSCI SET ob_data_wyj ='" + obj.dateOut + "'  WHERE ob_id = " + id
			+ "";
		this.st = this.conn.createStatement();
		st.execute(updateWyjscie);

	    }
	} catch (SQLException ex) {
	    ex.printStackTrace();
	} finally {
	    rozlacz();
	        
	    // ustawiam obecność
	    obecnosc = false;

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

		if (formatterDaty != null) {

		    czasWejscia = formatterDaty;

		}

	    } catch (SQLException ex) {
		// Logger.getLogger(ObecnoscDataDAO.class.getName()).log(Level.SEVERE, null,
		// ex);
		ex.printStackTrace();
	    } finally {
		rozlacz();
	    }

	}
	return czasWejscia;
    }

}

