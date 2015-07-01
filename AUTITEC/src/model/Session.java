package model;

/**
 * Define uma sessão de utilização do sistema mediante login, armazenando o usuário ativo
 */
public class Session {
	
	private User user;
	
	private Session() {}
	
	/**
	 * Cria uma instância da sessão
	 */
	private static class SessionHolder { 
		private final static Session instance = new Session();
	}
	
	/**
	 * Retorna a instância da sessão
	 * @return A instãncia da sessão
	 */
	public static Session getInstance() {
		return SessionHolder.instance;
	}
	
	/**
	 * Retorna o usuário ativo da sessão
	 * @return O usuário ativo da sessão
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * Define o usuário ativo da sessão
	 * 
	 * @param User O usuário da sessão
	 */
	public void setUser(User user) {
		this.user = user;
	}
	public boolean havePermission(String menuName)
	{
			
	 return this.getUser().getPermissions().contains(menuName);
	 
	}

}