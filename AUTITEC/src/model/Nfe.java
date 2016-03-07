package model;

import java.util.Date;

public class Nfe {

	public void setId(int id) {
		this.id = id;
	}

	private int id;
	private String danfe;
	private String exitHour;
	private String model;
	private String fiscalNumber;
	private String freightCNPJ;
	private String companyName;
	private String street;
	private String danfeSerial;
	private String anttCode;
	
	private Date emissionDate;
	private Date exitDate;
	private CFOPExit cfop;
	
	private Supplier supplier;

	private int freight;

	private State state;
	private City city;
	
	private double totalPisTax;
	private double totalIpiTax;
	private double totalIcmsTax;
	private double totalCofinsTax;
	private double totalNfeValue;	
	private double totalCalBase;
	
	
	
	public Nfe(String danfe, String exitHour, String model, Date emissioDate, Date exiDate, String danfeSerial, String fiscalNumber, CFOPExit cfop, Supplier supplier, int freight) {
		this.danfe = danfe;
		this.exitHour = exitHour;
		this.model = model;
		emissionDate = emissioDate;
		exitDate = exiDate;
		this.danfeSerial = danfeSerial;
		this.fiscalNumber = fiscalNumber;
		this.cfop = cfop;
		this.supplier = supplier;
		this.freight = freight;
	}
	
	public int getId() {
		return id;
	}


	public String getDanfe() {
		return danfe;
	}


	public String getExitHour() {
		return exitHour;
	}


	public String getModel() {
		return model;
	}


	public Date getEmissionDate() {
		return emissionDate;
	}


	public Date getExitDate() {
		return exitDate;
	}


	public String getDanfeSerial() {
		return danfeSerial;
	}


	public CFOPExit getCfop() {
		return cfop;
	}


	public String getFiscalNumber() {
		return fiscalNumber;
	}


	public Supplier getSupplier() {
		return supplier;
	}


	public int getFreight() {
		return freight;
	}


	public String getCompanyName() {
		return companyName;
	}


	public String getStreet() {
		return street;
	}


	public State getState() {
		return state;
	}


	public City getCity() {
		return city;
	}


	public String getAntt_code() {
		return anttCode;
	}

	public String getFreightCNPJ() {
	    return freightCNPJ;
    }

	public void setFreightCNPJ(String freightCNPJ) {
	    this.freightCNPJ = freightCNPJ;
    }

	public double getTotalPisTax() {
	    return totalPisTax;
    }

	public void setTotalPisTax(double totalPisTax) {
	    this.totalPisTax = totalPisTax;
    }

	public double getTotalIpiTax() {
	    return totalIpiTax;
    }

	public void setTotalIpiTax(double totalIpiTax) {
	    this.totalIpiTax = totalIpiTax;
    }

	public double getTotalIcmsTax() {
	    return totalIcmsTax;
    }

	public void setTotalIcmsTax(double totalIcmsTax) {
	    this.totalIcmsTax = totalIcmsTax;
    }

	public double getTotalCofinsTax() {
	    return totalCofinsTax;
    }

	public void setTotalCofinsTax(double totalCofinsTax) {
	    this.totalCofinsTax = totalCofinsTax;
    }

	public double getTotalNfeValue() {
	    return totalNfeValue;
    }

	public void setTotalNfeValue(double totalNfeValue) {
	    this.totalNfeValue = totalNfeValue;
    }

	public double getTotalCalBase() {
	    return totalCalBase;
    }

	public void setTotalCalBase(double totalCalBase) {
	    this.totalCalBase = totalCalBase;
    }

	@Override
	public String toString() {
    	return this.danfe;
	}

}
