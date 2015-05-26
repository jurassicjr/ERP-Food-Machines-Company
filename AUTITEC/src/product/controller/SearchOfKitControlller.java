package product.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import userInterface.components.KitTableModel;
import model.Kit;
import model.Product;
import database.DataBase;

public class SearchOfKitControlller
{
	private DataBase dataBase;
	private KitTableModel kitModel;
	public SearchOfKitControlller(){
	   dataBase = new DataBase();
	   dataBase.connect();
	   KitTableModel kitModel;
    }
	
	public void queryAll(JTable table)
	{

		    String sql = "SELECT * FROM kit order by kit_name";
		    ArrayList<Kit> kits = new ArrayList<Kit>();
			try(ResultSet rs = dataBase.executeQuery(sql)){
				while(rs.next()) {
					
					Integer id =rs.getInt("id");
					String name = rs.getString("kit_name");
					String description = rs.getString("descrition");
					Kit kit  = new Kit(id, name, description);
					
					kits.add(kit);
				}
				kitModel = new KitTableModel(kits);
				table.setModel(kitModel);
				kitModel.fireTableDataChanged();
			} catch (SQLException e) {
		        e.printStackTrace();
	        }
	}
	public void search(JTable table,String kitName,Vector products)
	{

		    String sql = "SELECT * FROM kit WHERE";
		    ArrayList<Object> parameters = new ArrayList<>();
		    if(kitName!=null && !kitName.isEmpty())
		    {
		    	sql+=" kit_name like ? ";
		    	kitName+="%";
		    	parameters.add(kitName);
		    }
		    if(products.size() > 0)
		    {	
		    	
		    	for(Object objProd:products)
		    	{
		    		if(parameters.size() > 0)
			    		sql+=" AND";
		    		
		    		Product p = (Product) objProd; 
		    		sql+=" kit.id  in (select kit  from kit_relationship where kit_relationship.product=?) ";
		    		
		    		parameters.add(p.getId());
		    	}
		    	
		    }
		    if(parameters.size()  == 0)
		    	sql = sql.replace("WHERE","");
		    sql+="order by kit_name";
		    System.err.println(sql);
			
		    ArrayList<Kit> kits = new ArrayList<Kit>();
			try(ResultSet rs = dataBase.executeQuery(sql,parameters)){
				while(rs.next()) {
					
					Integer id =rs.getInt("id");
					String name = rs.getString("kit_name");
					String description = rs.getString("descrition");
					Kit kit  = new Kit(id, name, description);
					
					kits.add(kit);
				}
				kitModel = new KitTableModel(kits);
				table.setModel(kitModel);
				kitModel.fireTableDataChanged();
			} catch (SQLException e) {
		        e.printStackTrace();
	        }
	}
	
	public void addProduct(String productName,Vector<Product> products)
	{
		Product product = getProductByName(productName);
		products.add(product);	
	}
	public void removeProduct(Vector products,int index)
	{
		products.remove(index);
	}
	@SuppressWarnings("finally")
	public Product getProductByName(String name)
	{
		
		String sql = "SELECT * FROM compost_product where product = ?";
		Product product = new Product();
		try (ResultSet rs = dataBase.executeQuery(sql,name)) {
			if (rs.next()) {
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("product"));
				product.setDescription(rs.getString("description"));
			}
			
		} catch (SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		finally
		{
			return product;
		}
		
	}
	
}
