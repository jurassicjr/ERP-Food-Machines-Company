package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.CNPJ;
import model.Client;
import model.Kit;
import model.Material;
import model.PTC;
import model.Product;
import model.Service;
import database.DataBase;

public class PTCDAO {

	private DataBase dataBase;

	public PTCDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	public void register(Map<String, Object> map) {
		double aliquot = (double) map.get("aliquot");
		double aliquotPlusBrute = (double) map.get("aliquotPlusBrute");
		double bruteValue = (double) map.get("brutePrice");
		double suggestedPrice = (double) map.get("suggestedPrice");
		double discount = (double) map.get("discount");
		double contribuition = (double) map.get("contribuition");
		double finalPrice = (double) map.get("finalPrice");
		String title = (String) map.get("title");
		Client client = (Client) map.get("client");
		List<Material> materialList = (List<Material>) map.get("materialList");
		List<Product> productList = (List<Product>) map.get("productList");
		List<Service> serviceList = (List<Service>) map.get("serviceList");
		List<Kit> kitList = (List<Kit>) map.get("kitList");
		int clientID = client.getId();
		CNPJ cnpj = (CNPJ) map.get("cnpj");
		int cnpjID = cnpj.getId();
		String rastreabilityCode = (String) map.get("rastreabilityCode");

		int ptcID = dataBase.getAutoIncrementValue("ptc");
		String query = "INSERT INTO ptc(aliquot, brute_plus_aliquot, brute_value, suggested,"
		        + " discount, contribuition, final_value, title, client, cnpj, rastreability_code) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

		Object[] data = new Object[] { aliquot, aliquotPlusBrute, bruteValue, suggestedPrice, discount, contribuition,
		        finalPrice, title, clientID, cnpjID, rastreabilityCode };
		dataBase.executeUpdate(query, data);
		if (materialList.size() > 0) {
			registerPTCAssociationWithMaterial(materialList, ptcID);
		}
		if (productList.size() > 0) {
			registerPTCAssociationWithProduct(productList, ptcID);
		}
		if (kitList.size() > 0) {
			registerPTCAssociationWithKit(kitList, ptcID);
		}
		if (serviceList.size() > 0) {
			registerPTCAssociationWithService(serviceList, ptcID);
		}
	}

	private void registerPTCAssociationWithService(List<Service> serviceList, int ptcID) {
		String query = "INSERT INTO ptc_service(ptc, service) VALUES(?,?)";
		for (Service s : serviceList) {
			int serviceID = s.getId();
			Object[] data = new Object[] { ptcID, serviceID };
			dataBase.executeUpdate(query, data);
		}
	}

	private void registerPTCAssociationWithKit(List<Kit> kitList, int ptcID) {
		String query = "INSERT INTO ptc_kit(ptc, kit, ammount, price) VALUES (?,?,?,?)";
		for (Kit k : kitList) {
			int kitID = k.getId();
			double price = k.getAuxPrice();
			double ammount = k.getAuxAmmount();
			Object[] data = new Object[] { ptcID, kitID, ammount, price };
			dataBase.executeUpdate(query, data);
		}
	}

	private void registerPTCAssociationWithProduct(List<Product> productList, int ptcID) {
		String query = "INSERT INTO ptc_product(ptc, product, ammount, price) VALUES (?,?,?,?)";
		for (Product p : productList) {
			int productID = p.getId();
			int ammount = p.getAuxAmmount();
			double price = p.getAuxPrice();
			Object[] data = new Object[] { ptcID, productID, ammount, price };
			dataBase.executeUpdate(query, data);
		}
	}

	private void registerPTCAssociationWithMaterial(List<Material> materialList, int ptcID) {
		String query = "INSERT INTO ptc_material(ptc, material, ammount, price) VALUES (?,?,?,?)";
		for (Material m : materialList) {
			int materialID = m.getId();
			int ammount = m.getAuxAmmount();
			double price = m.getAuxPrice();
			Object[] data = new Object[] { ptcID, materialID, ammount, price };
			dataBase.executeUpdate(query, data);
		}
	}

	public List<PTC> getAllPTC() {
		List<PTC> list = new ArrayList<PTC>();
		String ptcQuery = "SELECT *FROM PTC";
		try (ResultSet rs = dataBase.executeQuery(ptcQuery)) {
			while(rs.next()){
				int id = rs.getInt("id");
				String title = rs.getString("title");
				Client client = new ClientDAO().getClientByID(rs.getInt("id"));
				double bruteValue = rs.getDouble("brute_value");
				double brutePlusAliquot = rs.getDouble("brute_plus_aliquot");
				double aliquot = rs.getDouble("aliquot");
				double contribuition = rs.getDouble("contribuition");
				double suggestedPrice = rs.getDouble("suggested");
				double discount = rs.getDouble("discount");
				double finalValue = rs.getDouble("final_value");
				CNPJ cnpj = getCnpjByID(rs.getInt("cnpj"));
				String rastreabilityCode = rs.getString("rastreability_code");
				List<Material> materialList = getMaterialRelation(id);
				List<Product> productList = getProductRelation(id);
				List<Kit> kitList = getKitRelation(id);
				List<Service> serviceList = getServiceRelation(id);
				
				PTC p = new PTC(title, suggestedPrice, aliquot, bruteValue, brutePlusAliquot, discount, finalValue, client, contribuition);
				p.setCnpj(cnpj);
				p.setId(id);
				p.setKitList(kitList);
				p.setMaterialList(materialList);
				p.setProductList(productList);
				p.setRastreabilityCode(rastreabilityCode);
				p.setServiceList(serviceList);
				p.setSuggestedPrice(suggestedPrice);
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private List<Service> getServiceRelation(int id) {
		List<Service> list = new ArrayList<Service>();
 	    String query = "SELECT * FROM ptc_service WHERE id = ?";
	    try(ResultSet rs = dataBase.executeQuery(query, id)){
	    	while(rs.next()) {
	    		ServiceDAO sDAO = new ServiceDAO();
	    		int serviceID = rs.getInt("service");
	    		Service service = sDAO.getServiceByID(serviceID);
	    		list.add(service);
	    	}
	    	
	    } catch (SQLException e) {
	        e.printStackTrace();
        }
		return list;
    }

	private List<Kit> getKitRelation(int id) {
	    List<Kit> list = new ArrayList<Kit>();
	    String query = "SELECT * FROM ptc_kit WHERE ptc = ?";
	    try(ResultSet rs = dataBase.executeQuery(query,id)){
	    	while(rs.next()) {
	    		KitDAO kitDAO = new KitDAO();
	    		int kitID = rs.getInt("kit");
	    		int auxAmmount = rs.getInt("ammount");
	    		double auxPrice = rs.getDouble("price");
	    		Kit kit = kitDAO.getKitByID(kitID);
	    		kit.setAuxAmmount(auxAmmount);
	    		kit.setAuxPrice(auxPrice);
	    		list.add(kit);
	    	}
	    } catch (SQLException e) {
	        e.printStackTrace();
        }
		return list;
    }

	private List<Product> getProductRelation(int id) {
	    List<Product> list = new ArrayList<Product>();
	    String query = "SELECT * FROM ptc_product WHERE ptc = ?";
	    try(ResultSet rs = dataBase.executeQuery(query, id)){
	    	while(rs.next()) {
	    		ProductDAO pDAO = new ProductDAO();
	    		int productID = rs.getInt("product");
		    	double auxPrice = rs.getDouble("price");
		    	int auxAmmount = rs.getInt("ammount");
		    	Product p = pDAO.getProductByID(productID);
		    	p.setAuxAmmount(auxAmmount);
		    	p.setAuxPrice(auxPrice);
		    	list.add(p);
	    	}
	    } catch (SQLException e) {
	        e.printStackTrace();
        }
	    return list;
    }

	private List<Material> getMaterialRelation(int id) {
		List<Material> list = new ArrayList<Material>();
		String query = "SELECT * FROM ptc_material WHERE ptc = ?";
		try (ResultSet rs = dataBase.executeQuery(query, id)){
			while(rs.next()) {
				MaterialDAO mDAO = new MaterialDAO();
				int	materialID = rs.getInt("material");
				double auxPrice = rs.getDouble("price");
				int auxAmmount = rs.getInt("ammount");
				Material m = mDAO.getMaterialById(materialID);
				m.setAuxAmmount(auxAmmount);
				m.setAuxPrice(auxPrice);
				list.add(m);
			}
		} catch (SQLException e) {
	        e.printStackTrace();
        }
		return list;
	}

	private CNPJ getCnpjByID(int id) {
		try {

			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM cnpj WHERE id = ?", id);

			if (resultSet.next()) {

				String cnpj = resultSet.getString("cnpj");
				String corporateName = resultSet.getString("corporate_name");
				CNPJ cnpj2 = new CNPJ(id, cnpj, corporateName);
				return cnpj2;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			DataBase.showDataBaseErrorMessage();
		}
		return null;
	}

}