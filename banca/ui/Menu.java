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
			case 1:
				amount=ottieniDouble("Inserisci quanto desideri depositare, poi premi invio:");
				idConto = ottieniIntero("Inserisci ora il codice del tuo conto, poi premi invio:");
				idCliente = ottieniIntero("Inserisci ora il tuo id, poi premi invio:");
				b.Deposita(amount, idConto, idCliente);
				System.out.println("Deposito andato a buon fine");
				break;
		
			case 2:
				amount = ottieniDouble("Inserisci l'importo del bonifico, poi premi invio:");
				idConto = ottieniIntero("Inserisci ora il codice del tuo conto, poi premi invio:");
				idCliente =  ottieniIntero("Inserisci ora il tuo id, poi premi invio:");
				idContoDest =  ottieniIntero("Inserisci ora il codice del conto di destinazione, poi premi invio:");
				idClienteDest = ottieniIntero("Inserisci ora l'id del cliente a cui è associato il suddetto conto, poi premi invio:");
				try {
					b.Bonifica(amount, idConto, idCliente, idContoDest, idClienteDest);
					System.out.println("Bonifico andato a buon fine");
				}
				catch (SaldoInsufficenteException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 3:
				amount = ottieniDouble("Inserisci l'importo del ritiro da effettuare");
				idConto = ottieniIntero("Inserisci ora il codice del tuo conto, poi premi invio:");
				idCliente = ottieniIntero("Inserisci ora il tuo id, poi premi invio:");
				
				try {
					b.preleva(amount, idConto, idCliente);
				} catch (SaldoInsufficenteException e) {
					System.out.println(e.getMessage());
				}
				System.out.println("Deposito andato a buon fine");
				
				break;
				
			case 4:
				Iterable<Cliente> ic = b.getClienti();
				System.out.println("Di seguito la lista dei clienti:");
				for(Cliente c : ic) {
					System.out.println(c);
				}
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
}
