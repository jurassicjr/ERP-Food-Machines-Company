package sales.controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import util.ClearFrame;
import database.DataBase;

public class SearchOfMaterialController extends SalesController{

	private DataBase dataBase;

	public SearchOfMaterialController() {
	   dataBase = new DataBase();
	   dataBase.connect();
    }

	public void search(JTable table, Integer max, Integer min, String name) {
	   int parameterCount = 0;
	   
	   List<Object> obj = new ArrayList<Object>() ;
	   String sql = "SELECT * FROM Product WHERE ";
	   
	   if(name!=null && !name.isEmpty())
	   {
		  
		  sql+= " name LIKE ? "; 
		  name = name +"%";
		  obj.add(name);
		  parameterCount++;
		
	   }
	   if(max >=1)
	   {
		   if(parameterCount > 0)
				 sql+=" AND "; 
		   
		   sql+=" quantidade <= ?"; 
		   obj.add(max);
		   parameterCount++;
	   }
	   if(min >=1)
	   {
		   if(parameterCount > 0)
				 sql+=" AND "; 
		   
		   sql+=" quantidade >= ? "; 
		   obj.add(min);
		   parameterCount++;
		   
		   
	   }
	   if(parameterCount == 0)
		   sql = sql.replace("WHERE","");
	   
	   sql+= " order by name";
	   
	   DefaultTableModel tbl = (DefaultTableModel) table.getModel();
	   ClearFrame.clearTable(table);
	   try(ResultSet rs = dataBase.executeQuery(sql, obj)){
		   while(rs.next()) {
			   String n = rs.getString("name");
			   double ammount = rs.getDouble("quantidade");
			   tbl.addRow(new Object[]{n, ammount});
		   }
		   
	   } catch (SQLException e) {
	    e.printStackTrace();
    }
			   
    }

	public void search(JTable table, int max, int min) {
		String sql = "SELECT *FROM Product WHERE quantidade > ? AND quantidade<?";
		Object[] obj = new Object[] {min, max};
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		try(ResultSet rs = dataBase.executeQuery(sql, obj)){
			while(rs.next()) {
				String n = rs.getString("name");
				double ammount = rs.getDouble("quantidade");
				tbl.addRow(new Object[] {n, ammount});
			}
		} catch (SQLException e) {
	        e.printStackTrace();
        }
    }

	public void simpleSearch(JTable table, String name) {
	    String sql = "SELECT *FROM Product WHERE name LIKE ?";
	    name = name+"%";
		Object[] obj = new Object[] {name};
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		try(ResultSet rs = dataBase.executeQuery(sql, obj)){
			while(rs.next()) {
				String n = rs.getString("name");
				double ammount = rs.getDouble("quantidade");
				tbl.addRow(new Object[] {n, ammount});
			}
		} catch (SQLException e) {
	        e.printStackTrace();
        }
    }
	public void queryAll(JTable table) {
	    String sql = "SELECT * FROM Product ORDER BY NAME";
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		try(ResultSet rs = dataBase.executeQuery(sql)){
			while(rs.next()) {
				String n = rs.getString("name");
				double ammount = rs.getDouble("quantidade");
				tbl.addRow(new Object[] {n, ammount});
			}
		} catch (SQLException e) {
	        e.printStackTrace();
        }
    }
	
}
