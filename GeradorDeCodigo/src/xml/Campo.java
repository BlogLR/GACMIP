package xml;

import org.xml.sax.Attributes;

/**
 * Esta class representas o campo em um tabela
 *
 * @author Leandro Rolim
 */
public class Campo {

    private String tipos[][] = {
        {"inteiro", "INT", "11"},
        {"string", "VARCHAR", "255"},
        {"texto", "TEXT", null},
        {"flutuante", "FLOAT", null},
        {"data", "DATE", null},
        {"datahora", "DATETIME", null},
        {"hora", "time", null},
        {"binario", "BLOB", null},
        {"boleano", "TINYINT", "1"}
    };
    private String nome = "",
            tipo = "VARCHAR",
            tamanho = "255";
    private boolean unico = false,
            nao_nulo = false,
            auto = false;

    /**
     *
     * @param attr
     */
    public Campo(Attributes attr) {
        this.setAttr(attr);
    }

    /**
     *
     * @param attr
     */
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
            // atributo 'unico'
            if (attr.getIndex("unico") >= 0) {
                if (attr.getValue("unico").equals("true")) {
                    this.unico = true;
                }
            }
            // atributo 'auto'
            if (attr.getIndex("auto") >= 0) {
                if (attr.getValue("auto").equals("true")) {
                    this.auto = true;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("setAttr: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("setAttr: " + e.getMessage());
        }
    }

    /**
     *
     * @param tipo
     */
    private void setTipo(String tipo) {
        for (int i = 0; i < this.tipos.length; i++) {
            if (this.tipos[i][0].equals(tipo)) {
                this.tipo = this.tipos[i][1];
                this.tamanho = this.tipos[i][2];
                return;
            }
        }
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
     * @return String
     */
    public String getTipo() {
        return this.tipo;
    }

    /**
     *
     * @return String
     */
    public String getTamanho() {
        return this.tamanho;
    }

    /**
     *
     * @return boolean
     */
    public boolean getPrimario() {
        return this.unico;
    }

    /**
     *
     * @return boolean
     */
    public boolean getNaoNulo() {
        return this.nao_nulo;
    }

    /**
     *
     * @return boolean
     */
    public boolean getAuto() {
        return this.auto;
    }
}
