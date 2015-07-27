package model;

import java.sql.Date;

;

public class Maintenance {

	private int id;
	private Date dateMaintenance;
	private String descriptionMaintenance;
	private Date expireDate;
	private Equipment equipmentMaintenance;

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateMaintenance() {
		return dateMaintenance;
	}

	public void setDateMaintenance(Date dateMaintenance) {
		this.dateMaintenance = dateMaintenance;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getDescriptionMaintenance() {
		return descriptionMaintenance;
	}

	public void setDescriptionMaintenance(String descriptionMaintenance) {
		this.descriptionMaintenance = descriptionMaintenance;
	}

	public Equipment getEquipmentMaintenance() {
		return equipmentMaintenance;
	}

	public void setEquipmentMaintenance(Equipment equipmentMaintenance) {
		this.equipmentMaintenance = equipmentMaintenance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateMaintenance == null) ? 0 : dateMaintenance.hashCode());
		result = prime
				* result
				+ ((descriptionMaintenance == null) ? 0
						: descriptionMaintenance.hashCode());
		result = prime
				* result
				+ ((equipmentMaintenance == null) ? 0 : equipmentMaintenance
						.hashCode());
		result = prime * result
				+ ((expireDate == null) ? 0 : expireDate.hashCode());
		result = prime * result + id;
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
		Maintenance other = (Maintenance) obj;
		if (dateMaintenance == null) {
			if (other.dateMaintenance != null)
				return false;
		} else if (!dateMaintenance.equals(other.dateMaintenance))
			return false;
		if (descriptionMaintenance == null) {
			if (other.descriptionMaintenance != null)
				return false;
		} else if (!descriptionMaintenance.equals(other.descriptionMaintenance))
			return false;
		if (equipmentMaintenance == null) {
			if (other.equipmentMaintenance != null)
				return false;
		} else if (!equipmentMaintenance.equals(other.equipmentMaintenance))
			return false;
		if (expireDate == null) {
			if (other.expireDate != null)
				return false;
		} else if (!expireDate.equals(other.expireDate))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
