/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geradorDeCodigos;

import org.xml.sax.Attributes;

/**
 *
 * @author leandro
 */
public class BancoDados {

    private String nome = "";
    private Tabela[] tbl = new Tabela[0];

    public void setNome(String nome) {
        try {
            this.nome = nome;
        } catch (Exception e) {
            System.out.println("BancoDados->setNome: erro = " + e.getMessage());
        }
    }

    public void addTabela() {
        Tabela[] tmp = tbl;
        tbl = new Tabela[tmp.length + 1];
        System.arraycopy(tmp, 0, tbl, 0, tmp.length);
    }

    public void addTabela(String nome) {
        addTabela();
        tbl[tbl.length - 1].setNome(nome);
    }

    public void addTabela(Attributes attr) {
        addTabela();
        tbl[tbl.length - 1].setPorAttr(attr);
    }

    public Tabela[] getTabela() {
        return tbl;
    }

    public String getNome() {
        return this.nome;
    }
}