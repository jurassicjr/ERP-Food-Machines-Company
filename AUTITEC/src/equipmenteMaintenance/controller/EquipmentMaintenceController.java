package equipmenteMaintenance.controller;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Maintenance;
import util.ClearFrame;
import util.ShowMessage;
import database.dao.MaintenceDAO;
import equipmentMaintenance.view.register.EquipmentMaintenanceRegisterFrame;

public class EquipmentMaintenceController {

	private EquipmentMaintenanceRegisterFrame frame;
	private MaintenceDAO dao;

	public EquipmentMaintenceController(EquipmentMaintenanceRegisterFrame frames) {
		this.frame = frames;
	}

	public void fillCbo(JComboBox cbo) {
		dao = new MaintenceDAO();
		dao.fillCbo(cbo);
	}

	public void closeFrame(JFrame frame) {

		String title = "Sair";
		String message = "Deseja realmente sair?\nOs dados não salvos serão perdidos.";

		int response = ShowMessage.questionMessage(frame, title, message);

		if (response == JOptionPane.YES_OPTION) {
			frame.dispose();

		}

	}

	public void clear() {

		String title = "Limpar os Dados Inseridos";
		String message = "Deseja realmente limpar os dados inseridos?";

		int response = ShowMessage.questionMessage(frame, title, message);

		if (response == JOptionPane.YES_OPTION) {
			ClearFrame.clear(frame);
		}
	}

	public boolean persistMaintenance(Maintenance main) {

		dao = new MaintenceDAO();
		if (dao.persistMaintence(main))
			return true;
		return false;
	}

}
