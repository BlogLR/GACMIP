package sql;

import xml.BancoDados;
import xml.Campo;
import xml.Tabela;

public class sqlGerador {

    BancoDados bd[];

    public sqlGerador(BancoDados bd[]) {
        this.bd = bd;
    }

    /**
     *
     * @return a String com a instrução sql
     */
    public String gerarSql() {
        StringBuffer sbSql;
        Tabela tbl[];
        Campo campo[];
        if (bd.length > 0) {
            sbSql = new StringBuffer("");
            for (int i = 0; i < bd.length; i++) {
                sbSql
                        .append("CREATE DATABASE IF NOT EXISTS `")
                        .append(bd[i].getNome())
                        .append("` \n");
                tbl = bd[i].getTabela();
                for (int j = 0; j < tbl.length; j++) {
                    sbSql.append("CREATE TABLE IF NOT EXISTS `")
                            .append(bd[i].getNome())
                            .append("`.`")
                            .append(tbl[j].getNome())
                            .append("` { ");
                    campo = tbl[j].getCampos();
                    for (int k = 0; k < campo.length; k++) {
                        if (k > 0) {
                            sbSql.append(", ");
                        }
                        sbSql
                                .append("`")
                                .append(campo[k].getNome())
                                .append("` ")
                                .append(campo[k].getTipo())
                                .append(" ");
                        if (campo[k].getTamanho() > 0) { // se o tamanha for definido
                            sbSql
                                    .append("(")
                                    .append(campo[k].getTamanho())
                                    .append(") ");
                        }

                        if (campo[k].getPrimario()) {
                            sbSql.append("PRIMARY KEY ");
                        }
                        if (campo[k].getNaoNulo()) {
                            sbSql.append("NOT NULL ");
                        } else {
                            sbSql.append("NULL ");
                        }

                    }
                    sbSql.append(" }\n\n");
                }
            }
            return sbSql.toString();
        } else {
            return null;
        }
    }
}