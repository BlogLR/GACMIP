package geradorDeCodigos;

import org.xml.sax.Attributes;

/**
 * Esta class representas o campo em um tabela
 * @author Leandro Rolim
 */
public class Campo {

    private String nome = "",
            func = "",
            tamanho = "",
            tipo = "";

    public void setPorAttr(Attributes attr) {
        if (attr.getIndex("nome") >= 0) {
            nome = attr.getValue("nome");
        }
        if (attr.getIndex("func") >= 0) {
            func = attr.getValue("func");
        }
        if (attr.getIndex("tamanho") >= 0) {
            tamanho = attr.getValue("tamanho");
        }
        if (attr.getIndex("tipo") >= 0) {
            tipo = attr.getValue("tipo");
        }
    }

    public void setNome(String nome) {
        try {
            this.nome = nome;
        } catch (Exception e) {
            System.out.println("setNome erro: " + e.getMessage());
        }
    }

    public void setTipo(String tipo) {
        try {
            this.tipo = tipo;
        } catch (Exception e) {
            System.out.println("setTipo erro: " + e.getMessage());
        }
    }

    public void setTamanho(String tamanho) {
        try {
            this.tamanho = tamanho;
        } catch (Exception e) {
            System.out.println("setTamanho erro: " + e.getMessage());
        }
    }

    public void setFunc(String func) {
        try {
            this.nome = func;
        } catch (Exception e) {
            System.out.println("setFunc erro: " + e.getMessage());
        }
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTamanho() {
        return tamanho;
    }

    public String getFunc() {
        return func;
    }
}
