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

    /**
     * 
     * @param attr 
     */
    public Relacao(Attributes attr) {
        this.tblEstrangeira = attr.getValue("tabela_estrangeira");
        this.tblReferencia = attr.getValue("tabela_referecia");
        this.tipo = attr.getValue("tipo");
    }

    /**
     * 
     * @return String
     */
    public String getEstrangeiro() {
        return this.tblEstrangeira;
    }

    /**
     * 
     * @return String
     */
    public String getReferencia() {
        return this.tblReferencia;
    }

    /**
     * 
     * @return String
     */
    public String getTipo() {
        return this.tipo;
    }
}
