package model;

public class ClientPropertiesMaterial {

	private int id;
	private Material material;
	private int ammount;
	private ClientProperties clientProrpeties;
	private String exitFiscalNote;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	public int getAmmount() {
		return ammount;
	}
	public void setAmmount(int ammount) {
		this.ammount = ammount;
	}
	public ClientProperties getClientProrpeties() {
		return clientProrpeties;
	}
	public void setClientProrpeties(ClientProperties clientProrpeties) {
		this.clientProrpeties = clientProrpeties;
	}
	
	@Override
	public String toString() {
		return material.getName();
	}
	public String getExitFiscalNote() {
	    return exitFiscalNote;
    }
	public void setExitFiscalNote(String exitFiscalNote) {
	    this.exitFiscalNote = exitFiscalNote;
    }
}
