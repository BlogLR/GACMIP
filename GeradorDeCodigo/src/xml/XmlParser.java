package xml;

import xml.Tabela;
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
 * @author Leandro Rolim
 * @param src caminho do arquivo XML Esta classe recebe o endereço do xml com o
 * modelo do banco de dados e tem funções para fornecer as intruções SQL
 * (formato MySQL)
 */
public class XmlParser extends DefaultHandler {

    private Document doc;
    private File xmlFile;
    private BancoDados[] bd = new BancoDados[0];

    public XmlParser(String src) {
        try {
            xmlFile = new File(src);
        } catch (Exception e) {
            System.out.println("Erro ao abrir o arquivo XML\nErro: " + e.getMessage());
        }
    }

    /**
     * função que executa o parser no XML
     *
     * @return void
     */
    public void parser() {

        try {
            SAXParserFactory fac = SAXParserFactory.newInstance();
            fac.setValidating(true);
            SAXParser parse = fac.newSAXParser();
            parse.parse(xmlFile, this);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("erro: " + e.getMessage());
        }
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

    public BancoDados[] getBD(){
        return this.bd;
    }
}