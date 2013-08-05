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

    /**
     *
     * @param attr
     */
    public Tabela(Attributes attr) {
        this.setAttr(attr);
    }

    /**
     *
     * @param attr
     */
    private void setAttr(Attributes attr) {
        if (attr.getIndex("nome") >= 0) {
            this.nome = attr.getValue("nome");
        } else {
            System.out.println("tabela: falta o atributo 'nome'");
        }
    }

    /**
     *
     * @param attr
     */
    public void addCampo(Attributes attr) {
        this.campos.add(new Campo(attr));
    }

    /**
     *
     * @return String
     */
    public String getNome() {
        return this.nome;
    }

    /**
     *
     * @return ArrayList<Campo>
     */
    public ArrayList<Campo> getCampos() {
        return this.campos;
    }
}