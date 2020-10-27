package banca.domain;

import java.time.LocalDate;

public class Impiegato {

	 private String nome; 
	  private String cognome; 
	  private int id; 
	  private Sesso sesso; 
	  private double stipendio;
	  private LocalDate dataNascita;
	  
	public Impiegato(String nome, String cognome,  int id, Sesso sesso, double stipendio,
			LocalDate dataNascita) {
		this.nome = nome;
		this.cognome = cognome;
		this.id = id;
		this.sesso = sesso;
		this.stipendio = stipendio;
		this.dataNascita = dataNascita;
	}

	public String getNome() {
		return nome;
	}
	public boolean isFemale() {
		/*if(this.sesso == Sesso.FEMMINA) {
			return true ;
		}else {
			return false;
		}*/
		return this.sesso == Sesso.FEMMINA;
	}

	public int getId() {
		return id;
	}

	public Sesso getSesso() {
		return sesso;
	}

	public double getStipendio() {
		return stipendio;
	}

	public LocalDate getDataNascita() {
		return dataNascita;
	}
	  
	  public String toString() {
		  return "NOME: "+ nome+" "+ " COGNOME: "+ cognome + " SESSO: "+sesso+" DATA NASCITA: "+dataNascita+" STIPENDIO: "+stipendio;
	  }


}


