package banca.ui;

import banca.domain.Banca;
import banca.domain.Cliente;
import banca.domain.Impiegato;
import banca.domain.exception.SaldoInsufficenteException;

public class BankInterface {

	public static void main(String[] args) {
		
		Menu m=new Menu();
		//m.scelta();
		m.menuStatistiche();
			
		/*
		Banca b = Banca.getInstance();
		
		Iterable<Impiegato> impiegati = b.getImpiegato();
		
		for(Impiegato c : impiegati) {
			System.out.println(c);
		}
		System.out.println(b.sommaStipendi());
		System.out.println(b.mediaStipendi());
		System.out.println(b.medianaStipendi());
		b.verificaStipendi();
		
		b.listGiovaniImpiegati().forEach(System.out::println);
		/*b.Deposita(100, 1, 1);
		
		for(Cliente c : ic) {
			System.out.println(c);
		}
		
		try {
			b.Bonifica(1000, 36, 2, 3, 1);
		} catch (SaldoInsufficenteException e) {
			System.out.println(e.getMessage());
		}
		
		for(Cliente c : ic) {
			System.out.println(c);
		}*/
	}

}
