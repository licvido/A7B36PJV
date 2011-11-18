/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adresar;

import java.text.Collator;
import java.util.Locale;

/**
 *
 * @author Filip
 */
public class Kontakt implements Comparable<Kontakt> {

    public static final Locale JAZYK = new Locale("cs", "CZ");

    private String jmeno;
    private String prijmeni;
    private String adresa;
    private String telefon;
    private String mobil;
    private String email;

    public Kontakt() {
        this("","","","","","");
    }

    public Kontakt(String jmeno, String prijmeni, String adresa, String telefon, String mobil, String email) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.adresa = adresa;
        this.telefon = telefon;
        this.mobil = mobil;
        this.email = email;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return this.prijmeni + " " + this.jmeno;
    }

    public int compareTo(Kontakt kontakt) {
        Collator col = Collator.getInstance(JAZYK);
        int porovnani = col.compare(this.prijmeni, kontakt.getPrijmeni());
        return porovnani == 0 ? col.compare(this.jmeno, kontakt.getJmeno()) : porovnani;
    }

}
