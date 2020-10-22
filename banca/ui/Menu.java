package banca.ui;

import java.util.Scanner;

import banca.domain.Banca;
import banca.domain.Cliente;
import banca.domain.exception.SaldoInsufficenteException;

public class Menu {
	
	public void scelta() {
		Banca b = Banca.getInstance();
		Scanner scanIn = new Scanner(System.in);
		Double amount;
		int idConto, idCliente, idContoDest, idClienteDest;
		int num;
		boolean bool = true;
		
		while (bool) {
			System.out.println("Digita 1 per depositare, 2 per un bonifico, 3 per ritirare,"
								+ " 4 per la lista clienti, altro per uscire:");
			num = Integer.parseInt(scanIn.nextLine());
			
			switch(num) {
			case 1:
				System.out.println("Inserisci quanto desideri depositare, poi premi invio:");
				amount = Double.parseDouble(scanIn.nextLine());
				System.out.println("Inserisci ora il codice del tuo conto, poi premi invio:");
				idConto = Integer.parseInt(scanIn.nextLine());
				System.out.println("Inserisci ora il tuo id, poi premi invio:");
				idCliente = Integer.parseInt(scanIn.nextLine());
				
				b.Deposita(amount, idConto, idCliente);
				System.out.println("Deposito andato a buon fine");
				break;
		
			case 2:
				System.out.println("Inserisci l'importo del bonifico, poi premi invio:");
				amount = Double.parseDouble(scanIn.nextLine());
				System.out.println("Inserisci ora il codice del tuo conto, poi premi invio:");
				idConto =  Integer.parseInt(scanIn.nextLine());
				System.out.println("Inserisci ora il tuo id, poi premi invio:");
				idCliente =  Integer.parseInt(scanIn.nextLine());
				System.out.println("Inserisci ora il codice del conto di destinazione, poi premi invio:");
				idContoDest =  Integer.parseInt(scanIn.nextLine());
				System.out.println("Inserisci ora l'id del cliente a cui è associato il suddetto conto, poi premi invio:");
				idClienteDest =  Integer.parseInt(scanIn.nextLine());
				
				try {
					b.Bonifica(amount, idConto, idCliente, idContoDest, idClienteDest);
					System.out.println("Bonifico andato a buon fine");
				}
				catch (SaldoInsufficenteException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 3:
				break;
				
			case 4:
				Iterable<Cliente> ic = b.getClienti();
				System.out.println("Di seguito la lista dei clienti:");
				for(Cliente c : ic) {
					System.out.println(c);
				}
				break;
				
			default:
				bool = false;
			}
		}
		scanIn.close();
	}
}
