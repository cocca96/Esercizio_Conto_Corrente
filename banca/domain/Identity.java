package banca.domain;

public class Identity {
	
	private double sum;
	private int numeroImpiegati;
	private double minStipendioMaschi = Double.POSITIVE_INFINITY;
	
	public Identity combina(Impiegato emp) {
		sum += emp.getStipendio();
		numeroImpiegati++;
		if(!emp.isFemale() &&  emp.getStipendio() < minStipendioMaschi) {
			minStipendioMaschi = emp.getStipendio();
		}
		return this;
	}
	
	public double getStipendioMedio() {
		return sum/numeroImpiegati;
	}

	public double getSum() {
		return sum;
	}
	
	public Identity combina(Identity other) {
		this.sum+=other.sum;
		this.numeroImpiegati+=other.numeroImpiegati;
		return this;
	}
	

}
