package model;

import java.sql.Date;

public class Vehicle 
{
	private Integer id;
	private String description;
	private String type;
	private String model;
	private String brand;
	private String chassi;
	private String renavan;
	private String placa;
	private Double initialKm;
	private Date lastOilChange;
	private Double oilChangeInterval;
	private Date ipvaDate;
	private Date licenseDate;
	
	public Vehicle(Integer id, String description, String type, String model, String brand, String chassi,
			String renavan, String placa, Double initialKm, Date lastOilChange, Double oilChangeInterval, Date ipvaDate,
			Date licenseDate) {
		super();
		this.id = id;
		this.description = description;
		this.type = type;
		this.model = model;
		this.brand = brand;
		this.chassi = chassi;
		this.renavan = renavan;
		this.placa = placa;
		this.initialKm = initialKm;
		this.lastOilChange = lastOilChange;
		this.oilChangeInterval = oilChangeInterval;
		this.ipvaDate = ipvaDate;
		this.licenseDate = licenseDate;
	}

	public Vehicle() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public String getRenavan() {
		return renavan;
	}

	public void setRenavan(String renavan) {
		this.renavan = renavan;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Double getInitialKm() {
		return initialKm;
	}

	public void setInitialKm(Double initialKm) {
		this.initialKm = initialKm;
	}

	public Date getLastOilChange() {
		return lastOilChange;
	}

	public void setLastOilChange(Date lastOilChange) {
		this.lastOilChange = lastOilChange;
	}

	public Double getOilChangeInterval() {
		return oilChangeInterval;
	}

	public void setOilChangeInterval(Double oilChangeInterval) {
		this.oilChangeInterval = oilChangeInterval;
	}

	public Date getIpvaDate() {
		return ipvaDate;
	}

	public void setIpvaDate(Date ipvaDate) {
		this.ipvaDate = ipvaDate;
	}

	public Date getLicenseDate() {
		return licenseDate;
	}

	public void setLicenseDate(Date licenseDate) {
		this.licenseDate = licenseDate;
	}

	@Override
	public String toString() {
		return  description ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
