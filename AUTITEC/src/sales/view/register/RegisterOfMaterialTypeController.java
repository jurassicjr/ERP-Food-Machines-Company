package sales.view.register;

import javax.swing.JOptionPane;

import util.ShowMessage;
import database.dao.RegisterOfMaterialTypeDAO;

public class RegisterOfMaterialTypeController {

	
	private RegisterOfMaterialTypeView frame;

	public RegisterOfMaterialTypeController(RegisterOfMaterialTypeView frame) {
		this.frame = frame;
	}
	
	public void close() {
		int i = ShowMessage.questionMessage(frame, "Fechar", "Deseja realmente fechar essa tela ?");
		if(i == JOptionPane.YES_OPTION)frame.dispose();
	}

	public void register(String name) {
	    RegisterOfMaterialTypeDAO dao = new RegisterOfMaterialTypeDAO();
	    dao.register(name);
    }
	
}
