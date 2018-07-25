package com.xdhuxc.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class AESUtils {

	/* 1、指定DES加密解密所用的密钥 */
	private static Key key;
	private static String KEY_STR = "yht_haguan";
	private static final String AESTYPE = "AES/ECB/PKCS5Padding";
	private static final String AES = "AES";

	static {
		try {
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(KEY_STR.getBytes());
			KeyGenerator generator = KeyGenerator.getInstance("AES");
			generator.init(secureRandom);
			key = generator.generateKey();
			generator = null;
		} catch (NoSuchAlgorithmException e) {
		}
	}

	/* 2、对字符串进行AES加密，返回BASE64编码的加密字符串 */
	public static String encrypt(String src/* 明文 */) {
		try {
			byte[] src_byte = src.getBytes(StandardCharsets.UTF_8);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] final_byte = cipher.doFinal(src_byte);
			return Base64Util.encode(final_byte);
		} /*
		   * catch (UnsupportedEncodingException e) { log.info(e.getMessage(), e); } catch
		   * (NoSuchAlgorithmException e) { log.info(e.getMessage(), e); } catch
		   * (NoSuchPaddingException e) { log.info(e.getMessage(), e); }
		   */catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static String encrypt(String pwd, String secretkey) {
		try {
			Key key = getKey(secretkey);
			byte[] src_byte = pwd.getBytes(StandardCharsets.UTF_8);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] final_byte = cipher.doFinal(src_byte);
			return Base64Util.encode(final_byte);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/* 3、对BASE64编码的加密字符串进行解密，返回解密后的字符串 */
	public static String decrypt(String src/* 密文 */) {
		try {
			byte[] src_byte = Base64Util.decode(src);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] decrypt_byte = cipher.doFinal(src_byte);
			return new String(decrypt_byte, StandardCharsets.UTF_8);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private static Key getKey(String strkey) {
		Key key = null;
		try {
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(strkey.getBytes());
			KeyGenerator generator;
			generator = KeyGenerator.getInstance("AES");
			generator.init(secureRandom);
			key = generator.generateKey();
			generator = null;
		} catch (NoSuchAlgorithmException e) {
		}
		return key;
	}

}
