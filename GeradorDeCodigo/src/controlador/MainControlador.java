package controlador;

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import sql.ConectorMySql;
import sql.sqlGerador;
import xml.XmlParser;

/**
 * Gerador de Código Automático a partir de Modelo Idependente de Plataforma
 *
 * @author Leandro Rolim
 *
 */
public class MainControlador extends Observable implements Observer {

    private String url, src;

    public void setSrc(String src) {
        this.src = src;
    }

    public String[] getSQL(String src) {
        sqlGerador sqlG;
        XmlParser xml = new XmlParser(src);
        xml.parser();
        sqlG = new sqlGerador(xml.getBD());
        sqlG.gerarSql();
        return sqlG.toStringArray();
    }

    public void gerarBD(String local, String porta, String usuario, String senha) {
        System.out.println(senha);
        this.url = "jdbc:mysql://" + local + ":" + porta;
        String[] strSql = this.getSQL(this.src);
        try {
            ConectorMySql cMysql = new ConectorMySql(this.url, usuario, senha);

            for (int i = 0; i < strSql.length; i++) {
                System.out.println(strSql[i]);
                cMysql.atualizar(strSql[i]);
            }
        } catch (Exception e) {
            System.err.println("Erro durante a atualização do MySQL");
        }
    }

    public void salvarSQL(String destino) {
        String str[];
        str = this.getSQL(this.src);
        Formatter saida = null;
        try {
            saida = new Formatter(destino);
            saida.flush();
            for (int i = 0; i < str.length; i++) {
                saida.format("%s ;\n\n", str[i]);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        saida.close();
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}