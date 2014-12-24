package model;

/**
 * Representação de um cnpj
 */
public class CNPJ {
	
	private int id;
	private String cnpj;
	private String corporateName;
	
	/**
	 * Cria o objeto de um cnpj
	 * 
	 * @param id O id no banco de dados do cnpj
	 * @param cnpj O número sem formatação do cnpj
	 * @param corporateName A razão social associada ao cnpj
	 */
	public CNPJ(int id, String cnpj, String corporateName) {
		this.id = id;
		this.cnpj = cnpj;
		this.corporateName = corporateName;
	}

	/**
	 * Retorna o id vinculado ao cnpj no banco de dados
	 * 
	 * @return O id vinculado ao cnpj no banco de dados
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Retorna o número sem formatação do cnpj
	 * 
	 * @return O número sem formatação do cnpj
	 */
	public String getCnpj() {
		return cnpj;
	}
	
	/**
	 * Retorna a razão social da empresa
	 * 
	 * @return A razão social da empresa
	 */
	public String getCorporateName() {
		return corporateName;
	}
	
	/**
	 * Retorna o CNPJ formatado com pontos e traços
	 * 
	 * @return O CNPJ formatado com pontos e traços
	 */
	public String getFormattedCnpj() {
		return cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." + cnpj.substring(5, 8) +
				"/" + cnpj.substring(8, 12) + "-" + cnpj.substring(12, 14);		
	}
	
	@Override
	public String toString() {
		return getFormattedCnpj() + " - " + corporateName;
	}
	
}
