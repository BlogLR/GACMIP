/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geradorDeCodigos;

/**
 * Gerador de Código Automático a partir de Modelo Idependente de Plataforma
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
        XmlToBancoDados xml = new XmlToBancoDados("src/gcamip/modelo.xml");
        xml.parser();
    }
}
