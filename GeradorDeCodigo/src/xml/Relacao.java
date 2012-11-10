/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

/**
 *
 * @author Leandro Rolim
 */
public class Relacao {

    private String tblEstrangeira = null,
            tblReferencia = null,
            tipo = "umxmuitos";

    public Relacao(String estrageiro, String referencia, String tipo) {
        this.tblEstrangeira = estrageiro;
        this.tblReferencia = referencia;
        this.tipo = tipo;
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
