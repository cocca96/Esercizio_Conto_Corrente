package banca.data;

import java.util.ArrayList;
import java.util.List;

import banca.domain.Cliente;
import banca.domain.ContoCayman;
import banca.domain.ContoCorrente;
import banca.domain.ContoItaliano;
import banca.domain.Impiegato;
import banca.domain.Sesso;

public class InMemoryDatabase implements Database {
		private Iterable<Cliente> clients=readAllClients();
		
		private Iterable<Cliente> readAllClients(){
			List<Cliente> clienti = new ArrayList<Cliente>();
			
			
			Cliente c1 = new Cliente(1, "ciccio", "pasticcio", 22, Sesso.MASCHIO);
			ContoCorrente cc1 = new ContoCayman(1,100,"asdafaf");
			c1.aggiungiConto(cc1);
			ContoCorrente cc2 = new ContoItaliano(3,300);
			c1.aggiungiConto(cc2);
			clienti.add(c1);
			
			Cliente c2 = new Cliente(2, "paola", "carlito", 24, Sesso.FEMMINA);
			ContoCorrente cc12 = new ContoCayman(15,1000,"asdafar");
			c2.aggiungiConto(cc12);
			ContoCorrente cc22 = new ContoItaliano(36,3000);
			c2.aggiungiConto(cc22);
			clienti.add(c2);
			
			return clienti;
		}
		
		public Iterable<Cliente> getAllClients(){
			return clients;
		}

		@Override
		public Iterable<Impiegato> getAllImpiegato() {
			// TODO Auto-generated method stub
			return null;
		}
		
		
}

