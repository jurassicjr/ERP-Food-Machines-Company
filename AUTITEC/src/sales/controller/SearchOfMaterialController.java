package sales.controller;
import java.util.List;

import javax.swing.JTable;

import model.Material;
import model.MeasureUnit;
import database.DataBase;
import database.dao.MeasureUnitDAO;
import database.dao.SearchOfMaterialDAO;

public class SearchOfMaterialController extends SalesController{

	private DataBase dataBase;

	public SearchOfMaterialController() {
	   dataBase = new DataBase();
	   dataBase.connect();
    }

	public void search(JTable table, Integer max, Integer min, String name, int materialModel, int materialType, int measureUnit) {
	   new SearchOfMaterialDAO().search(table, max, min, name, materialModel, materialType, measureUnit, measureUnitList());			   
    }

	private List<MeasureUnit> measureUnitList() {
			List<MeasureUnit> list = new MeasureUnitDAO().getUnits();
		    if(list.isEmpty())return null;
		    return list;
    }

	public void search(JTable table, int max, int min) {
		new SearchOfMaterialDAO().search(table, max, min, measureUnitList());
    }

	public void simpleSearch(JTable table, String name) {
	    new SearchOfMaterialDAO().simpleSearch(table, name, measureUnitList());
    }
	public void queryAll(JTable table) {
	    new SearchOfMaterialDAO().queryAll(table, measureUnitList());
    }
	public Material getMaterialByName(String materialName) {
		Material m = new SearchOfMaterialDAO().getMaterialByName(materialName);
		return m;
    }
	
}
