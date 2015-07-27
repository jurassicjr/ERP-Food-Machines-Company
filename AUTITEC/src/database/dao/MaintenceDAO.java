package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

import model.Equipment;
import model.Maintenance;
import database.DataBase;

public class MaintenceDAO {

	private DataBase dataBase;

	public MaintenceDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	public boolean persistMaintence(Maintenance main) {

		try {
			Object[] obj = { main.getDateMaintenance(),
					main.getDescriptionMaintenance(),
					main.getEquipmentMaintenance().getId(),
					main.getExpireDate() };

			String sql = "insert  into maintence ("
					+ " datemaintence, description, equip_id, expiratedate) "
					+ "values (?,?,?,?) ";
			dataBase.executeUpdate(sql, obj);
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	public void fillCbo(JComboBox cbo) {
		cbo.removeAllItems();
		try {
			ResultSet rs = dataBase
					.executeQuery("select e.name, e.id from equipment e");
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
	}

	public void fillCboComplete(JComboBox cbo) {
		cbo.removeAllItems();
		try {
			ResultSet rs = dataBase.executeQuery("select * from equipment e");
			while (rs.next()) {
				Equipment equip = new Equipment();
				equip.setName(rs.getString("name"));
				equip.setId(rs.getInt("id"));
				equip.setSerialNumber(rs.getString("serialNumber"));
				equip.setWarrantyDate(rs.getDate("warranty"));
				equip.setDescriptionModel("descriptionmodel");
				cbo.addItem(equip);
			}
			cbo.setSelectedIndex(-1);
		} catch (SQLException e) {
			e.printStackTrace();
			DataBase.showDataBaseErrorMessage();
			return;
		} finally {
			dataBase.close();
		}
	}

	public Maintenance recoveryMaintenanceOfEquip(Equipment equip) {
		// String sql =
		// "select m.id, m.datemaintence,m.expiratedate, m.description from maintence m, equipment e where m.equip_id = e.id"
		// +
		// " and e.id = ? and m.datemaintence = (SELECT MAX(expiratedate) from maintence where maintence.id = m.id)";
		String sql = "select m.id, m.datemaintence,m.expiratedate, m.description from maintence m, equipment e "
				+ " where m.equip_id = e.id and e.id = ? order by m.datemaintence DESC limit 1 ";
		System.out.println(sql);

		try {
			ResultSet rs = dataBase.executeQuery(sql, equip.getId());

			if (rs.next()) {
				Maintenance maintenance = new Maintenance();
				maintenance.setDateMaintenance(rs.getDate("datemaintence"));
				maintenance.setExpireDate(rs.getDate("expiratedate"));
				maintenance.setDescriptionMaintenance(rs
						.getString("description"));
				maintenance.setId(rs.getInt("id"));

				return maintenance;
			} else
				return null;
		} catch (Exception ex) {
			return null;
		} finally {
			dataBase.close();
		}

	}
}
