package sales.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

import model.Material;
import database.DataBase;

public class MaterialUpdateController extends SalesController{
	DataBase dataBase;
	
	public MaterialUpdateController() {
		dataBase = new DataBase();
		dataBase.connect();
    }
	
	/**
	 * Popula o JComboBox<Produto> com produtos do banco.
	 */
	
	public void fillMaterials(JComboBox<Material> cboMaterial) {
		cboMaterial.removeAllItems();
		try (ResultSet rs = dataBase.executeQuery("SELECT *FROM Product order by name")){
			while (rs.next()) {
				Material produto = new Material();
				produto.setName(rs.getString("name"));
				produto.setDescrition(rs.getString("descricao"));
				produto.setId(rs.getInt("id"));
				produto.setAmmount(rs.getInt("quantidade"));
				produto.setHeigth(rs.getDouble("z"));
				produto.setInternalCode(rs.getString("internal_code"));
				produto.setLength(rs.getDouble("y"));
				produto.setMaterialType(rs.getInt("material_type"));
				produto.setMeasureUnit(rs.getInt("measure_unit"));
				produto.setModel(rs.getInt("model"));
				produto.setNCM(rs.getString("ncm"));
				produto.setWidth(rs.getDouble("x"));
				cboMaterial.addItem(produto);
			}
			cboMaterial.setSelectedIndex(-1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
