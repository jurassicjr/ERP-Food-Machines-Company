package userInterface.components;

import java.awt.Container;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

public class FileChooser extends JFileChooser {
	
	private static final long serialVersionUID = 7890303360710039220L;
    
    private String pathFile;
    private File lastDir;
    private boolean selectedFile;
    private Container frame;
     
    public FileChooser() {
        setMultiSelectionEnabled(false);
        lastDir = null;
    }
    
    public FileChooser(Container frame) {
        setMultiSelectionEnabled(false);
        lastDir = null;
        this.frame = frame;
    }
     
    public void showSaveDialog() {
         
        setCurrentDirectory(lastDir);
         
        selectedFile = false;
        
        int result = showSaveDialog(frame);
         
        if(result == JFileChooser.APPROVE_OPTION){
             
            selectedFile = true;
            lastDir = getSelectedFile().getAbsoluteFile();
            pathFile = getSelectedFile().getAbsolutePath();
            
            if(new File(pathFile).exists()){
                if(!overwriteFile()) selectedFile = false;
            }
        }
         
    }
     
    public void showOpenDialog(FileFilter fileFilter) {
    	
    	setFileFilter(fileFilter);
         
        setCurrentDirectory(lastDir);
         
        selectedFile = false;
        
        int result = showOpenDialog(frame);
         
        if(result == JFileChooser.APPROVE_OPTION){
            selectedFile = true;                    
            lastDir = getSelectedFile().getAbsoluteFile();
            pathFile = getSelectedFile().getAbsolutePath();
        }
    }    
     
    public boolean overwriteFile() {
        String tokens[] = pathFile.split("\\" + File.separator);
        String fileName = tokens[tokens.length - 1];
        String message = "O Arquivo " + fileName + " já existe, deseja sobrescrevê-lo?";
        String title = "Sobrescrever o arquivo?";
        int result = JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return (result == 0) ? true : false;
    }
         
    public String getSelectedPathFile() {
        return (selectedFile) ? pathFile : null;
    }
     
    public boolean hasSelectedFile() {
        return selectedFile;
    }

}
