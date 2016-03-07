package sales.controller;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import model.Nfe;
import model.NfeMaterialRelation;
import sales.view.register.RegisterOfCheckList;
import util.ShowMessage;
import database.dao.NfeDAO;

public class RegisterCheckListController {

	private RegisterOfCheckList frame;

	public RegisterCheckListController(RegisterOfCheckList frame) {
		this.frame = frame;
	}
	
	public void close() {
		int i = ShowMessage.questionMessage(frame, "Fechar", "Deseja realmente fechar a janela ?");
		if(i == JOptionPane.YES_OPTION)frame.dispose();
	}

	public List<NfeMaterialRelation> fillPanel(Nfe nfe) {
	    List<NfeMaterialRelation> nfemr = new NfeDAO().getAllNfeRelation(nfe);
		return nfemr;
    }

	public void fillCboFiscalNote(JComboBox<Nfe> cboFiscalNote) {
	    List<Nfe> list = new NfeDAO().getNfeList();
	    list.forEach(s -> cboFiscalNote.addItem(s));
    }
}
