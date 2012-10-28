package xml;

import org.xml.sax.Attributes;

/**
 * 
 * @author Leandro Rolim
 */
public class BancoDados {

    private String nome = "";
    private Tabela[] tbl = new Tabela[0];

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
        Tabela[] tmp = this.tbl;
        this.tbl = new Tabela[tmp.length + 1];
        System.arraycopy(tmp, 0, this.tbl, 0, tmp.length);
        this.tbl[tmp.length] = new Tabela(attr);
    }

    public Tabela[] getTabela() {
        return this.tbl;
    }

    public String getNome() {
        return this.nome;
    }
}