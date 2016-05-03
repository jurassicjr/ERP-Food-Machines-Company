package model;

import java.util.Date;
import java.util.List;

public class PTC {
	
	private int id;
	private String title;
	private List<Material> materialList;
	private List<Product> productList;
	private List<Service> serviceList;
	private List<Kit> kitList;
	private double suggestedPrice;
	private double aliquot;
	private double brutePrice;
	private double aliquotPlusBrute;
	private Client client;
	private double contribuition;
	private double discount;
	private double finalPrice;
	private CNPJ cnpj;
	private String rastreabilityCode;
	private int isApproved;
	private Date creationDate;
	private int idMaster;
	private int isCompleted;
	
	public PTC(String title, double suggestedPrice, double aliquot, double brutePrice, double aliquotPlusBrute, double discount, double finalPrice, Client client, double contribuition, Date creationDate) {
		this.title = title;
		this.suggestedPrice = suggestedPrice;
		this.aliquot = aliquot;
		this.brutePrice = brutePrice;
		this.aliquotPlusBrute = aliquotPlusBrute;
		this.discount = discount;
		this.finalPrice = finalPrice;
		this.client = client;
		this.contribuition = contribuition;
		this.creationDate = creationDate;
	}

	
	public Date getCreationDate() {
		return this.creationDate;
	}
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public List<Material> getMaterialList() {
		return materialList;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public List<Service> getServiceList() {
		return serviceList;
	}

	public List<Kit> getKitList() {
		return kitList;
	}

	public double getSuggestedPrice() {
		return suggestedPrice;
	}

	public double getAliquot() {
		return aliquot;
	}

	public double getBrutePrice() {
		return brutePrice;
	}

	public double getAliquotPlusBrute() {
		return aliquotPlusBrute;
	}

	public Client getClient() {
		return client;
	}

	public double getContribuition() {
		return contribuition;
	}

	public double getDiscount() {
		return discount;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMaterialList(List<Material> materialList) {
		this.materialList = materialList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public void setServiceList(List<Service> serviceList) {
		this.serviceList = serviceList;
	}

	public void setKitList(List<Kit> kitList) {
		this.kitList = kitList;
	}

	public void setSuggestedPrice(double suggestedPrice) {
		this.suggestedPrice = suggestedPrice;
	}

	public CNPJ getCnpj() {
	    return cnpj;
    }

	public void setCnpj(CNPJ cnpj) {
	    this.cnpj = cnpj;
    }

	public String getRastreabilityCode() {
	    return rastreabilityCode;
    }

	public void setRastreabilityCode(String rastreabilityCode2) {
	    this.rastreabilityCode = rastreabilityCode2;
    }
	
	@Override
	public String toString() {
	    return this.rastreabilityCode + "/" + this.title;
	}

	public int getIsApproved() {
	    return isApproved;
    }

	public void setIsApproved(int isApproved) {
	    this.isApproved = isApproved;
    }


	public int getIdMaster() {
	    return idMaster;
    }


	public void setIdMaster(int idMaster) {
	    this.idMaster = idMaster;
    }


	public void setTitle(String title) {
		this.title = title;
	}


	public void setAliquot(double aliquot) {
		this.aliquot = aliquot;
	}


	public void setBrutePrice(double brutePrice) {
		this.brutePrice = brutePrice;
	}


	public void setAliquotPlusBrute(double aliquotPlusBrute) {
		this.aliquotPlusBrute = aliquotPlusBrute;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public void setContribuition(double contribuition) {
		this.contribuition = contribuition;
	}


	public void setDiscount(double discount) {
		this.discount = discount;
	}


	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}


	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	public void IsCompleted(int isCompleted) {
		this.isCompleted = isCompleted;
	    
    }


	public int getIsCompleted() {
	    
	    return this.isCompleted;
    }
}
