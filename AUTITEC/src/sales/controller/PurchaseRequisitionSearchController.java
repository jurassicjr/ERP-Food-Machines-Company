package sales.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Employee;
import model.PTC;
import model.PurchaseRequisition;
import sales.view.search.PurchaseRequisitionSearchFrame;
import util.ClearFrame;
import util.ShowMessage;
import database.DataBase;
import database.dao.EmployeeDAO;
import database.dao.PTCDAO;
import database.dao.SalesRequisitionDAO;

public class PurchaseRequisitionSearchController {

	private PurchaseRequisitionSearchFrame frame;
	private DataBase dataBase;

	public PurchaseRequisitionSearchController(PurchaseRequisitionSearchFrame frame) {
		this.frame = frame;
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	public void close() {
		int i = ShowMessage.questionMessage(frame, "Fechar", "Deseja realmente fechar o acompanhamento de requisições ?");
		if(i == JOptionPane.YES_OPTION) {
			frame.dispose();
		}
	}

	public void fillTable(JTable table) {
		List<PurchaseRequisition> list = new SalesRequisitionDAO().getAllRequisition();
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		for (PurchaseRequisition sr : list) {
			int status = sr.getStatus();
			String statusString = null;
			switch(status) {
				case 1:{
					statusString = "Pendente";
					break;
				}case 2:{
					statusString = "Concluido";
					break;
				}case 3:{
					statusString = "Semi-concluido/aberto";
					break;
				}case 4:{
					statusString = "Semi-concluido/fechado";
					break;
				}case 5:{
					statusString = "Negado";
					break;
				}
			}
			String fDate = new SimpleDateFormat("dd/MM/yyyy").format(sr.getDate());
	        Object[] rowData = new Object[] {sr, sr.getPtc().getRastreabilityCode(), fDate, statusString}; 
			tbl.addRow(rowData);
			}
		frame.repaint();
		frame.validate();
    }
	
	public void fillPTC(JComboBox<PTC> cboPTC) {
		List<PTC> list = new PTCDAO().getAllPTC();
		cboPTC.removeAllItems();
		for (PTC p : list) {
	        if(p.getIsApproved() == 1) {
	        	cboPTC.addItem(p);
	        }
        }
	}
	
	public void search(JTable table, String requisitionNumber, Date startDate, Date endDate, PTC ptc, Integer status) {
		   int parameterCount = 0;
		   
		   List<Object> obj = new ArrayList<Object>() ;
		   String sql = "SELECT * FROM sales_requisition WHERE ";
		   
		   if(requisitionNumber!=null && !requisitionNumber.isEmpty()){
			  sql+= " requisition_number LIKE ? "; 
			  requisitionNumber = requisitionNumber +"%";
			  obj.add(requisitionNumber);
			  parameterCount++;
			}
		   if(endDate != null && requisitionNumber!=null && requisitionNumber.isEmpty())
		   {
			   if(parameterCount > 0)
					 sql+=" AND "; 
			   
			   sql+=" date <= ? "; 
			   obj.add(new java.sql.Date(endDate.getTime()));
			   parameterCount++;
		   }
		   if(startDate != null)
		   {
			   if(parameterCount > 0)
					 sql+=" AND "; 
			   
			   sql+=" date >= ? "; 
			   obj.add(new java.sql.Date(startDate.getTime()));
			   parameterCount++;
			   
			   
		   }
		   if(ptc != null) {
			   if(parameterCount > 0) sql +=" AND ";
			   sql += " ptc = ? ";
			   obj.add(ptc.getId());
			   parameterCount++;
		   }
		   if(status != -1) {
			   if(parameterCount > 0) sql +=" AND ";
			   sql += " status = ? ";
			   obj.add(status + 1);
			   parameterCount++;
		   }

		   if(parameterCount == 0)
			   sql = sql.replace("WHERE","");
		   
		   sql+= " order by date";
		   
		   DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		   ClearFrame.clearTable(table);
		   try(ResultSet rs = dataBase.executeQuery(sql, obj)){
			   while(rs.next()) {
				   String n = rs.getString("requisition_number");
				   Integer ptcId = rs.getInt("ptc");
				   PTC pt = new PTCDAO().getPTCById(ptcId);
				   java.sql.Date d = rs.getDate("date");
				   Date date = new Date(d.getTime());
				   int statusNumber = rs.getInt("status");
				   String statusString = null;
				   int EmployeeId = rs.getInt("requisitor");
				   new EmployeeDAO();
				   Employee e = EmployeeDAO.getEmployeeById(EmployeeId);
				   String priority = rs.getString("priority");
				   switch(statusNumber) {
					case 1:{
						statusString = "Pendente";
						break;
					}case 2:{
						statusString = "Concluido";
						break;
					}case 3:{
						statusString = "Semi-concluido/aberto";
						break;
					}case 4:{
						statusString = "Semi-concluido/fechado";
						break;
					}case 5:{
						statusString = "Negado";
						break;
					}
				}
				   String dat = new SimpleDateFormat("dd/MM/yyyy").format(date);
				   PurchaseRequisition pr = new PurchaseRequisition(e, requisitionNumber, date, priority, ptc);
				   tbl.addRow(new Object[]{pr, pt.getRastreabilityCode(), dat, statusString});
			   }
			   
		   } catch (SQLException e) {
		    e.printStackTrace();
	    }
	 }
}
