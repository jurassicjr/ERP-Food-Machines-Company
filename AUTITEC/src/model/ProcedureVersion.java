package model;

import java.util.Date;

public class ProcedureVersion {

	private int id;
	private String filePath; 
	private Date dateUpdate;
	
	/**
	 * Cria o objeto representativo para uma versão de um procedimento. 
	 * 
	 * @param id O identificador no banco de dados da versão do procedimento
	 * @param filePath A localização do arquivo do procedimento
	 * @param dateUpdate A data de atualização da versão do procedimento.
	 */
	public ProcedureVersion(int id, String filePath, Date dateUpdate) {
		this.id = id;
		this.filePath = filePath;
		this.dateUpdate = dateUpdate;
	}

	/**
	 * Retorna o identificador no banco de dados da versão do procedimento
	 * 
	 * @return O identificador no banco de dados da versão do procedimento
	 */
	public int getId() {
		return id;
	}

	/**
	 * Retorna a localização do arquivo do procedimento
	 * 
	 * @return A localização do arquivo do procedimento
	 */
	public String getFileName() {
		return filePath;
	}

	/**
	 * Retorna a data de atualização da versão do procedimento
	 * 
	 * @return A data de atualização da versão do procedimento
	 */
	public Date getDateUpdate() {
		return dateUpdate;
	}
	
}
