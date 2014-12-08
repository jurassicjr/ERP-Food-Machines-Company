package model;

import java.util.Date;

/**
 * Representa uma versão de uma norma técnica
 */
public class TechnicalStandardVersion {
	
	private int id;
	private String filePath; 
	private Date dateUpdate;
	
	/**
	 * Cria o objeto representativo para uma versão de uma norma técnica 
	 * 
	 * @param id O identificador no banco de dados da versão da norma técnica
	 * @param filePath A localização do arquivo da norma técnica
	 * @param dateUpdate A data de atualização da versão da norma técnica
	 */
	public TechnicalStandardVersion(int id, String filePath, Date dateUpdate) {
		this.id = id;
		this.filePath = filePath;
		this.dateUpdate = dateUpdate;
	}

	/**
	 * Retorna o identificador no banco de dados da versão da norma técnica
	 * 
	 * @return O identificador no banco de dados da versão da norma técnica
	 */
	public int getId() {
		return id;
	}

	/**
	 * Retorna a localização do arquivo da norma técnica
	 * 
	 * @return A localização do arquivo da norma técnica
	 */
	public String getFileName() {
		return filePath;
	}

	/**
	 * Retorna a data de atualização da versão da norma técnica
	 * 
	 * @return A data de atualização da versão da norma técnica
	 */
	public Date getDateUpdate() {
		return dateUpdate;
	}
	
}
