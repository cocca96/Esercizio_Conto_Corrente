package banca.ui;

import java.util.Scanner;

import banca.domain.Banca;
import banca.domain.Cliente;
import banca.domain.exception.SaldoInsufficenteException;

public class Menu {
	private Scanner scanIn=new  Scanner(System.in);
	
	public void scelta() {
		Banca b = Banca.getInstance();
		
		Double amount;
		int idConto, idCliente, idContoDest, idClienteDest;
		int num=0;
		boolean bool = true;
		String s;
		
		while (bool) {
			boolean isNumber = false;
		
			do {
				try {
					 num=ottieniIntero("Digita 1 per depositare, 2 per un bonifico, 3 per ritirare,"
							+ " 4 per la lista clienti, altro per uscire:");
					isNumber=true;
					}catch (NumberFormatException e) {
						System.out.println("Digita un numero");
			         }
				
			}while(!isNumber);
			
			switch(num) {
			case 1: deposita();
				break;
		
			case 2: bonifica();
				break;

			case 3: preleva();
				break;
				
			case 4: stampaClienti();
				break;
				
			default:
				System.out.println("Grazie e serena giornata");
				bool = false;
			}
		}
		scanIn.close();
	}
	private String ottieniStringa(String messaggio) {
		System.out.println(messaggio);
		return scanIn.nextLine();
		
		}
	private int ottieniIntero(String messaggio) {
		String s=ottieniStringa(messaggio);
		return Integer.parseInt(s);
		
	}
	private double ottieniDouble(String messaggio) {
		String s=ottieniStringa(messaggio);
		return Double.parseDouble(s);
		
	}
	private void deposita() {
		double amount=ottieniDouble("Inserisci quanto desideri depositare, poi premi invio:");
		int idConto = ottieniIntero("Inserisci ora il codice del tuo conto, poi premi invio:");
		int idCliente = ottieniIntero("Inserisci ora il tuo id, poi premi invio:");
		Banca.getInstance().Deposita(amount, idConto, idCliente);
		System.out.println("Deposito andato a buon fine");
		
	}
	private void bonifica() {
		double amount = ottieniDouble("Inserisci l'importo del bonifico, poi premi invio:");
		int idConto = ottieniIntero("Inserisci ora il codice del tuo conto, poi premi invio:");
		int idCliente =  ottieniIntero("Inserisci ora il tuo id, poi premi invio:");
		int idContoDest =  ottieniIntero("Inserisci ora il codice del conto di destinazione, poi premi invio:");
		int idClienteDest = ottieniIntero("Inserisci ora l'id del cliente a cui è associato il suddetto conto, poi premi invio:");
		try {
			Banca.getInstance().Bonifica(amount, idConto, idCliente, idContoDest, idClienteDest);
			System.out.println("Bonifico andato a buon fine");
		}
		catch (SaldoInsufficenteException e) {
			System.out.println(e.getMessage());
		}
		
	}
	private void preleva() {
		double amount = ottieniDouble("Inserisci l'importo del ritiro da effettuare");
		int idConto = ottieniIntero("Inserisci ora il codice del tuo conto, poi premi invio:");
		int idCliente = ottieniIntero("Inserisci ora il tuo id, poi premi invio:");
		
		try {
			Banca.getInstance().preleva(amount, idConto, idCliente);
		} catch (SaldoInsufficenteException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Prelievo andato a buon fine");
	}
	private void stampaClienti() {
		Iterable<Cliente> ic = Banca.getInstance().getClienti();
		System.out.println("Di seguito la lista dei clienti:");
		for(Cliente c : ic) {
			System.out.println(c);
		}
	}
	
	public void menuStatistiche() {
		Banca b = Banca.getInstance();
		
		
		int num=0;
		boolean bool = true;
		
		while (bool) {
			boolean isNumber = false;
		
			do {
				try {
					 num=ottieniIntero("Digita 1 per avere la somma degli stipendi, 2 per la media, 3 per mediana,"
							+ " 4 per la verifica, 5 per la lista degi impiegati maschi con meno di 25 anni , poi altro per uscire:");
					isNumber=true;
					}catch (NumberFormatException e) {
						System.out.println("Digita un numero");
			         }
				
			}while(!isNumber);
			
			switch(num) {
					
			case 1: System.out.println(b.sommaStipendi());
					
				break;
		
			case 2: System.out.println(b.mediaStipendi());

				break;

			case 3: System.out.println(b.medianaStipendi());
				break;
				
			case 4: b.verificaStipendi();
				break;
			case 5: b.listGiovaniImpiegati().forEach(System.out::println);
;
				break;
			default:
				System.out.println("Grazie e serena giornata");
				bool = false;
			}
		}
		scanIn.close();
	}
	
	
}
