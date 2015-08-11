package model;

import java.util.ArrayList;
import java.util.Date;

public class ManualQuality {

	private int id;
	private String manualQuality;
	private ArrayList<ManualQualityVersion> versions;
	
	/**
	 * Cria o objeto que representa um manual de qualidade
	 * 
	 * @param id O id no banco de dados do manual de qualidade
	 * @param manualQuality O nome do manual de qualidade
	 */
	public ManualQuality(int id, String manualQuality) {
		
		this.id = id;
		this.manualQuality = manualQuality;
		
		versions = new ArrayList<>();
	}

	/**
	 * Retorna o ID do manual de qualidade
	 * 
	 * @return O ID do manual de qualidade
	 */
	public int getId() {
		return id;
	}

	/**
	 * Retorna o nome do manual de qualidade
	 * 
	 * @return O nome do manual de qualidade
	 */
	public String getManualQuality() {
		return manualQuality;
	}
	
	/**
	 * Adiciona uma versão do manual de qualidade
	 * 
	 * @param version A versão do manual de qualidade
	 */
	public void addVersion(ManualQualityVersion version) {
		versions.add(version);
	}
	
	/**
	 * Retorna as versões do manual de qualidade
	 * 
	 * @return As versões do manual de qualidade
	 */
	public ArrayList<ManualQualityVersion> getVersions() {
		return versions;
	}
	
	/**
	 * Retorna a última atualização do manual de qualidade
	 * 
	 * @return A última atualização do manual de qualidade
	 */
	public ManualQualityVersion getLastUpdate() {
		
		if(versions == null || versions.size() == 0) return null;
		
		Date last = versions.get(0).getDateUpdate();
		ManualQualityVersion lastVersion = versions.get(0); 
		
		for(int i = 1; i < versions.size(); ++i) {
			
			if(last.before(versions.get(i).getDateUpdate())) {
				last = versions.get(i).getDateUpdate(); 
				lastVersion = versions.get(i);
			}
			
		}
		
		return lastVersion;
		
	}

	@Override
	public String toString() {
		return manualQuality;
	}

}
