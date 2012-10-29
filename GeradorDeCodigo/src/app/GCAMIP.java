package app;

import sql.sqlGerador;
import xml.XmlParser;

/**
 * Gerador de Código Automático a partir de Modelo Idependente de Plataforma
 *
 * @author Leandro Rolim
 *
 */
public class GCAMIP {

    /**
     * Iniciar a aplicação
     *
     * @param args recebe os argumentos de linha de comando
     */
    public static void main(String[] args) {
        String src;
        sqlGerador sqlG;
        if (args.length > 1) {
            src = args[1];
        } else {
            src = "src/modelo.xml";
        }
        XmlParser xml = new XmlParser(src);
        xml.parser();
        sqlG = new sqlGerador(xml.getBD());
        System.out.println(sqlG.gerarSql());
    }
}