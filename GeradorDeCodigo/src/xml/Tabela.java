/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import org.xml.sax.Attributes;

/**
 *
 * @author Leandro Rolim
 */
public class Tabela {

    private String nome = "";
    private Campo[] campos = new Campo[0];

    public Tabela(Attributes attr) {
        this.setAttr(attr);
    }

    private void setAttr(Attributes attr) {
        if (attr.getIndex("nome") >= 0) {
            this.nome = attr.getValue("nome");
        } else {
            System.out.println("tabela: falta o atributo 'nome'");
        }
    }

    public void addCampo(Attributes attr) {
        Campo[] tmp = this.campos;
        this.campos = new Campo[tmp.length + 1];
        System.arraycopy(tmp, 0, this.campos, 0, tmp.length);
        this.campos[tmp.length] = new Campo(attr);
    }

    public String getNome() {
        return this.nome;
    }

    public Campo[] getCampos() {
        return this.campos;
    }
}