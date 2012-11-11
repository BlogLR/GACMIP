package xml;

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
            case "tabela":
                addTable(attr);
                break;
            case "campo":
                addCampo(attr);
                break;
            case "relacao":
                addRelacao(attr);
                break;
        }
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
        try {
            BancoDados tmp[] = this.bd;
            bd = new BancoDados[tmp.length + 1];
            System.arraycopy(tmp, 0, bd, 0, tmp.length);
            bd[tmp.length] = new BancoDados(attr);
        } catch (Exception e) {
            System.out.println("Erro ler tag \'db\': "+e.getMessage());
        }
    }

    private void addTable(Attributes attr) {
        try {
            int nBD = bd.length - 1;
            bd[nBD].addTabela(attr);
        } catch (Exception e) {
            System.out.println("Erro ler tag \'table\': "+e.getMessage());
        }
    }
    private void addRelacao(Attributes attr) {
        try {
            int nBD = bd.length - 1;
            bd[nBD].addRelacao(attr);
        } catch (Exception e) {
            System.out.println("Erro ler tag \'relacao\': "+e.getMessage());
        }
    }

    private void addCampo(Attributes attr) {
        try {
            int n = bd.length - 1;
            Tabela tbl[] = bd[n].getTabela();
            n = tbl.length - 1;
            tbl[n].addCampo(attr);
        } catch (Exception e) {
            System.out.println("Erro ler tag \'campo\': "+e.getMessage());
        }
    }

    public BancoDados[] getBD() {
        return this.bd;
    }
}
