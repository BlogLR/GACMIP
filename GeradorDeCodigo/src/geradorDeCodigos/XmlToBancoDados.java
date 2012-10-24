/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geradorDeCodigos;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.w3c.dom.Document;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author leandro
 */
public class XmlToBancoDados extends DefaultHandler {

    private Document doc;
    private File xmlFile;
    private StringBuffer sbSql, sbXML;
    private String bdNome[] = new String[0];
    private String[][] tabelas = new String[0][0];
    private String[][] primario = new String[0][0];
    private String[][][][] campo = new String[0][0][0][4];
    private BancoDados[] bd = new BancoDados[0];

    public XmlToBancoDados(String src) {
        try {
            xmlFile = new File(src);
        } catch (Exception e) {
            System.out.println("Erro ao abrir o arquivo XML\nErro: " + e.getMessage());
        }

    }

    public void parser() {

        try {
            SAXParserFactory fac = SAXParserFactory.newInstance();
            fac.setValidating(true);
            SAXParser parse = fac.newSAXParser();
            parse.parse(xmlFile, this);
        } catch (ParserConfigurationException | SAXException | IOException e0) {
            System.out.println("erro: " + e0.getMessage());
        }
    }

    @Override
    public void startDocument() {
        sbSql = new StringBuffer("");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attr) {
        switch (qName.toLowerCase().toString()) {
            case "bd":
                addDb(attr);
                break;
            case "table":
                addTable(attr);
                break;
            case "campo":
                addCampo(attr);
                break;
        }

        System.out.println("qName: " + qName);
    }

    @Override
    public void error(SAXParseException e) {
        System.out.println("erro SAXParseException: " + e.getMessage());
    }

    @Override
    public void fatalError(SAXParseException e) {
        System.out.println("FatalErro SAXParseException: " + e.getMessage());
    }

    @Override
    public void warning(SAXParseException e) {
        System.out.println("warning SAXParseException: " + e.getMessage());
    }

    private void addDb(Attributes attr) {
        BancoDados tmp[] = this.bd;
        bd = new BancoDados[tmp.length + 1];
        System.arraycopy(tmp, 0, bd, 0, tmp.length);
        bd[tmp.length].addTabela(attr);
        sbSql
                .append("CREATE DATABASE ")
                .append(bd[tmp.length].getNome())
                .append("\n");
    }

    private void addTable(Attributes attr) {
        int n = bd.length - 1;
        addTable(attr, n);
    }

    private void addTable(Attributes attr, int nBD) {
        bd[nBD].addTabela(attr);
    }

    private void addCampo(Attributes attr, int nBD, int nTabela) {
        bd[nBD].getTabela()[nTabela].addCampo(attr);
    }

    private void addCampo(Attributes attr) {
        int nBD = bd.length - 1;
        int nTabela = bd[nBD].getTabela().length - 1;
        addCampo(attr, nBD, nTabela);
    }
}
