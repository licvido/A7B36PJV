package adresar;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.TreeSet;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.TransformerException;

/**
 *
 * @author Filip
 */
public class NacteniKontaktu {
    
    private final File soubor;
    private final XMLStreamReader reader;
    private Kontakt pom;
    private final TreeSet<Kontakt> kontakty;

    public static final String ELEMENT_KONTAKT = "kontakt";
    public static final String ELEMENT_PRIJMENI = "prijmeni";
    public static final String ELEMENT_JMENO = "jmeno";
    public static final String ELEMENT_ADRESA = "adresa";
    public static final String ELEMENT_TELEFON = "telefon";
    public static final String ELEMENT_MOBIL = "mobil";
    public static final String ELEMENT_EMAIL = "email";
    public static final String ROOT_ELEMENT = "adresar";

    public NacteniKontaktu(final String xmlSoubor) throws XMLStreamException, IOException, TransformerException {
        this(new File(xmlSoubor));
    }

    public NacteniKontaktu(final File xmlSoubor) throws XMLStreamException, IOException, TransformerException {
        this.soubor = xmlSoubor;
        this.kontakty = new TreeSet<Kontakt>();
        this.pom = null;

        if(!this.soubor.exists()) {
            ZapisovacKontaktu zapisovac = new ZapisovacKontaktu(this.soubor);
            zapisovac.zapisDoXML(this.kontakty);
        }

        XMLInputFactory factory = XMLInputFactory.newInstance();
        this.reader = factory.createXMLStreamReader(new FileInputStream(this.soubor));        
    }

    public void zpracujXML() throws XMLStreamException {
        while (this.reader.hasNext()) {
            this.reader.next();

            if (this.reader.isStartElement()) {
                String element = this.reader.getLocalName();

                if (element.equals(ROOT_ELEMENT)) {
                    continue;
                }

                if (element.equals(this.ELEMENT_KONTAKT)) {
                    this.pom = new Kontakt();
                    continue;
                }

                String text = this.reader.getElementText();

                if (element.equals(this.ELEMENT_JMENO)) {
                    this.pom.setJmeno(text);
                }

                if (element.equals(this.ELEMENT_PRIJMENI)) {
                    this.pom.setPrijmeni(text);
                }

                if (element.equals(this.ELEMENT_ADRESA)) {
                    this.pom.setAdresa(text);
                }

                if (element.equals(this.ELEMENT_TELEFON)) {
                    this.pom.setTelefon(text);
                }

                if (element.equals(this.ELEMENT_MOBIL)) {
                    this.pom.setMobil(text);
                }

                if (element.equals(this.ELEMENT_EMAIL)) {
                    this.pom.setEmail(text);
                }
            }

            if (this.reader.isEndElement()) {
                String element = this.reader.getLocalName();

                if (element.equals(this.ELEMENT_KONTAKT)) {
                    this.kontakty.add(this.pom);
                    this.pom = null;
                }
            }
        }
    }

    public TreeSet<Kontakt> getKontakty() {
        return this.kontakty;
    }
}