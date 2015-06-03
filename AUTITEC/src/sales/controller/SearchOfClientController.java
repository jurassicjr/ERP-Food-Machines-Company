package sales.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;

import model.Client;
import userInterface.components.ClientTableModel;
import database.DataBase;

public class SearchOfClientController
{
	private ClientTableModel clientModel;
	private DataBase dataBase;
	public SearchOfClientController()
	{
		   dataBase = new DataBase();
		   dataBase.connect();
		
	}
	public void queryAll(JTable table)
	{

		    String sql = "SELECT * FROM client order by name";
		    ArrayList<Client> clients = new ArrayList<Client>();
			try(ResultSet rs = dataBase.executeQuery(sql)){
				while(rs.next()) {
					
					Client client  = new Client();
					client.setId(rs.getInt("id"));
					client.setName(rs.getString("name"));
					client.setCompanyNAme(rs.getString("companyname"));
					clients.add(client);	
				}
				clientModel = new ClientTableModel(clients);
				table.setModel(clientModel);
				clientModel.fireTableDataChanged();
			} catch (SQLException e) {
		        e.printStackTrace();
	        }
	}
	public void search(JTable table,String param,String fieldToSearch)
	{

		    String sql = "SELECT * FROM client where ";
		    ResultSet rs;
		    try{
		    	
		    if(fieldToSearch.equals("name/company"))
		    {
		    	sql+=" name like ?"; 
		    	param+="%";
		    
		    	rs = dataBase.executeQuery(sql,param);
		    	if(!rs.next())
		    	{
		    		sql = sql.replace(" name like ?","");
		    		
		    		sql+=" companyname like ?"; 
		    		rs = dataBase.executeQuery(sql,param);
		    	}
		    }
		    else
		    {  
		    	sql+=" cpf = ?"; 
		    	rs = dataBase.executeQuery(sql,param);
		    	if(!rs.next())
		    	{
		    		sql = sql.replace(" cpf = ?", "");
		    		sql+=" cnpj = ? "; 
		    		rs = dataBase.executeQuery(sql,param);
		    	}
		    	
		    }  		
		    sql+=" order by name";	
		    ArrayList<Client> clients = new ArrayList<Client>();
			rs.beforeFirst();
				while(rs.next()) {
					
					Client client  = new Client();
					client.setId(rs.getInt("id"));
					client.setName(rs.getString("name"));
					client.setCompanyNAme(rs.getString("companyname"));
					clients.add(client);	
				}
				clientModel = new ClientTableModel(clients);
				table.setModel(clientModel);
				clientModel.fireTableDataChanged();
			} catch (SQLException e) {
		        e.printStackTrace();
	        }
	}
	
	
}
