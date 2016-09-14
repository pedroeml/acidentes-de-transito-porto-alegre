package pucrs.alpro2.tf;

public class Clima implements Comparable<Clima> {
	private int cont;
	private String clima;

	public Clima(String clima) {
		this.clima = clima;
		this.cont = 1;
	}

	public String getClima() {
		return this.clima;
	}

	public int getCont() {
		return this.cont;
	}

	public void incrementaCont() {
		this.cont++;
	}

	@Override
	public int compareTo(Clima c) {
		if (this.cont > c.getCont())
			return 1;
		else if (this.cont == c.getCont())
			return 0;
		// menor
		return -1;
	}

	@Override
	public String toString() {
		return this.getClima();
	}
}