package sales.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;

import model.Product;
import model.Supplier;
import userInterface.components.SupplierTableModel;
import database.DataBase;

public class SearchOfSupplierController 
{
    private SupplierTableModel supplierModel;
    private DataBase dataBase;
    
    public SearchOfSupplierController()
    {
    	  dataBase = new DataBase();
		  dataBase.connect();
    }
    
    public void queryAll(JTable table)
	{

		    String sql = "SELECT * FROM suppliers order by corporate_name";
		    ArrayList<Supplier> suppliers = new ArrayList<>();
			try(ResultSet rs = dataBase.executeQuery(sql)){
				while(rs.next()) {
					Supplier supplier = new Supplier();
					supplier.setId(rs.getInt("id"));
					supplier.setCorporateName(rs.getString("corporate_name"));
					suppliers.add(supplier);
				}
				supplierModel = new SupplierTableModel(suppliers);
				table.setModel(supplierModel);
				supplierModel.fireTableDataChanged();
			} catch (SQLException e) {
		        e.printStackTrace();
	        }
	}
 
    public void search(JTable table,String param,String paramName)
   	{
    		
   		    String sql = "SELECT * FROM suppliers where ";
   		    if(!paramName.isEmpty())
   		    {
   		    	if(paramName.equals("corporate_name"))
   		    	{
   		    		sql+=" corporate_name like ? ";
   		    		param+="%";
   		    	}
   		    	else
   		    	if(paramName.equals("cnpj"))
   		    	{
   		    		sql+=" cnpj = ? ";
   		    	}
   		    	else
   		    	if(paramName.equals("productName"))
   		    	{
   		    		param = param.split("-")[0].trim();
   		    		sql+=" id in (select supplier from supplier_product_association where product = ?) ";
   		    	}
   		    	
   		    }
   		    else
   		    {
   		    	sql = sql.replace("where","");
   		    	param = "";
   		    }
   		    sql+= " order by corporate_name ";
   		    ArrayList<Supplier> suppliers = new ArrayList<>();
   			try		
   			{
   				ResultSet rs ;
   				if(param.isEmpty())
   					rs = dataBase.executeQuery(sql);
   				else
   					rs = dataBase.executeQuery(sql,param);
   					
   				while(rs.next()) {
   					Supplier supplier = new Supplier();
   					supplier.setId(rs.getInt("id"));
   					supplier.setCorporateName(rs.getString("corporate_name"));
   					suppliers.add(supplier);
   				}
   				supplierModel = new SupplierTableModel(suppliers);
   				table.setModel(supplierModel);
   				supplierModel.fireTableDataChanged();
   			} catch (SQLException e) {
   		        e.printStackTrace();
   	        }
   	}
    
}
