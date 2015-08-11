package model;

import java.util.ArrayList;
import java.util.Date;

public class Procedure {

	private int id;
	private String procedure;
	private ArrayList<ProcedureVersion> versions;
	
	/**
	 * Cria o objeto que representa uma Norma Técnica
	 * 
	 * @param id O id no banco de dados da norma técnica
	 * @param procedure O nome da norma técnica
	 */
	public Procedure(int id, String procedure) {
		
		this.id = id;
		this.procedure = procedure;
		
		versions = new ArrayList<>();
	}

	/**
	 * Retorna o ID do procedimento.
	 * 
	 * @return O ID do procedimento.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Retorna o nome do procedimento
	 * 
	 * @return O nome do procedimento
	 */
	public String getProcedure() {
		return procedure;
	}
	
	/**
	 * Adiciona uma versão do procedimento.
	 * 
	 * @param version A versão do procedimento.
	 */
	public void addVersion(ProcedureVersion version) {
		versions.add(version);
	}
	
	/**
	 * Retorna as versões do procedimento
	 * 
	 * @return As versões do procedimetno
	 */
	public ArrayList<ProcedureVersion> getVersions() {
		return versions;
	}
	
	/**
	 * Retorna a última atualização de norma técnica
	 * 
	 * @return A última atualização de norma técnica
	 */
	public ProcedureVersion getLastUpdate() {
		
		if(versions == null || versions.size() == 0) return null;
		
		Date last = versions.get(0).getDateUpdate();
		ProcedureVersion lastVersion = versions.get(0); 
		
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
		return procedure;
	}

}
