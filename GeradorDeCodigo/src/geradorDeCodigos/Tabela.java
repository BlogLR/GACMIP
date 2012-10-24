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
public class Tabela {

    private String nome = "", primario = "";
    private Campo[] campos = new Campo[0];

    public void setPorAttr(Attributes attr) {
        if (attr.getIndex("nome") >= 0) {
            setNome(attr.getValue("nome"));
        }
        if (attr.getIndex("primario") >= 0) {
            setPrimario(attr.getValue("primario"));
        }
    }

    public void setNome(String nome) {
        try {
            this.nome = nome;
        } catch (Exception e) {
            System.out.println("Tabela->setNome: erro = " + e.getMessage());
        }
    }

    public void setPrimario(String primario) {
        try {
            this.primario = primario;
        } catch (Exception e) {
            System.out.println("Tabela->setPrimario: erro = " + e.getMessage());
        }
    }

    public void addCampo(Attributes attr) {
        Campo[] tmp = campos;
        campos = new Campo[tmp.length + 1];
        System.arraycopy(tmp, 0, campos, 0, tmp.length);

        campos[tmp.length].setPorAttr(attr);

    }

    public String getNome() {
        return nome;
    }

    public String getPrimario() {
        return primario;
    }

    public Campo[] getCampos() {
        return campos;
    }
}