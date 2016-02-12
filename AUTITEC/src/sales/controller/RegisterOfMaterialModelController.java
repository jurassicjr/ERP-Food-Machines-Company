package sales.controller;

import javax.swing.JOptionPane;

import model.MaterialModel;
import sales.view.register.RegisterOfMaterialModelView;
import util.ShowMessage;
import database.dao.RegisterOfMaterialModelDAO;

public class RegisterOfMaterialModelController {

	
	private RegisterOfMaterialModelView frame;

	public RegisterOfMaterialModelController(RegisterOfMaterialModelView frame) {
		this.frame = frame;
	}
	
	public void close() {
		int i = ShowMessage.questionMessage(frame, "Fechar", "Deseja realmente fechar essa janela?");
		if(i==JOptionPane.YES_OPTION)frame.dispose();
	}

	public void register(String name) {
	    MaterialModel materialModel = new MaterialModel(name);
	    new RegisterOfMaterialModelDAO().register(materialModel);
    }
}
