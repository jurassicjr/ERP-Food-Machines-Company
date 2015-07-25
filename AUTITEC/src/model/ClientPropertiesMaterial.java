package model;

public class ClientPropertiesMaterial {

	private int id;
	private Material material;
	private int ammount;
	private ClientProrpeties clientProrpeties;
	
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
	public ClientProrpeties getClientProrpeties() {
		return clientProrpeties;
	}
	public void setClientProrpeties(ClientProrpeties clientProrpeties) {
		this.clientProrpeties = clientProrpeties;
	}
}
