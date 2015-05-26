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
					clients.add(client);	
				}
				clientModel = new ClientTableModel(clients);
				table.setModel(clientModel);
				clientModel.fireTableDataChanged();
			} catch (SQLException e) {
		        e.printStackTrace();
	        }
	}
	public void search(JTable table,String name,String c)
	{

		    String sql = "SELECT * FROM client where ";
		    Boolean flagName = false;
		    

		    
		    sql+=" order by name";	
		    ArrayList<Client> clients = new ArrayList<Client>();
			try(ResultSet rs = dataBase.executeQuery(sql)){
				while(rs.next()) {
					
					Client client  = new Client();
					client.setId(rs.getInt("id"));
					client.setName(rs.getString("name"));
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
