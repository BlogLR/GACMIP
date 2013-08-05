package xml;

import java.util.ArrayList;
import org.xml.sax.Attributes;

/**
 *
 * @author Leandro Rolim Representa um Database
 */
public class BancoDados {

    private String nome = "";
    private ArrayList<Tabela> tbl = new ArrayList<>();
    private ArrayList<Relacao> rel = new ArrayList<>();

    /**
     *
     * @param attr
     */
    public BancoDados(Attributes attr) {
        this.setAttr(attr);
    }

    /**
     *
     * @param attr
     */
    private void setAttr(Attributes attr) {
        // atributo 'nome'
        if (attr.getIndex("nome") >= 0) {
            this.nome = attr.getValue("nome");
        } else {
            System.out.println("BD: Faltando o atributo 'nome'");
        }

    }

    /**
     *
     * @param attr
     */
    public void addTabela(Attributes attr) {
        this.tbl.add(new Tabela(attr));
    }

    /**
     *
     * @param attr
     */
    public void addRelacao(Attributes attr) {
        this.rel.add(new Relacao(attr));
    }

    /**
     *
     * @return ArrayList<Relacao>
     */
    public ArrayList<Relacao> getRelacao() {
        return this.rel;
    }

    /**
     *
     * @return ArrayList<Tabela>
     */
    public ArrayList<Tabela> getTabela() {
        return this.tbl;
    }

    /**
     *
     * @return String
     */
    public String getNome() {
        return this.nome;
    }
}