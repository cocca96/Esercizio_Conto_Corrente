package banca.data;

import banca.domain.Cliente;
import banca.domain.Impiegato;

public interface Database {
	
	Iterable<Cliente> getAllClients();
	
	Iterable<Impiegato> getAllImpiegato();
}
