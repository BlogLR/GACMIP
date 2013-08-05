/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import java.util.ArrayList;
import org.xml.sax.Attributes;

/**
 *
 * @author Leandro Rolim
 */
public class Tabela {

    private String nome = "";
    private ArrayList<Campo> campos = new ArrayList<>();

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
        this.campos.add(new Campo(attr));
    }

    public String getNome() {
        return this.nome;
    }

    public ArrayList<Campo> getCampos() {
        return this.campos;
    }
}