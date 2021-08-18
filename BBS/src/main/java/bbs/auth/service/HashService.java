package bbs.auth.service;

import java.security.MessageDigest;
import java.security.SecureRandom;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.auth.model.User;
import bbs.jdbc.ConnectionProvider;
import bbs.member.dao.MemberDao;
import bbs.member.model.Member;

public class HashService {

	private static final int SALT_SIZE = 16;
	private MemberDao memberDao = new MemberDao();

	public User login(String id, String password) {

		try (Connection conn = ConnectionProvider.getConnection()) {
			Member member = memberDao.selectById(conn, id);
			if (member == null) {
				throw new LoginFailException();
			}
			if (member.matchPassword(password) == false) {
				throw new LoginFailException();
			}
			return new User(member.getId(), member.getName(), member.getEmail());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 스트링을 16진수로 변경
	public static String stringToHex(String tempPassword) {
		String result = "";

		for (int i = 0; i < tempPassword.length(); i++) {
			result += String.format("%02X ", (int) tempPassword.charAt(i));
		}

		return result;
	}

	public static byte[] hexStringToByteArray(String hexString) {

		String[] hexString_split = hexString.split(" ");

		byte[] hexBytes = new byte[hexString_split.length];

		for (int i = 0; i < hexBytes.length; i++) {

			hexBytes[i] = (byte) Integer.parseInt(hexString_split[i], 16);

		}

		return hexBytes;

	}

	// 바이트를 16진수로 변경
	private static String byteToString(byte[] temp) {
		StringBuilder sb = new StringBuilder();
		for (byte a : temp) {
			sb.append(String.format("%02x", a));
		}
		return sb.toString();
	}

	// SALT값 생성
	public static String setSalt(String id) throws Exception {
		SecureRandom sr = new SecureRandom();
		byte[] temp = new byte[SALT_SIZE];
		sr.nextBytes(temp);

		return byteToString(temp);
	}

	// 해싱
	public static String Hashing(byte[] hexPw, String Salt) throws Exception {

		MessageDigest md = MessageDigest.getInstance("SHA-256");

		for (int i = 0; i < 5; i++) {
			String temp = byteToString(hexPw) + Salt; // 암호와 솔트를 합침
			md.update(temp.getBytes()); // temp의 문자열을 해싱해서 md에 저장
			hexPw = md.digest(); // md객체의 다이제스트를 얻어 암호 갱신
		}

		return byteToString(hexPw);
	}

}
