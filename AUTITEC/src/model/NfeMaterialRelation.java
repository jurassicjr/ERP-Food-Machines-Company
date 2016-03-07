package model;



public class NfeMaterialRelation{

	private int id;
	private Material material;
	private Nfe nfe;	
	private double ammount;
	private double totalValue;
	private double unValue;
	private CFOPExit cfop;
	private int typeIndex;
	private String cstIcms;
	private String cstCofins;
	private String cstIpi;
	private String cstPis;
	private double finalCost;
	private double unFinalCost;
	private double salesPercent;  
	private double finalSalesValue;
	double aliquotPis;
	double aliquotIpi;
	double aliquotCofins;
	double aliquotIcms;
	double icmsTotalValue;
	double pisTotalValue;
	double ipiTotalValue;
	double cofinsTotalValue;
	double calBase;
	

	public NfeMaterialRelation(Material material, double totalValue, double unValue, CFOPExit cfop, int typeIndex, String cstIcms,
			String cstPis, String cstCofins, String cstIpi, double finalCost, double unFinalCost, double salesPercent,
			double finalSalesValue, double ammount, Nfe nfe, double aliquotPis, double aliquotIpi, double aliquotCofins,
			double aliquotIcms, double icmsTotalValue, double pisTotalValue, double ipiTotalValue, double cofinsTotalValue, double calBase){
		this.material = material;
		this.totalValue = totalValue;
		this.unValue = unValue;
		this.cfop = cfop;
		this.typeIndex = typeIndex;
		this.cstIcms = cstIcms;
		this.cstPis = cstPis;
		this.cstCofins = cstCofins;
		this.cstIpi = cstIpi;
		this.finalCost = finalCost;
		this.unFinalCost = unFinalCost;
		this.salesPercent = salesPercent;
		this.finalSalesValue = finalSalesValue;
		this.ammount = ammount;
		this.nfe = nfe;
		this.aliquotPis = aliquotPis;
		this.aliquotIpi = aliquotIpi;
		this.aliquotCofins = aliquotCofins;
		this.aliquotIcms = aliquotIcms;
		this.icmsTotalValue = icmsTotalValue;
		this.pisTotalValue = pisTotalValue;
		this.ipiTotalValue = ipiTotalValue;
		this.cofinsTotalValue = cofinsTotalValue;
		this.calBase = calBase;
	
	}
 
	public int getId() {
		return id;
	}

	public Material getMaterial() {
		return material;
	}

	public double getTotalValue() {
		return totalValue;
	}

	public double getUnValue() {
		return unValue;
	}

	public CFOPExit getCfop() {
		return cfop;
	}

	public int getTypeIndex() {
		return typeIndex;
	}

	public String getCstIcms() {
		return cstIcms;
	}

	public String getCstPis() {
		return cstPis;
	}

	public String getCstCofins() {
		return cstCofins;
	}

	public String getCstIpi() {
		return cstIpi;
	}

	public double getFinalCost() {
		return finalCost;
	}

	public double getUnFinalCost() {
		return unFinalCost;
	}

	public double getSalesPercent() {
		return salesPercent;
	}

	public double getFinalSalesValue() {
		return finalSalesValue;
	}

	public double getAmmount() {
		return ammount;
	}

	public Nfe getNfe() {
		return nfe;
	}
	
	@Override
	public String toString() {
		return this.material.getName();
	}
	
	@Override
	public boolean equals(Object obj) {
	    NfeMaterialRelation nfe = (NfeMaterialRelation) obj;
	    if(nfe.getMaterial().getName().equalsIgnoreCase(this.getMaterial().getName()))return true;
	    else return false;
	}

	public double getAliquotPis() {
		return aliquotPis;
	}

	public double getAliquotIpi() {
		return aliquotIpi;
	}

	public double getAliquotCofins() {
		return aliquotCofins;
	}

	public double getAliquotIcms() {
		return aliquotIcms;
	}

	public double getIcmsTotalValue() {
		return icmsTotalValue;
	}

	public double getPisTotalValue() {
		return pisTotalValue;
	}

	public double getIpiTotalValue() {
		return ipiTotalValue;
	}

	public double getCofinsTotalValue() {
		return cofinsTotalValue;
	}

	public double getCalBase() {
		return calBase;
	}

	
}
