package database.dao;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.CFOPExit;
import database.DataBase;

public class CFOPDAO {

	private DataBase dataBase;

	public CFOPDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	public CFOPExit getCFOPExitByCode(String code) {
	    String query = "select * from tbl_cfop_tab_exit where CFOP_DE = ?";
	    try(ResultSet rs = dataBase.executeQuery(query, code)){
	    	if(rs.next()) {
	    		int id = rs.getInt("id");
	    		Blob blob = rs.getBlob("OBS");
	    		byte[] bdata = blob.getBytes(1, (int) blob.length());
	    		String description = new String(bdata);
	    		String cfopDE = rs.getString("CFOP_DE");
	    		String cfopFE = rs.getString("CFOP_FE");
	    		String cfopIN = rs.getString("CFOP_IN");
	    		String nat = rs.getString("nat");
	    		CFOPExit cfop = new CFOPExit(id, description, cfopDE, cfopFE, cfopIN, nat);
	    		return cfop;
	    	}
	    } catch (SQLException e) {
	        e.printStackTrace();
        }
		return null;
    }

	public CFOPExit getCFOPExitById(int cfopID) {
		  String query = "select * from tbl_cfop_tab_exit where id = ?";
		    try(ResultSet rs = dataBase.executeQuery(query, cfopID)){
		    	if(rs.next()) {
		    		int id = rs.getInt("id");
		    		Blob blob = rs.getBlob("OBS");
		    		byte[] bdata = blob.getBytes(1, (int) blob.length());
		    		String description = new String(bdata);
		    		String cfopDE = rs.getString("CFOP_DE");
		    		String cfopFE = rs.getString("CFOP_FE");
		    		String cfopIN = rs.getString("CFOP_IN");
		    		String nat = rs.getString("nat");
		    		CFOPExit cfop = new CFOPExit(id, description, cfopDE, cfopFE, cfopIN, nat);
		    		return cfop;
		    	}
		    } catch (SQLException e) {
		        e.printStackTrace();
	        }
			return null;
    }
	
	
}
