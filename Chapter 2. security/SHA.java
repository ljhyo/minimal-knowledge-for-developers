import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class SHA {

    public String hash(String plainText) throws Exception {

        byte[] hashValue = null;

        // SHA-256 해시함수 사용을 위한 객체 생성
        MessageDigest messageDdigest = MessageDigest.getInstance("SHA-256");

        // 안전한 솔트(난수)값 생성을 위한 랜덤함수(SecureRandom) 객체 생성
        SecureRandom secureRandom = new SecureRandom();

        byte[] salt = new byte[32];

        // 32바이트 길이의 솔트(난수)값 생성
        secureRandom.nextBytes(salt);

        // 해시 대상 입력 문자열에 솔트값 추가
        messageDdigest.update(salt);

        // 입력 문자열(plainText + salt)를 해시값으로 반환
        hashValue = messageDdigest.digest(plainText.getBytes());

        // 해시값을 16진수로 변환하여 반환
        return new BigInteger(1, hashValue).toString(16);
    }
}
