package banca.domain;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import banca.data.AgeCalculator;
import banca.data.Database;
import banca.data.FileDatabase;
import banca.data.InMemoryDatabase;
import banca.domain.exception.SaldoInsufficenteException;

public class Banca {
			
	//singleton 1 solo oggetto istanziato nella classe
	
	private static Banca instance = new Banca();
	private String nome = "Bank of Java";
	private String[] codiciSegreti = {"adfhfda","asdafaf","zxcxv"};
		
	private Database database = new FileDatabase();
	private Database dbClienti = new InMemoryDatabase();
	Collection<Impiegato> listImpiegati = (Collection<Impiegato>) database.getAllImpiegato();


	
	private Banca() {
	}
	
	
	public Iterable<Cliente> getClienti() {
		return dbClienti.getAllClients();
	}
	
	public Iterable<Impiegato> getImpiegato() {
		return database.getAllImpiegato();
	}
	

	public static Banca getInstance() {
		return instance;
	}
	
	public boolean verificaCodice(String codiceSegreto) {
		
		for(String codice : codiciSegreti) {
			if (codiceSegreto.equals(codice)) {
				return true;
			}
		}
	return false;
	}
	
	public void Deposita(double deposito, int idConto, int idCliente) {
		for(Cliente cliente : database.getAllClients()) {
			int id = cliente.getId();
			if(idCliente == id) {
				ContoCorrente x = cliente.getContoById(idConto);
				
				x.deposita(deposito);
				return;
			}
		}
		
	}
	
	public void Bonifica(double bonifico, int idContoSorgente, int idClienteSorgente,
			int idContoDestinatario, int idClienteDestinatario) throws SaldoInsufficenteException {
		
		Cliente sorgente = null;
		Cliente destinatario = null;
		
		for(Cliente c : database.getAllClients()) {
			int id = c.getId();
			if(idClienteSorgente == id) {
				sorgente=c;
// andrebbe nell'oggetto e modificherebbe sorgente
				//c.setName("Paolo")
			}else if(idClienteDestinatario == id){
				destinatario=c;
			}
		}
		sorgente.getContoById(idContoSorgente)
			.bonifica(bonifico, destinatario.getContoById(idContoDestinatario));		
	}
	
	public void preleva(double somma, int idConto, int idCliente) throws SaldoInsufficenteException {
		for(Cliente cliente : database.getAllClients()) {
			int id = cliente.getId();
			if(idCliente == id) {
				ContoCorrente x = cliente.getContoById(idConto);
				
				if(x.getSaldo()<somma) {
					throw new SaldoInsufficenteException(x.getSaldo(), somma, idConto);
				}else {
				x.preleva(somma);
				
				}
				return;
			}
		}
		
		
		
	}

	public double sommaStipendi() {
		double sommaStipendi = listImpiegati.stream()
								.mapToDouble(x -> x.getStipendio())
								.sum();
		
		return sommaStipendi;							
		
	}
	
	public double mediaStipendi() {
		double mediaStipendi = listImpiegati.stream()
				.mapToDouble(x -> x.getStipendio())
				.average().getAsDouble();

		return mediaStipendi;
	}
	
	public double medianaStipendi() {
		DoubleStream sortStipendi = listImpiegati.stream()
				.mapToDouble(Impiegato::getStipendio)
				.sorted();
		double mediana = listImpiegati.size()%2 == 0?
				sortStipendi.skip(listImpiegati.size()/2-1).limit(2).average().getAsDouble():        
				sortStipendi.skip(listImpiegati.size()/2).findFirst().getAsDouble();

		return mediana;
	}
	
	public void verificaStipendi() {
		double maxStipendioFemmina = listImpiegati.stream()
				.filter(x -> x.getSesso() == Sesso.FEMMINA)
				.mapToDouble(x -> x.getStipendio())
				.max().getAsDouble();
		
		double minStipendioMaschio = listImpiegati.stream()
				.filter(x -> x.getSesso() == Sesso.MASCHIO)
				.mapToDouble(x -> x.getStipendio())
				.min().getAsDouble();
		if(minStipendioMaschio > maxStipendioFemmina) {
			System.out.println("il minimo stipendio degli uomini è maggiore del"
					+ " massimo stipendio delle donne");
		}
	}
	
	/*public List<Impiegato> listGiovaniImpiegati(){
		
		List<Impiegato> giovanotti = listImpiegati.stream()
				.filter(x -> ((x.getSesso() == Sesso.MASCHIO) && ( 25 > AgeCalculator
				.calculateAge(x.getDataNascita(), LocalDate.of(2020, 7, 12)))))
				.collect(Collectors.toList());
		
        
		return giovanotti;
	}
	*/
public List<Impiegato> listGiovaniImpiegati(){
		
		List<Impiegato> giovanotti = listImpiegati.stream()
				.filter(x -> ((x.getSesso() == Sesso.MASCHIO) && ( 25 > Period
				.between(x.getDataNascita(), LocalDate.of(2020, 7, 12))
				.getYears())))
				.collect(Collectors.toList());
		
        
		return giovanotti;
	}

}
