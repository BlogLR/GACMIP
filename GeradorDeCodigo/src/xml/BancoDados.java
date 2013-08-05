package xml;

import java.util.ArrayList;
import org.xml.sax.Attributes;

/**
 *
 * @author Leandro Rolim
 */
public class BancoDados {

    private String nome = "";
    private ArrayList<Tabela> tbl = new ArrayList<>();
    private ArrayList<Relacao> rel = new ArrayList<>();

    public BancoDados(Attributes attr) {
        this.setAttr(attr);
    }

    private void setAttr(Attributes attr) {
        // atributo 'nome'
        if (attr.getIndex("nome") >= 0) {
            this.nome = attr.getValue("nome");
        } else {
            System.out.println("BD: Faltando o atributo 'nome'");
        }

    }

    public void addTabela(Attributes attr) {
        this.tbl.add(new Tabela(attr));
        /*
        Tabela[] tmp = this.tbl;
        this.tbl = new Tabela[tmp.length + 1];

        System.arraycopy(tmp, 0, this.tbl, 0, tmp.length);

        this.tbl[tmp.length] = new Tabela(attr);
        */
    }

    public void addRelacao(Attributes attr) {
        this.rel.add(new Relacao(attr));
        /*
        Relacao[] tmp = this.rel;
        this.rel = new Relacao[tmp.length + 1];

        System.arraycopy(tmp, 0, this.rel, 0, tmp.length);

        this.rel[tmp.length] = new Relacao(attr);
        */
    }

    public ArrayList<Relacao> getRelacao() {
        return this.rel;
    }

    public ArrayList<Tabela> getTabela() {
        return this.tbl;
    }

    public String getNome() {
        return this.nome;
    }
}