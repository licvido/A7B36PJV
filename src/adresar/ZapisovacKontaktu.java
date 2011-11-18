/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adresar;

import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.IOException;
import java.util.TreeSet;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author Filip
 */
public class ZapisovacKontaktu {
    private final File xml;
    private final XMLOutputFactory factory;

    public static final String KODOVANI = "UTF-8";

    public ZapisovacKontaktu(final String xmlFile) {
        this(new File(xmlFile));
    }

    public ZapisovacKontaktu(final File xmlFile) {
        this.xml = xmlFile;
        this.factory = XMLOutputFactory.newInstance();
    }

    private void zapisDoSouboru(final byte[] byteXMLStream) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer zapisovac = factory.newTransformer();
        zapisovac.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        zapisovac.setOutputProperty(OutputKeys.ENCODING, KODOVANI);
        zapisovac.setOutputProperty(OutputKeys.STANDALONE, "yes");
        zapisovac.setOutputProperty(OutputKeys.INDENT, "yes");
        zapisovac.setOutputProperty(OutputKeys.METHOD, "xml");
        zapisovac.setOutputProperty(OutputKeys.MEDIA_TYPE, "text/xml");
        zapisovac.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        zapisovac.transform(
                new StreamSource(new ByteArrayInputStream(byteXMLStream)),
                new StreamResult(this.xml));
    }


    public void zapisDoXML(TreeSet<Kontakt> kontakty) throws XMLStreamException,
                                                             IOException,
                                                             TransformerException {
        CharArrayWriter chaw = new CharArrayWriter();
        XMLStreamWriter out = this.factory.createXMLStreamWriter(chaw);
        out.writeStartDocument(KODOVANI, "1.0");
        out.writeStartElement("adresar");

        for(Kontakt kontakt : kontakty) {
            // Jmeno kontaktu
            out.writeStartElement("jmeno");
            out.writeCharacters(kontakt.getJmeno());
            out.writeEndElement();

            // Jmeno kontaktu
            out.writeStartElement("prijmeni");
            out.writeCharacters(kontakt.getPrijmeni());
            out.writeEndElement();

            // Jmeno kontaktu
            out.writeStartElement("adresa");
            out.writeCharacters(kontakt.getAdresa());
            out.writeEndElement();

            // Jmeno kontaktu
            out.writeStartElement("telefon");
            out.writeCharacters(kontakt.getTelefon());
            out.writeEndElement();

            // Jmeno kontaktu
            out.writeStartElement("mobil");
            out.writeCharacters(kontakt.getMobil());
            out.writeEndElement();

            // Jmeno kontaktu
            out.writeStartElement("email");
            out.writeCharacters(kontakt.getEmail());
            out.writeEndElement();
        }

        out.writeEndElement();
        out.writeEndDocument();
        out.close();

        // Ziskani proudu bajtů
        byte[] byteXMLStream = chaw.toString().getBytes();
        this.zapisDoSouboru(byteXMLStream);
    }
}