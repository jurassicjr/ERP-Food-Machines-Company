package equipmenteMaintenance.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import util.ClearFrame;
import util.ShowMessage;
import database.DataBase;
import database.dao.EquipmentDAO;
import database.dao.MaintenceDAO;
import equipmentMaintenance.view.register.EquipmentMaintenanceRegisterFrame;
import equipmentMaintenance.view.register.EquipmentRegisterFrame;
import model.Brand;
import model.Equipment;
import model.Maintenance;

public class EquipmentRegisterController {

	private EquipmentRegisterFrame frame;

	private DataBase dataBase;
	
	public EquipmentRegisterController(
			EquipmentRegisterFrame frames) {
		dataBase = new DataBase();
		dataBase.connect();
		this.frame = frames;
	}

	public void fillCboBrand(JComboBox<Brand> cbo) {

		cbo.removeAllItems();

		try {
			ResultSet rs = dataBase.executeQuery("SELECT * FROM BRAND");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Brand brand = new Brand(id, name);
				cbo.addItem(brand);
			}
			cbo.setSelectedIndex(-1);
		} catch (SQLException e) {
			e.printStackTrace();
			DataBase.showDataBaseErrorMessage();
			return;
		}
	}

	public void closeFrame(JFrame frame) {

		String title = "Sair";
		String message = "Deseja realmente sair?\nOs dados não salvos serão perdidos.";

		int response = ShowMessage.questionMessage(frame, title, message);

		if (response == JOptionPane.YES_OPTION) {
			frame.dispose();
			dataBase.close();
		}

	}

	public boolean persistEquipament(Equipment equip) {
		EquipmentDAO dao = new EquipmentDAO();
		if (dao.persistEquipment(equip))
			return true;
		return false;

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

		MaintenceDAO daoMain = new MaintenceDAO();
		if (daoMain.persistMaintence(main))
			return true;
		return false;
	}

	public int returnLastPK(String table) {
		return dataBase.getAutoIncrementValue(table);
	}

}
