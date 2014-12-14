package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Criar códigos hash em MD5
 */
public class MD5 {
	
	/**
	 * Retorna o código MH5 de 32 caracteres para a string passada como parametro.
	 *  
	 * @param s A string que se deseja o código MD5
	 * 
	 * @return O código MD5 da string passada por parâmetro
	 */
	public static String getMD5Code(String s) {
		
		MessageDigest messageDigest;
		
		try {
			
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(s.getBytes(), 0, s.length());
			
			BigInteger i = new BigInteger(1, messageDigest.digest());
			
			return String.format("%1$032X", i);
			
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
}