package product.controller;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Kit;
import database.DataBase;

public class SearchOfKitControlller
{
	private DataBase dataBase;

	public SearchOfKitControlller(){
	   dataBase = new DataBase();
	   dataBase.connect();
    }
	
	public void queryAll(JTable table)
	{

		    String sql = "SELECT * FROM kit order by kit_name";
			DefaultTableModel tbl = (DefaultTableModel) table.getModel();
			try(ResultSet rs = dataBase.executeQuery(sql)){
				while(rs.next()) {
					String name = rs.getString("kit_name");
					String description = rs.getString("descrition");
					tbl.addRow(new Object[] {name,description});
				}
			} catch (SQLException e) {
		        e.printStackTrace();
	        }
	}
	public void search(JTable table,String kitName,List products)
	{

		    String sql = "SELECT * FROM kit order by kit_name WHERE";
		    int parameterCount = 0;
		    if(kitName!=null && kitName.isEmpty())
		    {
		    	sql+=" kit_name like ? ";
		    	kitName+="%";
		    }
		    if(products.size() > 0)
		    {	
		    	
		    	for(Object objProd:products)
		    	{
		    		if(parameterCount>0)
			    		sql+=" AND";
		    		
		    		int productId = (int) objProd; 
		    		sql+=" kit.id  in (select kit  from kit_relationship where kit_relationship.product=?) ";
		    		
		    		parameterCount++;
		    	}
		    	
		    }
			DefaultTableModel tbl = (DefaultTableModel) table.getModel();
			try(ResultSet rs = dataBase.executeQuery(sql)){
				while(rs.next()) {
					String name = rs.getString("kit_name");
					String description = rs.getString("descrition");
					tbl.addRow(new Object[] {name,description});
				}
			} catch (SQLException e) {
		        e.printStackTrace();
	        }
	}
	
	
}
