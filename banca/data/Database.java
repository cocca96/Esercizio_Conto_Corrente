package banca.data;

import banca.domain.Cliente;

public interface Database {
	Iterable<Cliente> getAllClients();
	

}
