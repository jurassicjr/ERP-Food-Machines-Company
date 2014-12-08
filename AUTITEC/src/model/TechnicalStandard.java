package model;

import java.util.ArrayList;
import java.util.Date;


public class TechnicalStandard {
	
	private int id;
	private String technicalStandard;
	private ArrayList<TechnicalStandardVersion> versions;
	
	/**
	 * Cria o objeto que representa uma Norma Técnica
	 * 
	 * @param id O id no banco de dados da norma técnica
	 * @param technicalStandard O nome da norma técnica
	 */
	public TechnicalStandard(int id, String technicalStandard) {
		
		this.id = id;
		this.technicalStandard = technicalStandard;
		
		versions = new ArrayList<>();
	}

	/**
	 * Retorna o ID da norma técnica
	 * 
	 * @return O ID da norma técnica
	 */
	public int getId() {
		return id;
	}

	/**
	 * Retorna o nome da norma técnica
	 * 
	 * @return O nome da norma técnica
	 */
	public String getTechnicalStandard() {
		return technicalStandard;
	}
	
	/**
	 * Adiciona uma versão da Norma Técnica
	 * 
	 * @param version A versão da Norma Técnica
	 */
	public void addVersion(TechnicalStandardVersion version) {
		versions.add(version);
	}
	
	/**
	 * Retorna as versões da Norma Técnica
	 * 
	 * @return As versões da Norma Técnica
	 */
	public ArrayList<TechnicalStandardVersion> getVersions() {
		return versions;
	}
	
	/**
	 * Retorna a última atualização de norma técnica
	 * 
	 * @return A última atualização de norma técnica
	 */
	public TechnicalStandardVersion getLastUpdate() {
		
		if(versions == null || versions.size() == 0) return null;
		
		Date last = versions.get(0).getDateUpdate();
		TechnicalStandardVersion lastVersion = versions.get(0); 
		
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
		return technicalStandard;
	}

}
