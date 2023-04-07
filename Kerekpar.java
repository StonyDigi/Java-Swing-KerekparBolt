package kerekparBolt;

import java.time.LocalDate;

public class Kerekpar {

	private Integer cikkSzam;
	private String nev;
	private String tipus;
	private Integer ar;
	private boolean hasznalt;
	private Byte kategoriaId;
	private LocalDate rogzitesDatuma;

	public Kerekpar(Integer cikkSzam, String nev, String tipus, Integer ar, boolean hasznalt, Byte kategoriaId,
			LocalDate rogzitesDatuma) {

		this.cikkSzam = cikkSzam;
		this.nev = nev;
		this.tipus = tipus;
		this.ar = ar;
		this.hasznalt = hasznalt;
		this.kategoriaId = kategoriaId;
		this.rogzitesDatuma = rogzitesDatuma;
	}

	public Integer getCikkSzam() {
		return cikkSzam;
	}

	public String getNev() {
		return nev;
	}

	public String getTipus() {
		return tipus;
	}

	public Integer getAr() {
		return ar;
	}

	public boolean getHasznalt() {
		return hasznalt;
	}

	public Byte getKategoriaId() {
		return kategoriaId;
	}

	public LocalDate getRogzitesDatuma() {
		return rogzitesDatuma;
	}

	public String toString() {
		return this.cikkSzam + ";" + this.nev + ";" + this.tipus + ";" + this.ar + ";" + this.hasznalt + ";"
				+ this.kategoriaId + ";" + this.rogzitesDatuma + "\n";

	}

	public String getHasznaltSzovegesen() {
		String valasz = "�j";
		if (this.hasznalt) {
			valasz = "haszn�lt";

		}
		return valasz;
	}

	public String getKategoriaIdSzovegesen() {
		String kategoriaSzoveg = null;

		switch (this.kategoriaId) {
		case 1:
			kategoriaSzoveg = "f�rfi";
			break;
		case 2:
			kategoriaSzoveg = "n�i";
			break;
		case 3:
			kategoriaSzoveg = "gyermek";
			break;

		default:
			kategoriaSzoveg = "hib�s adat";
			break;
		}
		return kategoriaSzoveg;
	}

}
