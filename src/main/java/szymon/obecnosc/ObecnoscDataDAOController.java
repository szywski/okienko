
package szymon.obecnosc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Szymon Chmielewski
 */
abstract class ObecnoscDataDAOController {
    

    public boolean czyWszedl(ObecnoscData obj,ObecnoscDataDAO a){
	if(obj.dateIn != null && a.getServerTime().getTime() - obj.dateIn.getTime() <  4320000){//24h
	    return true;
	}
    
    return false;
    }
    public boolean czyWyszedl(ObecnoscData obj,ObecnoscDataDAO a){
	if(obj.dateIn != null && a.getServerTime().getTime() - obj.dateIn.getTime() <  8640000){//24h w milisekundach
	    return false;
	}
	else return true;
      }

}
