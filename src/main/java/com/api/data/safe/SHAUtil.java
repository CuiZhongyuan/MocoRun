package com.api.data.safe;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 摘要算法SHA
 */
public class SHAUtil {
	/**
	 * sha1
	 * @param str
	 * @return
	 */
	public final static String encode(String str){
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		messageDigest.update(str.getBytes());
		return Base64.getEncoder().encodeToString(messageDigest.digest());
	}

}
