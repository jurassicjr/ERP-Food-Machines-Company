package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import model.CFOPExit;
import model.City;
import model.Employee;
import model.Material;
import model.Nfe;
import model.NfeMaterialRelation;
import model.PurchaseOrder;
import model.Session;
import model.State;
import model.Supplier;
import database.DataBase;

public class NfeDAO {

	
	private DataBase dataBase;

	public NfeDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	public boolean register(Map<String, Object> map) {
	    //Atribuindo map
		Nfe nfe = (Nfe) map.get("nfe");
		@SuppressWarnings("unchecked")
        List<NfeMaterialRelation> nfemr = (List<NfeMaterialRelation>) map.get("nfeRelationList");

		//Registrando Nfe
		
		String antt_code = nfe.getAntt_code();
		CFOPExit cfop = nfe.getCfop();
		City city = nfe.getCity();
		String companyName = nfe.getCompanyName();
		String danfe = nfe.getDanfe();
		String danfeSerial = nfe.getDanfeSerial();
		Date emissionDate = nfe.getEmissionDate();
		java.sql.Date eDate = new java.sql.Date(emissionDate.getTime());
		Date exitDate = nfe.getExitDate();
		java.sql.Date exDate = new java.sql.Date(exitDate.getTime());
		String exitHour = nfe.getExitHour();
		String fiscalNumber = nfe.getFiscalNumber();
		int freight = nfe.getFreight();
		String freightCNPJ = nfe.getFreightCNPJ();
		String model = nfe.getModel();
		State state = nfe.getState();
		String street = nfe.getStreet();
		Supplier supplier = nfe.getSupplier();
		int supplierID = supplier.getId();
		double totalCalBase = 0;
		double totalCofinsTax = 0;
		double totalIpiTax = 0;
		double totalIcmsTax = 0;
		double totalPisTax = 0;
		Employee em = Session.getInstance().getUser().getEmployee();
		int employeeID = em.getId();
		PurchaseOrder purchaseOrder = nfe.getPurchaseOrder();
		int purchaseOrderId = purchaseOrder.getId();
		
		for (NfeMaterialRelation nfeMaterialRelation : nfemr) {
			totalCalBase += nfeMaterialRelation.getCalBase();
			totalCofinsTax += nfeMaterialRelation.getCofinsTotalValue();
			totalIcmsTax += nfeMaterialRelation.getIcmsTotalValue();
			totalIpiTax += nfeMaterialRelation.getIpiTotalValue();
			totalPisTax += nfeMaterialRelation.getPisTotalValue();
        }
		double totalNfeValue = totalCalBase + totalIpiTax;
		Object[] data = new Object[] {danfe, exitHour, model, eDate, exDate, danfeSerial, fiscalNumber, cfop.getId(), supplierID,  freight, freightCNPJ, companyName, 
				street, state, city, antt_code, totalPisTax, totalIpiTax, totalIcmsTax, totalCofinsTax, totalNfeValue, totalCalBase, employeeID, purchaseOrderId};
		String query = "insert into nfe(danfe, exit_hour,model, emission_date, exit_date, danfe_serial, fiscal_number, cfop, supplier, freight_type, freight_cnpj, company_name"
				+ ", street, state, city, antt_code, total_pis_tax, total_ipi_tax, total_icms_tax, total_cofins_tax, total_nfe_value, total_cal_base, employee, purchase_order)"
				+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		int nfeID = dataBase.getAutoIncrementValue("nfe");
		
		try{
			dataBase.executeUpdate(query, data);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		for (NfeMaterialRelation nfeMaterialRelation : nfemr) {
	        double aliquotCofins = nfeMaterialRelation.getAliquotCofins();
	        CFOPExit matCFOP = nfeMaterialRelation.getCfop();
	        double aliquotIcms = nfeMaterialRelation.getAliquotIcms();
	        double aliquotIpi = nfeMaterialRelation.getAliquotIpi();
	        double aliquotPis = nfeMaterialRelation.getAliquotPis();
	        double ammount = nfeMaterialRelation.getAmmount();
	        double calBase = nfeMaterialRelation.getCalBase();
	        double cofinsTotalValue = nfeMaterialRelation.getCofinsTotalValue();
	        int cstCofins = Integer.parseInt(nfeMaterialRelation.getCstCofins());
	        int cstIcms = Integer.parseInt(nfeMaterialRelation.getCstIcms());
	        int cstIpi = Integer.parseInt(nfeMaterialRelation.getCstIpi());
	        int cstPis = Integer.parseInt(nfeMaterialRelation.getCstPis());
	        double finalCost = nfeMaterialRelation.getFinalCost();
	        double finalSalesValue = nfeMaterialRelation.getFinalSalesValue();
	        double icmsTotalValue = nfeMaterialRelation.getIcmsTotalValue();
	        double ipiTotalValue = nfeMaterialRelation.getIpiTotalValue();
	        Material material = nfeMaterialRelation.getMaterial();
	        int materialID = material.getId();
	        double pisTotalValue = nfeMaterialRelation.getPisTotalValue();
	        double salesPercent = nfeMaterialRelation.getSalesPercent();
	        double totalValue = nfeMaterialRelation.getTotalValue();
	        int typeIndex = nfeMaterialRelation.getTypeIndex();
	        double unFinalCost = nfeMaterialRelation.getUnFinalCost();
	        double unValue = nfeMaterialRelation.getUnValue();
	        
	        Object[] rData = new Object[] {materialID, nfeID, ammount, totalValue, unValue, matCFOP.getId(), typeIndex, cstIcms, cstCofins, cstIpi, cstPis, finalCost,
	        		unFinalCost, salesPercent, finalSalesValue, aliquotPis, aliquotIpi, aliquotCofins, aliquotIcms, icmsTotalValue, pisTotalValue, ipiTotalValue,
	        		cofinsTotalValue, calBase};
	        String rQuery = "insert into nfe_material_relation(material, nfe, ammount, total_value, un_value, cfop, m_type, cst_icms, cst_cofins, cst_ipi, cst_pis, final_cost,"
	        		+ " un_final_cost, sales_percent, final_sales_value, aliquot_pis, aliquot_ipi, aliquot_cofins, aliquot_icms, icms_total_value, pis_total_value,"
	        		+ " ipi_total_value, cofins_total_value, cal_base) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	        
	        try {
	        	dataBase.executeUpdate(rQuery, rData);	        	
	        }catch(Exception e) {
	        	e.printStackTrace();
	        	return false;
	        }
        }
		return true;
		
    }

	public List<NfeMaterialRelation> getAllNfeRelation(Nfe nfe) {
	    int nfeID = nfe.getId();
	    String query = "select * from nfe_material_relation where nfe = ?";
	    List<NfeMaterialRelation> nfeList = new ArrayList<NfeMaterialRelation>();
	    try(ResultSet rs = dataBase.executeQuery(query, nfeID)){
	    	while(rs.next()){
	    		int materialID = rs.getInt("material");
	    		Material material = new MaterialDAO().getMaterialById(materialID);
	    		double totalValue = rs.getDouble("total_value");
	    		double unValue = rs.getDouble("un_value");
	    		int cfopID = rs.getInt("cfop");
	    		CFOPExit cfop = new CFOPDAO().getCFOPExitById(cfopID);
	    		int typeIndex = rs.getInt("m_type");
	    		String cstIcms = rs.getString("cst_icms");
	    		String cstPis = rs.getString("cst_pis");
	    		String cstCofins = rs.getString("cst_cofins");
	    		String cstIpi = rs.getString("cst_ipi");
	    		double finalCost = rs.getDouble("final_cost");
	    		double unFinalCost = rs.getDouble("un_final_cost");
	    		double salesPercent = rs.getDouble("sales_percent");
	    		double finalSalesValue = rs.getDouble("final_sales_value");
	    		double aliquotPis = rs.getDouble("aliquot_pis");
	    		double aliquotIpi = rs.getDouble("aliquot_ipi");
	    		double aliquotCofins = rs.getDouble("aliquot_cofins");
	    		double aliquotIcms = rs.getDouble("aliquot_icms");
	    		double icmsTotalValue = rs.getDouble("icms_total_value");
	    		double pisTotalValue = rs.getDouble("pis_total_value");
	    		double ipiTotalValue = rs.getDouble("ipi_total_value");
	    		double cofinsTotalValue = rs.getDouble("cofins_total_value");
	    		double ammount = rs.getDouble("ammount");
	    		double calBase = rs.getDouble("cal_base"); 
	    		NfeMaterialRelation nfemr = new NfeMaterialRelation(material, totalValue, unValue, cfop, typeIndex, cstIcms, cstPis,
	    				cstCofins, cstIpi, finalCost, unFinalCost, salesPercent, finalSalesValue, ammount, nfe, aliquotPis, aliquotIpi,
	    				aliquotCofins, aliquotIcms, icmsTotalValue, pisTotalValue, ipiTotalValue, cofinsTotalValue, calBase);
	    		nfeList.add(nfemr);
	    	}
	    	return nfeList;
	    } catch (SQLException e) {
	        e.printStackTrace();
        }
		return null;
    }

	public List<Nfe> getNfeList() {
	    List<Nfe> nfeList = new ArrayList<Nfe>();
	    String query = "select * from nfe";
		try(ResultSet rs = dataBase.executeQuery(query)){
			while(rs.next()) {
				String danfe = rs.getString("danfe");
				String exitHour = rs.getString("exit_hour");
				String model = rs.getString("model");
				java.sql.Date eDate = rs.getDate("emission_date");
				Date emissionDate = new Date(eDate.getTime());
				java.sql.Date exDate = rs.getDate("exit_date");
				Date exitDate =  new Date(exDate.getTime());
				String danfeSerial = rs.getString("danfe_serial");
				String fiscalNumber = rs.getString("fiscal_number");
				int cfopID = rs.getInt("cfop");
				CFOPExit cfop = new CFOPDAO().getCFOPExitById(cfopID);
				int supplierID = rs.getInt("supplier");
				Supplier supplier = new SuppliersDAO().getSupplierbyId(supplierID);
				int freight = rs.getInt("freight_type");
				int purchaseOrderId = rs.getInt("purchase_order");
				PurchaseOrder po = new PurchaseOrderDAO().getCompletePurchaseById(purchaseOrderId);
				Nfe nfe = new Nfe(danfe, exitHour, model, emissionDate, exitDate, danfeSerial, fiscalNumber, cfop, supplier, freight);
				nfe.setPurchaseOrder(po);
				int id = rs.getInt("id");
				nfe.setId(id);
				nfeList.add(nfe);
			}
		} catch (SQLException e) {
	        e.printStackTrace();
        }
	    return nfeList;
    }

	public List<Nfe> getAllActiveFiscalNote() {
	    String sql = "select * from nfe where purchase_order in (select rs.id from purchase_order as rs where isConcluded = ?)";
	    List<Nfe> list = new ArrayList<Nfe>();
	    try(ResultSet rs = dataBase.executeQuery(sql, false)){
	    	while(rs.next()) {
	    		String danfe = rs.getString("danfe");
				String exitHour = rs.getString("exit_hour");
				String model = rs.getString("model");
				java.sql.Date eDate = rs.getDate("emission_date");
				Date emissionDate = new Date(eDate.getTime());
				java.sql.Date exDate = rs.getDate("exit_date");
				Date exitDate =  new Date(exDate.getTime());
				String danfeSerial = rs.getString("danfe_serial");
				String fiscalNumber = rs.getString("fiscal_number");
				int cfopID = rs.getInt("cfop");
				CFOPExit cfop = new CFOPDAO().getCFOPExitById(cfopID);
				int supplierID = rs.getInt("supplier");
				Supplier supplier = new SuppliersDAO().getSupplierbyId(supplierID);
				int freight = rs.getInt("freight_type");
				Nfe nfe = new Nfe(danfe, exitHour, model, emissionDate, exitDate, danfeSerial, fiscalNumber, cfop, supplier, freight);
				int id = rs.getInt("id");
				nfe.setId(id);
				list.add(nfe);
	    	}
	    	return list;
	    } catch (SQLException e) {
	        e.printStackTrace();
        }
		return null;
    }	
}
