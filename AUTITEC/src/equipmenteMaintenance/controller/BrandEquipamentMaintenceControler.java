package equipmenteMaintenance.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import util.ShowMessage;
import model.Brand;
import database.DataBase;
import database.dao.BrandDAO;
import equipmentMaintenance.view.register.BrandEquipmenteRegisterFrame;

public class BrandEquipamentMaintenceControler {
	private BrandDAO dao;

	private BrandEquipmenteRegisterFrame frame;

	public BrandEquipamentMaintenceControler(
			BrandEquipmenteRegisterFrame control) {
		frame = control;
	}

	public boolean persistBrand(Brand brand) {
		dao = new BrandDAO();
		if (dao.persisteBrand(brand))
			return true;
		return false;
	}

	public void closeFrame(JFrame frame) {

		String title = "Sair";
		String message = "Deseja realmente sair?\nOs dados não salvos serão perdidos.";

		int response = ShowMessage.questionMessage(frame, title, message);

		if (response == JOptionPane.YES_OPTION) {
			frame.dispose();

		}

	}

	public void fillCboBrand(JComboBox<Brand> cbo) {
		DataBase dataBase = new DataBase();
		dataBase.connect();
		
		try {
			cbo.removeAllItems();
			
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
		}finally
		{
			dataBase.close();
		}
	}

}
