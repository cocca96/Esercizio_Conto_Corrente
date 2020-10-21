package banca.ui;

import java.util.Scanner;

import banca.domain.Banca;

public class Menu {
	
	
	
	
	
	public void scelta() {
		Banca b=Banca.getInstance();
		Scanner scanIn = new Scanner(System.in);
		String s;
		
		System.out.println("Scegli tra 1, 2 o 3");
		s=scanIn.nextLine();
		while(true) {
			switch(s) {
			case "0":
				System.out.println("Inserisci quanto desideri depositare, il codice del tuo conto e il tuo id");
				String depositoString;
				String idContoString;
				String idClienteString;
				depositoString=scanIn.nextLine();
				double deposito=Double.parseDouble(depositoString);
				
				idContoString=scanIn.nextLine();
				int idConto=Integer.parseInt(idContoString);
				idClienteString=scanIn.nextLine();
				int idCliente=Integer.parseInt(idClienteString);
				b.Deposita(deposito, idConto, idCliente);
				
				break;
				
			case "1":
				
				System.out.println("Inserisci quanto desideri depositare per il bonifico, il codice del tuo conto e il tuo id e poi inserisci l'id del conto destinatario e l'id del cliente destinatario");
				String bonificoString;
				String idContoSorgenteString;
				String idClienteSorgenteString;
				String idContoDestinatarioString;
				String idClientestinatarioString;
				
				depositoString=scanIn.nextLine();
				//double bonifico=Double.parseDouble(bonificoString);
				
				idContoString=scanIn.nextLine();
				//int idContoSorgente=Integer.parseInt(idContoSorgenteString);
				idClienteString=scanIn.nextLine();
				int idClienteSorgente=Integer.parseInt(idClienteString);
				idClienteString=scanIn.nextLine();
				int idContoDestinatario=Integer.parseInt(idClienteString);
				
				//b.Bonifica(bonifico, idContoSorgente, idClienteSorgente, idContoDestinatario, idClienteDestinatario);
			
				break;
				
				
			}
		}
		
	}

}
