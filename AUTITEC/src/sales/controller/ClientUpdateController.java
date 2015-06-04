package sales.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import model.City;
import model.Client;
import model.State;
import database.DataBase;

public class ClientUpdateController extends SalesController {
	
	  private DataBase dataBase;
	  public ClientUpdateController()
	  {
		   dataBase = new DataBase();
		   dataBase.connect();
		  
	  }
    public void deleteClient(Client client)
    {
		dataBase.executeUpdate("delete from client where id = ?",client.getId());
    }
	public void updateClient(Client client,Boolean isPF)
	{
		String sql;
		if(isPF)
		{
			Object[] fieldsToUpdate = {client.getName(),client.getBirthDate(),
					client.getRg(),client.getCpf(),client.getSex()
					,client.getStreet(),client.getNeighborhood(),client.getCep()
					,client.getState().getId(),client.getCity().getId(),client.getPhone(),client.getEmail(),client.getId()};
			sql = "update client set name=? , birthdate = ? ,rg = ?, cpf =?, sex = ? , street = ?, "
					+ "neighborhood = ?, cep = ?, state = ? ,city = ?,phone = ?,email = ? where id = ?";
			dataBase.executeUpdate(sql,fieldsToUpdate);
		}
		else
		{	
			try{
			Object[] fieldsToUpdate = {client.getCompanyNAme(),client.getCnpj(),
					client.getStateInscrition(),client.getContactName()
					,client.getStreet(),client.getNeighborhood(),client.getCep()
					,client.getState().getId(),client.getCity().getId(),client.getPhone(),client.getEmail(),client.getId()};
			sql = "update client set companyname=? , cnpj = ? ,ie = ?, companycontactname =?, street = ?, "
					+ "neighborhood = ?, cep = ?, state = ? ,city = ?,phone = ?,email = ? where id = ?";
			dataBase.executeUpdate(sql,fieldsToUpdate);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
			
	}
	public City getCity(int cityId)
	{
		 String sql = "SELECT * FROM city where id = ?";
		 City city;
		 try{
			 ResultSet rs = dataBase.executeQuery(sql,cityId);
			 if(rs.next())
				 city = new City(rs.getInt("id"),rs.getString("name"),new State(rs.getInt("state"),""));
			 else
				 city = new City(0,"",null);
			 
				return city;
		 }catch(SQLException e)
		 {
			 e.printStackTrace();
			 return null;
		 }
	}
	public State getState(int stateId)
	{
		 String sql = "SELECT * FROM state where id = ?";
		 State state;
		 try{
			 ResultSet rs = dataBase.executeQuery(sql,stateId);
			 if(rs.next())
				 state = new State(rs.getInt("id"),rs.getString("name"));
			 else
				 state = new State(0,"");
			 
				return state;
		 }catch(SQLException e)
		 {
			 e.printStackTrace();
			 return null;
		 }
	}
	public void queryAll(JComboBox<Client> cboClients,boolean itemSelected)
	{

		    String sql = "SELECT * FROM client order by name";
		    
		    DefaultComboBoxModel<Client>  clients = new DefaultComboBoxModel<Client>();
		    cboClients.removeAllItems();
			try(ResultSet rs = dataBase.executeQuery(sql)){
				while(rs.next()) {
					
					Client client  = new Client();
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
					clients.addElement(client);
				}
				cboClients.setModel(clients);
			
			if(!itemSelected)
			    cboClients.setSelectedItem(null);	
		 
			} catch (SQLException e) {
		        e.printStackTrace();
	        }
	}
}
