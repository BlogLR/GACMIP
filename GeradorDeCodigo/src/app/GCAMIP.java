package app;

import java.util.Scanner;
import sql.ConectorMySql;
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
     *
     * @param args recebe os argumentos de linha de comando
     */
    public static void main(String[] args) {
        String src,
                url = "jdbc:mysql://localhost:3306",
                usuario = "root",
                senha = ""; //colocar a senha aqui
        sqlGerador sqlG;
        if (args.length > 1) {
            src = args[1];
        } else {
            src = "src/modelo.xml";
        }
        XmlParser xml = new XmlParser(src);
        xml.parser();
        sqlG = new sqlGerador(xml.getBD());
        sqlG.gerarSql();
        
        ConectorMySql cMysql = new ConectorMySql(url, usuario, senha);

        for (int i = 0; i < sqlG.toStringArray().length; i++) {
            System.out.println(sqlG.toStringArray()[i]);
            cMysql.atualizar(sqlG.toStringArray()[i]);
        }
    }
}