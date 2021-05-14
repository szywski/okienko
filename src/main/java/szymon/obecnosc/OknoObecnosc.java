/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szymon.obecnosc;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

/**
 * @author Szymon Chmielewski
*/
public class OknoObecnosc extends JFrame implements ActionListener {
    private final JFrame oknoObecnosc = new JFrame();
    private final JButton pObecnoscBtn = new JButton();
    private final JButton pWyjscieBtn = new JButton();
    private final JLabel napis = new JLabel();
    private final JFormattedTextField dataField = new JFormattedTextField();
    private final JFormattedTextField godzinaField = new JFormattedTextField();
    FlowLayout flowLayout;
    private ObecnoscData pracownik;
    private ObecnoscDataDAO a;

    // oknoObecnosc - wlasciwosci

    public OknoObecnosc() {
	super();
	flowLayout = new FlowLayout();
	flowLayout.setAlignment(FlowLayout.CENTER);
	ustawieniaOkna();
	pracownik = new ObecnoscData();
	try {
	    a = new ObecnoscDataDAO();
	} catch (SQLException ex) {
	    Logger.getLogger(OknoObecnosc.class.getName()).log(Level.SEVERE, null, ex);
	}
	pracownik.setIdPracownika(3);
    }

    private void ustawieniaOkna() {
	// okno ogólnie
	oknoObecnosc.setSize(350, 150);
	oknoObecnosc.setTitle("Lista Obecności");
	oknoObecnosc.setLocationRelativeTo(null);
	// guzik pObecność
	// pObecnoscBtn.setSize(500, 30);
	pObecnoscBtn.setText("Potwierdź obecność");

	pObecnoscBtn.addActionListener(this);
	pObecnoscBtn.setAlignmentX(FlowLayout.CENTER);
	// guzik pWyjście
	// pWyjscieBtn.setSize(500, 30);
	pWyjscieBtn.setText("Potwierdź Wyjście");
	pWyjscieBtn.setEnabled(false);
	pWyjscieBtn.addActionListener(this);

	pWyjscieBtn.setAlignmentY(FlowLayout.CENTER);

	// okno
	
	 pObecnoscBtn.addActionListener(e ->{
	    
	  
	// dzialania po kliknieciu guzika potwierdzjaacego obecnosc
		
		napis.setText("Obecnośćć potwierdzono: ");
		a.rejestracjaWejscia(pracownik);
				
		dataField.setText(pracownik.dateIn.toString());
		pObecnoscBtn.setEnabled(false);
		pWyjscieBtn.setEnabled(true);
	   });
	 
	
	 pWyjscieBtn.addActionListener(e ->{
		
	     
	        napis.setText("Wyjście potwierdzono: ");
		a.rejestracjaWyjscia(pracownik);
		System.out.println(pracownik.dateOut.toString());
		dataField.setText(pracownik.dateOut.toString());
	 });    
	
	
	oknoObecnosc.add(pObecnoscBtn);
	oknoObecnosc.add(pWyjscieBtn);
	oknoObecnosc.add(napis);
	oknoObecnosc.add(dataField);
	oknoObecnosc.setLayout(flowLayout);
	oknoObecnosc.setDefaultCloseOperation(EXIT_ON_CLOSE);
	oknoObecnosc.setVisible(true);
	oknoObecnosc.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
   
    
   

}


