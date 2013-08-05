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
 * @author Leandro Rolim
 */
public class ConectorMySql {

    /**
     *
     * @param url
     * @param usuario
     * @param senha
     */
    public ConectorMySql(String url, String usuario, String senha) {
        this.url = url;
        this.usuario = usuario;
        this.senha = senha;
    }
    private String url,
            usuario,
            senha;

    /**
     *
     * @return Connection
     */
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

    /**
     *
     * @param sql
     * @return ResultSet
     */
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

    /**
     *
     * @param sql
     * @return int
     */
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
