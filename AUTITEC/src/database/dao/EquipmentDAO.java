package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

import com.mysql.fabric.xmlrpc.base.Data;

import database.DataBase;
import model.Brand;
import model.Equipment;

public class EquipmentDAO {

	private DataBase dataBase;
	private int maxPK=-1;

	public EquipmentDAO() {
		dataBase = new DataBase();
		dataBase.connect();

	}

	public boolean persistEquipment(Equipment newEquipment) {
		try {
			Object[] obj = { newEquipment.getDescriptionModel(),
					newEquipment.getEquipamentBrand().getId(), newEquipment.getName(),
					newEquipment.getSerialNumber(),
					newEquipment.getInvoice(),
					newEquipment.getWarrantyDate()
					};
			String sql = "insert into equipment ("
					+ "descriptionmodel,brand,name,serialnumber,invoice,warranty)"
					+ "values (?,?,?,?,?,?)";
			dataBase.executeUpdate(sql, obj);
			return true;
		}
		catch (Exception e) {
			return false;
		}
		finally
        {
           dataBase.close();
        }
		
	}
	public void fillCbo(JComboBox cbo)
	{
		cbo.removeAllItems();
		try {
			ResultSet rs = dataBase.executeQuery("SELECT * equipment");
			while (rs.next()) {
				Equipment equip = new Equipment();
				equip.setName(rs.getString("name"));
				equip.setId(rs.getInt("id"));
				cbo.addItem(equip);
			}
			cbo.setSelectedIndex(-1);
		} catch (SQLException e) {
			e.printStackTrace();
			DataBase.showDataBaseErrorMessage();
			return;
		}
		finally
        {
           dataBase.close();
        }
	}
	

}
