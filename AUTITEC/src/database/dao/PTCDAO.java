package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import model.CNPJ;
import model.Client;
import model.Employee;
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

	public void register(Map<String, Object> map, boolean isNew) {
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
		Date creationDate = (Date) map.get("date");
		java.sql.Date date = new java.sql.Date(creationDate.getTime());
		int ptcID = dataBase.getAutoIncrementValue("ptc");	
		int idMaster = 0;
		if(isNew) {
			 idMaster = ptcID;			
		}else {
			idMaster = (int) map.get("idMaster");
		}
		String query = "INSERT INTO ptc(aliquot, brute_plus_aliquot, brute_value, suggested,"
		        + " discount, contribuition, final_value, title, client, cnpj, rastreability_code, creationDate, id_master) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Object[] data = new Object[] { aliquot, aliquotPlusBrute, bruteValue, suggestedPrice, discount, contribuition,
		        finalPrice, title, clientID, cnpjID, rastreabilityCode, date, idMaster};
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
				Client client = new ClientDAO().getClientByID(rs.getInt("client"));
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
				java.sql.Date creationDate = rs.getDate("creationDate");
				Date date = new Date(creationDate.getTime());
				int isApproved = rs.getInt("is_approved");
				int idMaster = rs.getInt("id_master");
				int isCompleted = rs.getInt("is_completed");
				
				PTC p = new PTC(title, suggestedPrice, aliquot, bruteValue, brutePlusAliquot, discount, finalValue, client, contribuition, date);
				p.setCnpj(cnpj);
				p.setId(id);
				p.setKitList(kitList);
				p.setMaterialList(materialList);
				p.setProductList(productList);
				p.setRastreabilityCode(rastreabilityCode);
				p.setServiceList(serviceList);
				p.setSuggestedPrice(suggestedPrice);
				p.setIsApproved(isApproved);
				p.setIdMaster(idMaster);
				p.IsCompleted(isCompleted);
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

	public void approve(Map<String, Object> map) {
	    Employee e = (Employee) map.get("approvedBy");
	    PTC ptc = (PTC) map.get("ptc");
	    Date approvationDate = (Date) map.get("date");
	    java.sql.Date date = new java.sql.Date(approvationDate.getTime());
	    String observation = (String) map.get("observation");
	    
	    String query = "INSERT INTO ptc_approvation(date, approved_by, ptc, observation) VALUES (?,?,?,?)";
	    Object[] data = new Object[] {date, e.getId(), ptc.getId(), observation};
	    dataBase.executeUpdate(query, data);
	    String query2 = "UPDATE ptc SET is_approved = 1 WHERE id = ?";
	    dataBase.executeUpdate(query2, ptc.getId());
	    String query3 = "UPDATE ptc SET is_approved = 3 where id_master = ? AND id != ?";
	    dataBase.executeUpdate(query3, new Object[] {ptc.getIdMaster(), ptc.getId()});
    }

	public PTC getPTCById(int ptcId) {
		String ptcQuery = "SELECT *FROM PTC where id = ?";
		try (ResultSet rs = dataBase.executeQuery(ptcQuery, ptcId)) {
			if(rs.next()){
				int id = rs.getInt("id");
				String title = rs.getString("title");
				Client client = new ClientDAO().getClientByID(rs.getInt("client"));
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
				java.sql.Date creationDate = rs.getDate("creationDate");
				Date date = new Date(creationDate.getTime());
				int isApproved = rs.getInt("is_approved");
				int idMaster = rs.getInt("id_master");
				int isCompleted = rs.getInt("is_completed");
				
				PTC p = new PTC(title, suggestedPrice, aliquot, bruteValue, brutePlusAliquot, discount, finalValue, client, contribuition, date);
				p.setCnpj(cnpj);
				p.setId(id);
				p.setKitList(kitList);
				p.setMaterialList(materialList);
				p.setProductList(productList);
				p.setRastreabilityCode(rastreabilityCode);
				p.setServiceList(serviceList);
				p.setSuggestedPrice(suggestedPrice);
				p.setIsApproved(isApproved);
				p.setIdMaster(idMaster);
				p.IsCompleted(isCompleted);
				return p;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
    }

}