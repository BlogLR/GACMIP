package geradorDeCodigos;

/**
 * Gerador de Código Automático a partir de Modelo Idependente de Plataforma
 *
 * @author Leandro Rolim
 *
 */
public class GCAMIP {

    /**
     * Iniciar a aplicação
     *
     * @param args recebe os argumentos de linha de comando
     */
    public static void main(String[] args) {
        String src;
        if (args.length > 1) {
            src = args[1];
        } else {
            src = "\"src/gcamip/modelo.xml\"";
        }
        XmlToBancoDados xml = new XmlToBancoDados(src);
        xml.parser();
        System.out.println(xml.toSqlString());
    }
}
