import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESEncryption {

    private SecretKeySpec secretKey;
    private byte[] initialVector;

    // 대칭 키 암호화를 위한 메소드
    public String encrypt(String plainText, String secretString) throws Exception {

	byte[] sha256 = null;
	byte[] cipherText = null;

	// secretString에 대한 해시 값 생성
	sha256 = MessageDigest.getInstance("SHA-256").digest(secretString.getBytes("UTF-8"));

	// 해시 값을 16바이트 배열로 나누어 저장
	sha256 = Arrays.copyOf(sha256, 16);

	// 해시 값과 AES 알고리즘을 이용하여 비밀 키 생성
	secretKey = new SecretKeySpec(sha256, "AES");

	// 첫 번째 블록 암호화를 위해 난수 값을 이용하여 초기화 벡터 생성
	initialVector = new byte[16];
	SecureRandom random = new SecureRandom();
	random.nextBytes(initialVector);

	/*
	 * 암호화 객체 생성 
	 *  - 암호 알고리즘: AES 
	 *  - 블록 암호화 운영 모드: CBC 
	 *  - 패딩: PKCS5Padding
	 */
	Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	// 암호화 모드, 대칭 키, 초기화 벡터 값을 입력하여 암호화 객체 초기화
	cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(initialVector));

	// 데이터 암호화 수행
	cipherText = cipher.doFinal(plainText.getBytes("UTF-8"));
	// Base64로 인코딩하여 암호문 반환
	return Base64.getEncoder().encodeToString(cipherText);
    }

    // 대칭 키 복호화 함수
    public String decrypt(String cipherText, SecretKeySpec secretKey) throws Exception {

	byte[] plainText = null;

	// 암호화 객체 생성
	Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

	// 암호화 모드, 대칭 키, 초기화 벡터 값을 입력하여 암호화 객체 초기화
	// secretKey(대칭 키)와 initialVector(초기화 벡터)는 암호화에
	// 사용한 값과 동일한 값으로 설정
	cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(initialVector));
	// 데이터 복호화 수행
	plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
	// 바이트 형식으로 복호화된 데이터를 문자열 형식으로 변환하여 반환
	return new String(plainText);
    }

    // 대칭 키 및 암호 알고리즘 조회
    public SecretKeySpec getSecretKey() {
	return secretKey;
    }
}