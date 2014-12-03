package model;

import java.util.ArrayList;


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

	@Override
	public String toString() {
		return technicalStandard;
	}

}
