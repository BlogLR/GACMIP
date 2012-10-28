package xml;

import org.xml.sax.Attributes;

/**
 * Esta class representas o campo em um tabela
 *
 * @author Leandro Rolim
 */
public class Campo {
    
    private String tipos[][] = {
        {"inteiro", "INT"},
        {"decimal", "DECIMAL"},
        {"caracteres", "VARCHAR"},
        {"texto", "TEXT"},
        {"logico", "BOOLEAN"},
        {"data", "DATE"}
    };
    private String nome = "",
            tipo = "";
    private boolean primario = false,
            nao_nulo = false,
            auto = false;
    private int tamanho = 0;

    public Campo(Attributes  attr) {
        this.setAttr(attr);
    }
    
    private void setAttr(Attributes attr) {
        try {
            // atributo 'nome'
            if (attr.getIndex("nome") >= 0) {
                this.nome = attr.getValue("nome");
            } else {
                System.out.println("Faltando o atributo nome na tag campo");
            }
            // atributo 'tipo'
            if (attr.getIndex("tipo") >= 0) {
                this.setTipo(attr.getValue("tipo"));
            } else {
                System.out.println("Faltando o atributo tipo na tag campo");
            }
            // atributo 'naonulo'
            if (attr.getIndex("naonulo") >= 0) {
                if (attr.getValue("naonulo").equals("true")) {
                    this.nao_nulo = true;
                }
            }
            // atributo 'primario'
            if (attr.getIndex("primario") >= 0) {
                if (attr.getValue("primario").equals("true")) {
                    this.primario = true;
                }
            }
            // atributo 'auto'
            if (attr.getIndex("auto") >= 0) {
                if (attr.getValue("auto").equals("true")) {
                    this.auto = true;
                }
            }
            // atributo 'tamanho'
            if (attr.getIndex("tamanho") >= 0) {
                tamanho = (int) Double.parseDouble(attr.getValue("tamanho"));
            }
        } catch (NumberFormatException e) {
            System.out.println("setAttr: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("setAttr: " + e.getMessage());
        }
    }

    private void setTipo(String tipo) {
        for (int i = 0; i < this.tipos.length; i++) {
            if (this.tipos[i][0].equals(tipo)) {
                this.tipo = this.tipos[i][1];
                return;
            }
        }
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public int getTamanho() {
        return tamanho;
    }
}
