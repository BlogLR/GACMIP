/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.UIManager;

/**
 *
 * @author Leandro Rolim
 */
public class FileChooserPT {

    /**
     * Traduz os textos da janela de seleção de arquivos
     */
    static public void traduzir() {
        UIManager.put("FileChooser.directoryOpenButtonText", "Abrir");
        UIManager.put("FileChooser.directoryOpenButtonToolTipText", "Abrir diretório selecionado");

        UIManager.put("FileChooser.openButtonToolTipText", "Selecionar");
        UIManager.put("FileChooser.openButtonAccessibleName", "Selecionar");
        UIManager.put("FileChooser.openButtonText", "Selecionar");

        UIManager.put("FileChooser.cancelButtonToolTipText", "Cancelar");
        UIManager.put("FileChooser.cancelButtonAccessibleName", "Cancelar");
        UIManager.put("FileChooser.cancelButtonText", "Cancelar");

        UIManager.put("FileChooser.saveButtonText", "Salvar");
        UIManager.put("FileChooser.saveButtonToolTipText", "S");
        UIManager.put("FileChooser.saveButtonToolTipText", "Salvar");

        UIManager.put("FileChooser.updateButtonText", "Alterar");
        UIManager.put("FileChooser.updateButtonToolTipText", "A");
        UIManager.put("FileChooser.updateButtonToolTipText", "Alterar");

        UIManager.put("FileChooser.helpButtonText", "Ajuda");
        UIManager.put("FileChooser.helpButtonToolTipText", "A");
        UIManager.put("FileChooser.helpButtonToolTipText", "Ajuda");

        UIManager.put("FileChooser.lookInLabelMnemonic", new Integer('E'));
        UIManager.put("FileChooser.lookInLabelText", "Examinar");
        UIManager.put("FileChooser.saveInLabelMnemonic", new Integer('S'));
        UIManager.put("FileChooser.saveInLabelText", "Salvar em");

        UIManager.put("FileChooser.fileNameLabelMnemonic", new Integer('N')); // N  
        UIManager.put("FileChooser.fileNameLabelText", "Nome do Arquivo");

        UIManager.put("FileChooser.filesOfTypeLabelMnemonic", new Integer('T')); // T  
        UIManager.put("FileChooser.filesOfTypeLabelText", "Tipo");

        UIManager.put("FileChooser.upFolderToolTipText", "Um nível acima");
        UIManager.put("FileChooser.upFolderAccessibleName", "Um nível acima");

        UIManager.put("FileChooser.homeFolderToolTipText", "Desktop");
        UIManager.put("FileChooser.homeFolderAccessibleName", "Desktop");

        UIManager.put("FileChooser.newFolderToolTipText", "Criar nova pasta");
        UIManager
                .put("FileChooser.newFolderAccessibleName", "Criar nova pasta");

        UIManager.put("FileChooser.listViewButtonToolTipText", "Lista");
        UIManager.put("FileChooser.listViewButtonAccessibleName", "Lista");

        UIManager.put("FileChooser.detailsViewButtonToolTipText", "Detalhes");
        UIManager.put("FileChooser.detailsViewButtonAccessibleName", "Detalhes");

        UIManager.put("FileChooser.fileNameHeaderText", "Nome");
        UIManager.put("FileChooser.fileSizeHeaderText", "Tamanho");
        UIManager.put("FileChooser.fileTypeHeaderText", "Tipo");
        UIManager.put("FileChooser.fileDateHeaderText", "Data");
        UIManager.put("FileChooser.fileAttrHeaderText", "Atributos");

        UIManager.put("FileChooser.acceptAllFileFilterText", "Todos os Arquivos");
    }
}
