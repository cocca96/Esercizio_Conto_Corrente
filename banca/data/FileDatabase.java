package banca.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import banca.domain.Cliente;
import banca.domain.ContoCayman;
import banca.domain.ContoCorrente;
import banca.domain.ContoItaliano;
import banca.domain.Impiegato;
import banca.domain.Sesso;

public class FileDatabase implements Database{
	  
	  
	@Override
	public Iterable<Cliente> getAllClients() {	
        	return null;
		}

	public Iterable<Impiegato> getAllImpiegato() {
		
		  String nome; 
		  String cognome; 
		  int id; 
		  Sesso sesso; 
		  LocalDate dataNascita;
		  double salario;
		
		
		List<Impiegato> impiegati = new ArrayList<Impiegato>();
		
		try(BufferedReader reader = new BufferedReader(new FileReader("impiegati.txt"))) {
			String line ;

			while((line = reader.readLine())!=null) {
				
			
	            
	            
	            String[] riga  = line.split(",");
	            id = Integer.parseInt(riga[0]);
				nome = riga[1];
				cognome = riga[2];	
				sesso = Sesso.valueOf(riga[3]) ;
				dataNascita = LocalDate.parse(riga[4]) ;
				salario = Double.parseDouble(riga[5]) ;
				
				Impiegato impiegato = new Impiegato(nome,cognome,id,sesso, salario, dataNascita);
				impiegati.add(impiegato) ;
				
				
		}
        
        		
	}catch(IOException e ) {
		e.printStackTrace();
	}
		return impiegati;
	}
}       
