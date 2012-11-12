package sql;

import xml.BancoDados;
import xml.Campo;
import xml.Relacao;
import xml.Tabela;

public class sqlGerador {

    BancoDados bd[];
    String sql[] = new String[0];

    public sqlGerador(BancoDados bd[]) {
        this.bd = bd;
    }

    /**
     *
     * @return a String com a instrução sql
     */
    public void gerarSql() {
        StringBuffer sbSql;
        Tabela tbl[];
        Campo campo[];
        Relacao rel[];
        if (bd.length > 0) {
            sbSql = new StringBuffer("");
            for (int i = 0; i < bd.length; i++) {
                sbSql
                        .append("CREATE DATABASE IF NOT EXISTS `")
                        .append(bd[i].getNome())
                        .append("`");
                addSQL(sbSql.toString());
                sbSql = new StringBuffer("");
                tbl = bd[i].getTabela();
                for (int j = 0; j < tbl.length; j++) {
                    sbSql.append("CREATE TABLE IF NOT EXISTS `")
                            .append(bd[i].getNome())
                            .append("`.`")
                            .append(tbl[j].getNome())
                            .append("` ( `id` INT (11) NULL AUTO_INCREMENT "); //campo id será add em todas as tabelas
                    campo = tbl[j].getCampos();
                    for (int k = 0; k < campo.length; k++) {
                        sbSql.append(", ");
                        sbSql
                                .append("`")
                                .append(campo[k].getNome())
                                .append("` ")
                                .append(campo[k].getTipo())
                                .append(" ");
                        if (campo[k].getTamanho() != null) { // se o tamanha for definido
                            sbSql
                                    .append("(")
                                    .append(campo[k].getTamanho())
                                    .append(") ");
                        }

                        if (campo[k].getNaoNulo()) {
                            sbSql.append("NOT NULL ");
                        } else {
                            sbSql.append("NULL ");
                        }

                    }
                    sbSql.append(", PRIMARY KEY (`id`)) ENGINE=InnoDB ");
                    addSQL(sbSql.toString());
                    sbSql = new StringBuffer("");
                }
                rel = this.bd[i].getRelacao();
                for (int j = 0; j < rel.length; j++) {
                    switch (rel[j].getTipo()) {
                        case "umxmuitos":
                            //ALTER TABLE  `Produtos` ADD  `Categorias_id` INT( 11 ) NULL AFTER  `id`
                            String chaveEstra = rel[j].getReferencia() + "_id";
                            sbSql
                                    .append("ALTER TABLE `")
                                    .append(bd[i].getNome())
                                    .append("`.`")
                                    .append(rel[j].getEstrangeiro())
                                    .append("` ADD `")
                                    .append(chaveEstra)
                                    .append("` INT(11) NOT NULL AFTER `id` , ADD INDEX (`")
                                    .append(chaveEstra)
                                    .append("`),  ADD FOREIGN KEY ( `")
                                    .append(chaveEstra)
                                    .append("` ) REFERENCES  `")
                                    .append(bd[i].getNome())
                                    .append("`.`")
                                    .append(rel[j].getReferencia())
                                    .append("` (`id`) ");
                            this.addSQL(sbSql.toString());
                            break;
                        case "muitosxmuitos":
                            String tblLink = bd[i].getNome()+"`.`"+rel[j].getReferencia() + "_" + rel[j].getEstrangeiro();
                            sbSql
                                    .append("CREATE TABLE IF NOT EXISTS `")
                                    .append(tblLink)
                                    .append("` ( `id` INT (11) NULL AUTO_INCREMENT , `")
                                    .append(rel[j].getReferencia())
                                    .append("_id` INT (11) NOT NULL , `")
                                    .append(rel[j].getEstrangeiro())
                                    .append("_id` INT (11) NOT NULL ,PRIMARY KEY (`id`) , KEY (`")
                                    .append(rel[j].getReferencia())
                                    .append("_id` , `")
                                    .append(rel[j].getEstrangeiro())
                                    .append("_id` ) , FOREIGN KEY ( `")
                                    .append(rel[j].getReferencia())
                                    .append("_id` ) REFERENCES `")
                                    .append(bd[i].getNome())
                                    .append("`.`")
                                    .append(rel[j].getReferencia())
                                    .append("` (`id`) , FOREIGN KEY ( `")
                                    .append(rel[j].getEstrangeiro())
                                    .append("_id` ) REFERENCES `")
                                    .append(bd[i].getNome())
                                    .append("`.`")
                                    .append(rel[j].getEstrangeiro())
                                    .append("` (`id`) ) ENGINE=InnoDB");
                            this.addSQL(sbSql.toString());
                            break;
                        case "umxum":
                            //falta implementar
                            break;
                    }
                    sbSql = new StringBuffer("");
                }
            }
        }
    }

    /**
     *
     * @return String com instrução SQL
     */
    public String[] toStringArray() {
        return this.sql;
    }

    private void addSQL(String str) {
        String[] tmp = this.sql;
        this.sql = new String[tmp.length + 1];
        System.arraycopy(tmp, 0, this.sql, 0, tmp.length);
        this.sql[tmp.length] = str;
    }
}
