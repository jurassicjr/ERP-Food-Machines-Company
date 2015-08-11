package model;

import java.util.Date;

public class ManualQualityVersion {

	private int id;
	private String filePath; 
	private Date dateUpdate;
	
	/**
	 * Cria o objeto representativo para uma versão de um manual de qualidade 
	 * 
	 * @param id O identificador no banco de dados da versão do manual de qualidade
	 * @param filePath A localização do arquivo do manual de qualidade
	 * @param dateUpdate A data de atualização da versão do manual de qualidade
	 */
	public ManualQualityVersion(int id, String filePath, Date dateUpdate) {
		this.id = id;
		this.filePath = filePath;
		this.dateUpdate = dateUpdate;
	}

	/**
	 * Retorna o identificador no banco de dados da versão do manual de qualidade
	 * 
	 * @return O identificador no banco de dados da versão do manual de qualidade
	 */
	public int getId() {
		return id;
	}

	/**
	 * Retorna a localização do arquivo do manual de qualidade
	 * 
	 * @return A localização do arquivo do manual de qualidade
	 */
	public String getFileName() {
		return filePath;
	}

	/**
	 * Retorna a data de atualização da versão do manual de qualidade
	 * 
	 * @return A data de atualização da versão do manual de qualidade
	 */
	public Date getDateUpdate() {
		return dateUpdate;
	}
}
