package sql;

import java.util.ArrayList;
import xml.BancoDados;
import xml.Campo;
import xml.Relacao;
import xml.Tabela;

public class sqlGerador {

    ArrayList<BancoDados> bd;
    String sql[] = new String[0];

    public sqlGerador(ArrayList<BancoDados> bd) {
        this.bd = bd;
    }

    /**
     *
     * @return a String com a instrução sql
     */
    public void gerarSql() {
        StringBuffer sbSql;
        ArrayList<Tabela> tbl;
        ArrayList<Campo> campo;
        ArrayList<Relacao> rel;
        if (bd.size() > 0) {
            sbSql = new StringBuffer("");
            for (int i = 0; i < bd.size(); i++) {
                sbSql
                        .append("CREATE DATABASE IF NOT EXISTS `")
                        .append(bd.get(i).getNome())
                        .append("`");
                addSQL(sbSql.toString());
                sbSql = new StringBuffer("");
                tbl = bd.get(i).getTabela();
                for (int j = 0; j < tbl.size(); j++) {
                    sbSql.append("CREATE TABLE IF NOT EXISTS `")
                            .append(bd.get(i).getNome())
                            .append("`.`")
                            .append(tbl.get(j).getNome())
                            .append("` ( `id` INT (11) NULL AUTO_INCREMENT "); //campo id será add em todas as tabelas
                    campo = tbl.get(j).getCampos();
                    for (int k = 0; k < campo.size(); k++) {
                        sbSql.append(", ");
                        sbSql
                                .append("`")
                                .append(campo.get(k).getNome())
                                .append("` ")
                                .append(campo.get(k).getTipo())
                                .append(" ");
                        if (campo.get(k).getTamanho() != null) { // se o tamanha for definido
                            sbSql
                                    .append("(")
                                    .append(campo.get(k).getTamanho())
                                    .append(") ");
                        }

                        if (campo.get(k).getNaoNulo()) {
                            sbSql.append("NOT NULL ");
                        } else {
                            sbSql.append("NULL ");
                        }

                    }
                    sbSql.append(", PRIMARY KEY (`id`)) ENGINE=InnoDB ");
                    addSQL(sbSql.toString());
                    sbSql = new StringBuffer("");
                }
                rel = this.bd.get(i).getRelacao();
                
                for (int j = 0; j < rel.size(); j++) {
                    switch (rel.get(j).getTipo()) {
                        case "umxmuitos":
                            //ALTER TABLE  `Produtos` ADD  `Categorias_id` INT( 11 ) NULL AFTER  `id`
                            String chaveEstra = rel.get(j).getReferencia() + "_id";
                            sbSql
                                    .append("ALTER TABLE `")
                                    .append(bd.get(i).getNome())
                                    .append("`.`")
                                    .append(rel.get(j).getEstrangeiro())
                                    .append("` ADD `")
                                    .append(chaveEstra)
                                    .append("` INT(11) NOT NULL AFTER `id` , ADD INDEX (`")
                                    .append(chaveEstra)
                                    .append("`),  ADD FOREIGN KEY ( `")
                                    .append(chaveEstra)
                                    .append("` ) REFERENCES  `")
                                    .append(bd.get(i).getNome())
                                    .append("`.`")
                                    .append(rel.get(j).getReferencia())
                                    .append("` (`id`) ");
                            this.addSQL(sbSql.toString());
                            break;
                        case "muitosxmuitos":
                            String tblLink = bd.get(i).getNome() + "`.`" + rel.get(j).getReferencia() + "_" + rel.get(j).getEstrangeiro();
                            sbSql
                                    .append("CREATE TABLE IF NOT EXISTS `")
                                    .append(tblLink)
                                    .append("` ( `id` INT (11) NULL AUTO_INCREMENT , `")
                                    .append(rel.get(j).getReferencia())
                                    .append("_id` INT (11) NOT NULL , `")
                                    .append(rel.get(j).getEstrangeiro())
                                    .append("_id` INT (11) NOT NULL ,PRIMARY KEY (`id`) , KEY (`")
                                    .append(rel.get(j).getReferencia())
                                    .append("_id` , `")
                                    .append(rel.get(j).getEstrangeiro())
                                    .append("_id` ) , FOREIGN KEY ( `")
                                    .append(rel.get(j).getReferencia())
                                    .append("_id` ) REFERENCES `")
                                    .append(bd.get(i).getNome())
                                    .append("`.`")
                                    .append(rel.get(j).getReferencia())
                                    .append("` (`id`) , FOREIGN KEY ( `")
                                    .append(rel.get(j).getEstrangeiro())
                                    .append("_id` ) REFERENCES `")
                                    .append(bd.get(i).getNome())
                                    .append("`.`")
                                    .append(rel.get(j).getEstrangeiro())
                                    .append("` (`id`) ) ENGINE=InnoDB");
                            this.addSQL(sbSql.toString());
                            break;
                        case "umxum":
                            
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
