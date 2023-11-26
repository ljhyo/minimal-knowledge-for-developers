import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class RSAEncryption {

    private PrivateKey privateKey = null;
    private PublicKey publicKey = null;

    public RSAEncryption() {
	this.makeKey();
    }

    // 비대칭 키 생성 메소드
    public KeyPair makeKey() {

	KeyPairGenerator keyPairGenerator = null;
	KeyPair keyPair = null;

	try {
	    // 비대칭 키 생성을 위한 객체 생성
	    keyPairGenerator = KeyPairGenerator.getInstance("RSA");
	    // RSA 알고리즘의 비트 수를 2048비트로 설정
	    keyPairGenerator.initialize(2048);
	    // 비대칭 키(공개 키, 비밀 키) 생성
	    keyPair = keyPairGenerator.generateKeyPair();
	    // 비밀 키 변수 할당
	    privateKey = keyPair.getPrivate();
	    // 공개 키 변수 할당
	    publicKey = keyPair.getPublic();
	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	}
	return keyPair;
    }

    // 암호화 함수
    public String encrypt(String plainText) throws Exception {

	byte[] cipherText = null;

	// 암호화 객체 생성
	Cipher cipher = Cipher.getInstance("RSA");
	// 암호화 모드, 공개 키를 입력하여 암호화 객체 초기화
	cipher.init(Cipher.ENCRYPT_MODE, publicKey);
	// 데이터 암호화 수행
	cipherText = cipher.doFinal(plainText.getBytes());

	// Base64로 인코딩하여 암호문 반환
	return Base64.getEncoder().encodeToString(cipherText);
    }

    // 복호화 함수
    public String decrypt(String cipherText) throws Exception {
	
	byte[] plainText = null;
	
	// 암호화 객체 생성
	Cipher cipher = Cipher.getInstance("RSA");
	
	// 복호화 모드, 비밀 키를 입력하여 암호화 객체 초기화
	cipher.init(Cipher.DECRYPT_MODE, privateKey);
	
	// 데이터 복호화 수행
	plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
	
	// 바이트 형식으로 복호화된 데이터를 문자열 형식으로 변환하여 반환
	return new String(plainText);
    }
}