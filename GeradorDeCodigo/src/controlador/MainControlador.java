package controlador;

import sql.ConectorMySql;
import sql.sqlGerador;
import xml.XmlParser;

/**
 * Gerador de Código Automático a partir de Modelo Idependente de Plataforma
 *
 * @author Leandro Rolim
 *
 */
public class MainControlador {

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
            System.out.println("Erro durante a atualização do MySQL");
        }
    }
    
    public void salvarSQL(String destino){
        
    }
}