package model;

public class Material {
	private int id;
	private String name;
	private int ammount;
	private String descrition;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmmount() {
		return ammount;
	}

	public void setAmmount(int quantidade) {
		this.ammount = quantidade;
	}

	public String getDescrition() {
		return descrition;
	}

	public void setDescrition(String descricao) {
		this.descrition = descricao;
	}
	@Override
	public String toString() {
	    return this.name;
	}
}
