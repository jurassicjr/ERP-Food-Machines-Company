package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Product;
import util.ShowMessage;
import database.DataBase;

public class ProductDAO {

	private DataBase dataBase;
	private Object[] insertData;

	public ProductDAO() {
		this.dataBase = new DataBase();
		dataBase.connect();
	}

	public ProductDAO(Map<String, Object> data) {
		this.dataBase = new DataBase();
		dataBase.connect();
		persist(data);
	}

	private void persist(Map<String, Object> data) {
		String descrition = (String) data.get("descricao");
		String name = (String) data.get("name");
		insertData = new Object[] { descrition, name };
		String sql = "INSERT INTO Product(descricao, name) VALUES (?, ?)";
		dataBase.executeUpdate(sql, insertData);
		ShowMessage.successMessage(null, "GRAVAÇÂO", "Gravação concluida com sucesso!");
	}

	public List<Product> getProductAssociationWithKit(int id) {
		List<Product> list = new ArrayList<Product>();
		try (ResultSet rs = dataBase.executeQuery("SELECT *FROM kit_relationship WHERE kit = ?", id)) {
			while (rs.next()) {
				Product p = new Product();
				p.setAuxAmmount(rs.getInt("ammount"));
				try (ResultSet rsName = dataBase.executeQuery("SELECT *From compost_product WHERE id = ?",
				        rs.getInt("product"))) {
					if (rsName.next()) {
						p.setName(rsName.getString("product"));
						// m.setAmmount(rsName.getDouble("ammount"));
						p.setId(rsName.getInt("id"));
						list.add(p);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
