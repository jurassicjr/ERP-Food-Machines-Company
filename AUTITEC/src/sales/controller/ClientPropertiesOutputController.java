package sales.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.ClientProperties;
import model.ClientPropertiesMaterial;
import model.ClientPropertiesOutput;
import util.ShowMessage;
import database.dao.ClientPropertiesDAO;
import database.dao.ClientPropertiesOutputDAO;

public class ClientPropertiesOutputController {

	
	private JFrame frame;

	public ClientPropertiesOutputController(JFrame frame) {
		this.frame = frame;
	}
	
	public void close() {
		int i = ShowMessage.questionMessage(frame, "Fechar", "Deseja realmente fechar?\nAs informações não salvas serão perdidas");
		if(i == JOptionPane.YES_OPTION)frame.dispose();
	}

	public void fillCbo(JComboBox<ClientProperties> cboClientProperties) {
		cboClientProperties.removeAllItems();
		List<ClientProperties> cpList = new ClientPropertiesDAO().getAllActivedClientsProperties();
		cpList.forEach(cp -> cboClientProperties.addItem(cp));
	}

	public void register(ClientPropertiesOutput cpo, boolean propertiesEnd) {
	    String motive = cpo.getMotive();
	    String exitFiscalNote = cpo.getExitFiscalNote();
	    ClientProperties cp = cpo.getCp();
	    Date exitDate = cpo.getExitDate();
	    List<ClientPropertiesMaterial> cpmList = cpo.getCpList();
	    
	    Map<String, Object> map = new HashMap<String, Object>();
	    
	    map.put("motive", motive);
	    map.put("exitFiscalNote", exitFiscalNote);
	    map.put("clientProperties", cp);
	    map.put("cpm", cpmList);
	    map.put("exitDate", exitDate);
	    
	    new ClientPropertiesOutputDAO().register(map, propertiesEnd);
	    
    }

}
