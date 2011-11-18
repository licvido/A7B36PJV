package adresar;

import java.io.File;
import java.io.IOException;
import java.util.TreeSet;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

/**
 *
 * @author Filip
 */
public class Adresar {
    private final File xml;
    private final TreeSet<Kontakt> kontakty;
    private final NacteniKontaktu nacitac;
    private final ZapisovacKontaktu zapisovac;

    public static final String DEFAULT_XML = "kontakty.xml";

    public Adresar() throws XMLStreamException, IOException, TransformerException {
        this(new File(DEFAULT_XML));
    }

    public Adresar(final String xmlFile) throws XMLStreamException, IOException, TransformerException {
        this(new File(xmlFile));
    }

    public Adresar(final File xmlFile) throws XMLStreamException, IOException, TransformerException {
        this.xml = xmlFile;      
        this.nacitac = new NacteniKontaktu(this.xml);
        this.zapisovac = new ZapisovacKontaktu(this.xml);
        this.kontakty = this.nactiKontakty();
    }

    public TreeSet<Kontakt> getKontaktyList() {
        return this.kontakty;
    }

    public Kontakt[] getKontaktyArray() {
        Kontakt[] kontakty = new Kontakt[this.kontakty.size()];
        return this.kontakty.toArray(kontakty);
    }

    public TreeSet<Kontakt> nactiKontakty() throws XMLStreamException, IOException {
        this.nacitac.zpracujXML();

        return this.nacitac.getKontakty();
    }

    public boolean addKontakt(Kontakt kontakt) throws XMLStreamException, IOException, TransformerException {
        if(this.kontakty.add(kontakt)) {
            this.ulozKontakty();
            return true;
        }

        return false;
    }

    public boolean removeKontakt(Kontakt kontakt) throws XMLStreamException, IOException, TransformerException {
        if(this.kontakty.remove(kontakt)) {
            this.ulozKontakty();
            return true;
        }

        return false;
    }

    public void ulozKontakty() throws XMLStreamException, IOException, TransformerException {
        this.zapisovac.zapisDoXML(this.kontakty);
    }


}
