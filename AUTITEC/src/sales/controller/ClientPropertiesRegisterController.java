package sales.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Client;
import model.ClientPropertiesMaterial;
import model.ClientProperties;
import model.Material;
import util.ShowMessage;
import database.dao.ClientDAO;
import database.dao.ClientPropertiesDAO;
import database.dao.MaterialDAO;

public class ClientPropertiesRegisterController {

	
	private JFrame frame;

	public ClientPropertiesRegisterController(JFrame frame) {
		this.frame = frame;
	}
	
	public void close() {
		int i = ShowMessage.questionMessage(frame, "Fechar", "Deseja realmente fechar o registro de propriedades de cliente?\nAs informações não salvas serão peridadas");
		if( i == JOptionPane.YES_OPTION) frame.dispose(); 
	}

	public void fillProductCbo(JComboBox<Material> cboMaterial) {
	    List<Material> pList = new MaterialDAO().getAllMaterials();
	    pList.forEach(m -> cboMaterial.addItem(m));
	    cboMaterial.setSelectedIndex(-1);
    }

	public void fillCLientsCBO(JComboBox<Client> cboClient) {
	    List<Client> cList = new ClientDAO().getAllClients();
	    cList.forEach(c -> cboClient.addItem(c));
	    cboClient.setSelectedIndex(-1);
	    		
    }

	public void register(ClientProperties cp) {
	    Date date = cp.getEntryDate();
	    String fiscalNote = cp.getFiscalNote();
	    List<ClientPropertiesMaterial> cpmList = cp.getPropertiesList();
	    
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("entryDate", date);
	    map.put("fiscalNote", fiscalNote);
	    map.put("cpmList", cpmList);
	    
	    new ClientPropertiesDAO().register(map);
	    
    }
}
