/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szymon.obecnosc;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    
    
    

    // oknoObecnosc - wlasciwosci

    public OknoObecnosc() {
	super();
	flowLayout = new FlowLayout();
	flowLayout.setAlignment(FlowLayout.CENTER);
	ustawieniaOkna();
    }

    private void ustawieniaOkna() {
	//okno ogólnie
	oknoObecnosc.setSize(350, 150);
	oknoObecnosc.setTitle("Lista Obecności");
	oknoObecnosc.setLocationRelativeTo(null);
	//guzik pObecność
	//pObecnoscBtn.setSize(500, 30);	
	pObecnoscBtn.setText("Potwierdź obecność");	
	
	pObecnoscBtn.addActionListener(this);
	pObecnoscBtn.setAlignmentX(FlowLayout.CENTER);
	//guzik pWyjście
	//pWyjscieBtn.setSize(500, 30);
	pWyjscieBtn.setText("Potwierdź Wyjście");
	pWyjscieBtn.setEnabled(false);
	pWyjscieBtn.addActionListener(this);
	

	pWyjscieBtn.setAlignmentY(FlowLayout.CENTER);
	
	//okno
	oknoObecnosc.add(pObecnoscBtn);
	oknoObecnosc.add(pWyjscieBtn);
	oknoObecnosc.setLayout(flowLayout);
	oknoObecnosc.setDefaultCloseOperation(EXIT_ON_CLOSE);
	oknoObecnosc.setVisible(true);
    }
	@Override
	public void actionPerformed(ActionEvent e){
	    Object source = e.getSource();
	    
	    if(source == pObecnoscBtn){
		
		//oknoObecnosc.add(godzinaField);
		oknoObecnosc.add(napis);
		oknoObecnosc.add(dataField);
		napis.setText("Obecnośćć potwierdzono: ");
		
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"); //te onformacje powinny być pobierane z serwera?
		LocalDateTime now = LocalDateTime.now();
		
		//System.out.println(dtf.format(now));
		dataField.setText(dtf.format(now));
		
		try{
		    
		        DAO a = new DAO();
			a.insert(new ObecnoscData());
		    
		}catch(Exception ex){
		    ex.printStackTrace();
		}
		
		
		
		pObecnoscBtn.setEnabled(false);
		pWyjscieBtn.setEnabled(true);
	    
	    }
	    if(source == pWyjscieBtn){
		oknoObecnosc.dispose();
	    }
	    
	
	}
    

}
