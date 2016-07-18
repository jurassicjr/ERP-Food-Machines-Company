package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.DataBase;

public class CstDAO {
	
	private DataBase dataBase;

	public CstDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	public boolean isIcmsCst(String cst) {
	    String query = "select * from cst_icms where cod = ?";
	    try(ResultSet rs = dataBase.executeQuery(query, cst)){
	    	if(rs.next()) {
	    		return true;
	    	}
	    	dataBase.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
        }
	    dataBase.close();
		return false;
    }

	public boolean isCofinsCst(String cst) {
		String query = "select * from cst_pis_cofins where cod = ?";
	    try(ResultSet rs = dataBase.executeQuery(query, cst)){
	    	if(rs.next()) {
	    		return true;
	    	}
	    	dataBase.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
        }
	    dataBase.close();
		return false;
    }

	public boolean isIpiCst(String cst) {
		String query = "select * from cst_pis_cofins where cod = ?";
	    try(ResultSet rs = dataBase.executeQuery(query, cst)){
	    	if(rs.next()) {
	    		return true;
	    	}
	    } catch (SQLException e) {
	        e.printStackTrace();
        }
		return false;
    }

}
