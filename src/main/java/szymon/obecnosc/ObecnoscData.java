/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package szymon.obecnosc;

import java.util.Date;

/**
 *
 * @author Szymon Chmielewski
 */
public class ObecnoscData {
    int idPracownika;
    Date dateIn;
    Date dateOut;
    
    public ObecnoscData(){
    }
    public void setDateOut(Date dateOut){
	this.dateOut = dateOut;
    }

    public void setIdPracownika(int idPracownika) {
	this.idPracownika = idPracownika;
    }

    public void setDateIn(Date dateIn) {
	this.dateIn = dateIn;
    }
    
 
    

}
