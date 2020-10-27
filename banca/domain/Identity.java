package banca.domain;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Identity {
	private int listSize;
	private double sum, maxStipFemmine, minStipMaschi = Double.POSITIVE_INFINITY;
	private List<Impiegato> giovanotti;
	private List<Double> listaStip;
	
	public Identity (int size) {
		listSize = size;
		giovanotti = new ArrayList<Impiegato>();
		listaStip = new ArrayList<Double>(listSize);
	}
	
	public static Identity combina (Identity identity, Impiegato emp) {
		Identity newIdentity = new Identity(identity.listSize);
		double stipendio = emp.getStipendio();
		newIdentity.sum = identity.sum + stipendio;
		newIdentity.listaStip.addAll(identity.listaStip);
		newIdentity.listaStip.add(stipendio);
		
		newIdentity.giovanotti.addAll(identity.giovanotti);
		newIdentity.minStipMaschi = identity.minStipMaschi;
		newIdentity.maxStipFemmine = identity.maxStipFemmine;
		if (!emp.isFemale()) {
			if (stipendio < identity.minStipMaschi) {
				newIdentity.minStipMaschi = stipendio;
			}
			if (Period.between(emp.getDataNascita(), LocalDate.now()).getYears() < 25) {
				newIdentity.giovanotti.add(emp);
			}
		}
		else {
			if (stipendio > identity.maxStipFemmine) {
				newIdentity.maxStipFemmine = stipendio;
			}
		}
		return newIdentity;
	}
	
	
	public double getSum() {
		return sum;
	}
	
	public double getStipendioMedio() {
		return sum/(listSize);
	}
	
	public double getMediana() {
		listaStip.sort((e1, e2) -> Double.compare(e1, e2));
		double mediana = listaStip.get(listSize/2);
		if (listSize%2==0) {
			mediana = (mediana + listaStip.get(listSize/2 - 1)) / 2;
		}
		return mediana;
	}
	
	public double getModa() {
		Double mode = listaStip.stream()
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()))
                .entrySet().stream().sorted((a, b) -> b.getValue().compareTo(a.getValue())).findFirst().get().getKey();

		/*
		listaStip.sort((e1, e2) -> Double.compare(e1, e2));
		int max = 0, temp = 1;
		double moda = listaStip.get(0);
		for (int i=0; i<listaStip.size()-1; i++) {
			if (listaStip.get(i).equals(listaStip.get(i+1))) {
				temp++;
			}
			else {
				if (temp > max) {
					max = temp;
					moda = listaStip.get(i);
				}
				temp = 1;
			}
		}
		*/
		return mode;
	}
	
	public boolean verificaStipendi() {
		return minStipMaschi > maxStipFemmine;
	}
	
	public List<Impiegato> getListaGiovanotti() {
		return giovanotti;
	}
	
	public static Identity combina (Identity i1, Identity i2) {
		Identity newIdentity = new Identity(i1.listSize);
		newIdentity.sum = i1.sum + i2.sum;
		newIdentity.minStipMaschi = Math.min(i1.minStipMaschi, i2.minStipMaschi);
		newIdentity.maxStipFemmine = Math.max(i1.maxStipFemmine, i2.maxStipFemmine);
		newIdentity.giovanotti.addAll(i1.getListaGiovanotti());
		newIdentity.giovanotti.addAll(i2.getListaGiovanotti());
		newIdentity.listaStip.addAll(i1.listaStip);
		newIdentity.listaStip.addAll(i2.listaStip);
		return newIdentity;
	}
	
	public void stampa() {
		String separatore = System.getProperty("line.separator");
		String outputString = "Somma: " +sum+separatore+ "Media: " +getStipendioMedio()+separatore+ "Mediana: " +getMediana()
							  +separatore+"Verifica: " +verificaStipendi()+separatore+"Moda: "+getModa()+
							  separatore+"Di seguito i giovani impiegati:";
		System.out.println(outputString);
		giovanotti.forEach(System.out::println);
	}
}


/*
 * 	public static Identity combina (Identity identity, Impiegato emp) {
		Identity newIdentity = new Identity(identity.listSize);
		double stipendio = emp.getStipendio();
		identity.sum += stipendio;
		
		if ((identity.listSize%2) == 0) {
			if ((identity.listSize/2)==identity.numImpiegati || (identity.listSize/2-1)==identity.numImpiegati) {
				newIdentity.mediana = identity.mediana + stipendio/2;
			}
		}
		else {
			if ((identity.listSize/2)==identity.numImpiegati) {
				newIdentity.mediana = identity.mediana + stipendio;
			}
		}
		newIdentity.numImpiegati = ++identity.numImpiegati;
		
		if (!emp.isFemale()) {
			if (stipendio < identity.minStipMaschi) {
				identity.minStipMaschi = stipendio;
			}
			if (Period.between(emp.getDataNascita(), LocalDate.now()).getYears() < 25) {
				identity.giovanotti.add(emp);
			}
		}
		else {
			if (stipendio > identity.maxStipFemmine) {
				identity.maxStipFemmine = stipendio;
			}
		}
		newIdentity.minStipMaschi = stipendio;
		newIdentity.minStipMaschi = stipendio;
		return newIdentity;
	}
	
	
	public double getSum() {
		return sum;
	}
	
	public double getStipendioMedio() {
		return sum/(listSize+1);
	}
	
	public double getMediana() {
		return mediana;
	}
	
	public boolean verificaStipendi() {
		return minStipMaschi > maxStipFemmine;
	}
	
	public List<Impiegato> getListaGiovanotti() {
		return giovanotti;
	}
	
	public static Identity combina (Identity i1, Identity i2) {
		Identity newIdentity = new Identity(i1.listSize);
		newIdentity.sum = i1.sum + i2.sum;
		newIdentity.numImpiegati = i1.numImpiegati;
		newIdentity.mediana = i1.mediana + i2.mediana;
		newIdentity.minStipMaschi = Math.min(i1.minStipMaschi, i2.minStipMaschi);
		newIdentity.maxStipFemmine = Math.max(i1.maxStipFemmine, i2.maxStipFemmine);
		newIdentity.giovanotti.addAll(i1.getListaGiovanotti());
		newIdentity.giovanotti.addAll(i2.getListaGiovanotti());
		return newIdentity;
	}
	
	public void stampa() {
		String separatore = System.getProperty("line.separator");
		String outputString = "Somma: " +sum+separatore
				+ "Media: " +getStipendioMedio()+separatore
				+ "Mediana: " +mediana+separatore
				+"Verifica: " +verificaStipendi()+separatore
				+"Di seguito i giovani impiegati:";
		System.out.println(outputString);
		giovanotti.forEach(System.out::println);
	}
 */