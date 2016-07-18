package sales.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Employee;
import model.Material;
import model.PTC;
import model.PurchaseRequisition;
import sales.view.PurchaseRequisitionFrame;
import util.ShowMessage;
import database.DataBase;
import database.dao.EmployeeDAO;
import database.dao.PTCDAO;
import database.dao.PurchaseRequisitionDAO;

public class SalesRequisitionControllerFrame {
	
	private PurchaseRequisitionFrame frame;
	private DataBase dataBase;

	public SalesRequisitionControllerFrame(PurchaseRequisitionFrame frame) {
		this.frame = frame;
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	public void close() {
		int i = ShowMessage.questionMessage(frame, "Sair", "Deseja realmente fechar o registro de requisição de compra ?");
		if(i == JOptionPane.YES_OPTION) {
			frame.dispose();
		}
	}
	
	public void fillEmployees(JComboBox<Employee> cboEmployee){
		List<Employee> list = new EmployeeDAO().getEmployees();
		list.forEach(e -> cboEmployee.addItem(e));
	}
	
//	public void fillPTC(JComboBox<PTC> cboPTC) {
//		List<PTC> list = new PTCDAO().getAllPTC();
//		boolean v = false;
//		PTC pt = null;
//		for (int i = 0; i < list.size(); i++) {
//			for (int j = i; j < list.size(); j++) {
//				if (list.get(i).getIdMaster() == list.get(j).getIdMaster()) {
//					v = true;
//					if (list.get(i).getId() <= list.get(j).getId()) {
//						pt = list.get(j);
//					}
//				}
//			}
//			if (list.get(i).getIsApproved() == 0 && !v) {
//				if(list.get(i).getIsCompleted() == 0) {
//					cboPTC.addItem(list.get(i));					
//				}
//			} else if (list.get(i).getIsApproved() == 0 && v) {
//				if(list.get(i).getIsCompleted() == 0) {					
//					cboPTC.addItem(pt);
//				}
//			}
//			v = false;
//		}
//		cboPTC.setSelectedIndex(-1);
//	}
	
	public void fillPTC(JComboBox<PTC> cboPTC) {
		List<PTC> list = new PTCDAO().getAllPTC();
		cboPTC.removeAllItems();
		for (PTC p : list) {
	        if(p.getIsApproved() == 1) {
	        	cboPTC.addItem(p);
	        }
        }
	}
		
	
	
	public void fillMaterials(JComboBox<Material> product) {
		product.removeAllItems();
		try (ResultSet rs = dataBase.executeQuery("SELECT *FROM Product")){
			while (rs.next()) {
				Material produto = new Material();
				produto.setName(rs.getString("name"));
				produto.setDescrition(rs.getString("descricao"));
				produto.setId(rs.getInt("id"));
				produto.setAmmount(rs.getInt("quantidade"));
				produto.setHeigth(rs.getDouble("z"));
				produto.setInternalCode(rs.getString("internal_code"));
				produto.setLength(rs.getDouble("y"));
				produto.setMaterialType(rs.getInt("material_type"));
				produto.setMeasureUnit(rs.getInt("measure_unit"));
				produto.setModel(rs.getInt("model"));
				produto.setNCM(rs.getString("ncm"));
				produto.setWidth(rs.getDouble("x"));
				product.addItem(produto);
			}
			product.setSelectedIndex(-1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addMaterial(Material material, double ammount, JTable table,String area) {

		for (int i = 0; i < table.getRowCount(); ++i) {

			Material m = (Material) table.getValueAt(i, 0);

			if (m.equals(material)) {
				ShowMessage.errorMessage(frame, "Material já presente", material.getName()
				        + " já existe nessa requisição de compra");
				return;
			}
		}

		DefaultTableModel tbl = (DefaultTableModel) table.getModel();

		tbl.addRow(new Object[] { material, ammount , area});

	}

	public void register(PurchaseRequisition sr) {
	    HashMap<String, Object> map = new HashMap<String, Object>();
	    map.put("requisitor", sr.getRequisitor());
	    map.put("priority", sr.getPrority());
	    map.put("requisitionNumber", sr.getRequisitionNumber());
	    map.put("ptc", sr.getPtc());
	    map.put("date", sr.getDate());
	    map.put("associationList", sr.getAssociationList());
	    map.put("justification", sr.getJustification());
	    PurchaseRequisitionDAO sDAO = new PurchaseRequisitionDAO();
	    sDAO.register(map);
    }

	public void fillRequisitionNumber(JTextField txtRequisitionNumber) {
	    String requisitionNumber = new PurchaseRequisitionDAO().getNewRequisitionNumber();
	    if(requisitionNumber.length() == 1) {
	    	requisitionNumber = "000" + requisitionNumber;
	    }else if(requisitionNumber.length() == 2) {
	    	requisitionNumber = "00" + requisitionNumber;
	    }else if(requisitionNumber.length() == 3) {
	    	requisitionNumber = "0" + requisitionNumber;
	    }
		txtRequisitionNumber.setText(requisitionNumber);
    }
}
