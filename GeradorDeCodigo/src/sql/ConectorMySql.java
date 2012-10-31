/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leandro
 */
public class ConectorMySql {

    public ConectorMySql(String url, String usuario, String senha) {
        this.url = url;
        this.usuario = usuario;
        this.senha = senha;
    }
    private String url,
            usuario,
            senha;

    private Connection getConn() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            try {
                conn = (Connection) DriverManager.getConnection(this.url, this.usuario, this.senha);
            } catch (SQLException ex) {
                Logger.getLogger(ConectorMySql.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException |
                InstantiationException |
                IllegalAccessException ex) {
            Logger.getLogger(ConectorMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    public ResultSet executarSql(String sql) {
        Connection conn = this.getConn();
        ResultSet rs = null;
        try {
            Statement stm = conn.createStatement();
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConectorMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public int atualizar(String sql) {
        Connection conn = this.getConn();
        try {
            Statement stm = conn.createStatement();
            return stm.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConectorMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
