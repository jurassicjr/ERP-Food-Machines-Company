package sales.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;

import model.City;
import model.Client;
import model.State;
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
	public boolean isPF(Client client)
	{
		return client.getName() != null &&  !client.getName().isEmpty();
	}
	public Client getClientByName(Integer index)
	{
			Client lineClient = clientModel.getClient(index);
		    String sql;
		    ResultSet rs;
		    Boolean isPF = isPF(lineClient);
		    if(isPF)
		    	sql =  "SELECT * FROM client where name = ?";
		    else
		    	sql = "SELECT * FROM client where companyname = ?";
			try{
				if(isPF)
					rs = dataBase.executeQuery(sql,lineClient.getName());
				else
					rs = dataBase.executeQuery(sql,lineClient.getCompanyNAme());
				
				Client client  = new Client();
				while(rs.next()) {
					
					client.setId(rs.getInt("id"));
					client.setName(rs.getString("name"));
					client.setCompanyNAme(rs.getString("companyname"));
					client.setStreet(rs.getString("street"));
					client.setBirthDate(rs.getDate("birthdate"));
					client.setNeighborhood(rs.getString("neighborhood"));
					client.setState(new State(rs.getInt("state"),""));
					client.setCity(new City(rs.getInt("city"),"",client.getState()));
					client.setCep(rs.getString("cep"));
					client.setPhone(rs.getString("phone"));
					client.setEmail(rs.getString("email"));
					client.setCompanyNAme(rs.getString("companyname"));
					client.setCpf(rs.getString("cpf"));
					client.setCnpj(rs.getString("cnpj"));
					client.setStateInscrition(rs.getString("ie"));
					client.setSex(rs.getString("sex"));
					client.setContactName(rs.getString("companycontactname"));
					client.setRg(rs.getString("rg"));
					
				}
				return client;
			} catch (SQLException e) {
		        e.printStackTrace();
		        return new Client();
	        }
	}
}
