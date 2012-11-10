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
public class Relacao {

    private String tblEstrangeira = null,
            tblReferencia = null,
            tipo = "umxmuitos";

    public Relacao(Attributes attr) {
        this.tblEstrangeira = attr.getValue("tabela_estrangeira");
        this.tblReferencia = attr.getValue("tabela_referecia");
        this.tipo = attr.getValue("tipo");
    }

    public String getEstrangeiro() {
        return this.tblEstrangeira;
    }

    public String getReferencia() {
        return this.tblReferencia;
    }

    public String getTipo() {
        return this.tipo;
    }
}
