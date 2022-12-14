package be.stackoverflow.security;

import be.stackoverflow.user.entity.User;
import be.stackoverflow.user.service.UserService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
/*
 * jwt 토큰을 사용하기 위한 메서드
 */
@Component
@RequiredArgsConstructor
@CrossOrigin(origins = "http://pre-19.s3-website.ap-northeast-2.amazonaws.com" , exposedHeaders = {"Authorization","Refresh"} )
public class JwtTokenizer {
    //해당 정보들은 yml파일에 저장하기 JWT의 기본정보 엑세스토큰과 리프레쉬토큰
    @Getter
    @Value("${jwt.secret-key}")
    private String secretKey;

    @Getter
    @Value("${jwt.access-token-expiration-minutes}")
    private int accessToken;        // 엑세스 토큰

    @Getter
    @Value("${jwt.refresh-token-expiration-minutes}")
    private int refreshToken;          // 리프레쉬 토큰


    /*
     * 보안키 비번을 암호화
     */

    public String makingSecretKey(String secretKey) {
        return Encoders.BASE64.encode(secretKey.getBytes(StandardCharsets.UTF_8));
    }


    /**
     * 1. 인증된 사용자에게 JWT를 최초로 발급해주기 위한 JWT 생성 메서드입니다.
     */
    public String generateAccessToken(Map<String, Object> claims,
                                      String subject,
                                      Date expiration,
                                      String base64EncodedSecretKey) {

        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setClaims(claims) //custom claims = 인증된 사용자에 대한 정보담기
                .setSubject(subject) //jwt 제목추가
                .setIssuedAt(Calendar.getInstance().getTime()) //발행일자가 적힌 정보 java.util.Date 타입
                .setExpiration(expiration) // jwt 만료일 지정
                .signWith(key) //비밀키 객체 설정 위에서 64로 인코딩한 비번
                .compact(); //데이터 직렬화
    }

    // 기한 만료되면 사용되는 메서드
    public String generateRefreshToken(String subject, Date expiration, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    /**
     * 내용 검증을 할때  key값과 인증완료된 토큰값을 넘김
     */
    public Jws<Claims> getClaims(String jws, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);
        return claims;
    }

    /**
     * jwt 검증 메서드
     */
    public Jws<Claims> verifySignature(String jws) {

        Key key = getKeyFromBase64EncodedKey(makingSecretKey(secretKey));

        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);
        return claimsJws;
    }

    /*
     * 만료기한 얻는 메서드
     */
    public Date getTokenExpiration(int expirationMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expirationMinutes);
        Date expiration = calendar.getTime();

        return expiration;
    }

    /*
     * 비밀번호 제작 메서드
     */
    private Key getKeyFromBase64EncodedKey(String base64EncodedSecretKey) {

        byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return key;
    }

    public String getEmailWithToken(HttpServletRequest request) {
        //HEADER에 있는 복호화된 값을 가져옴
        String authorization = request.getHeader("Authorization");
        String[] split = authorization.split("\\ "); //barer asdasdasd.adasdasd.asdasdasd 이런식으로 되어있어서 나눈다.

        try {
            Claims body = Jwts.parserBuilder().setSigningKey(getKeyFromBase64EncodedKey(makingSecretKey(secretKey)))
                    .build().parseClaimsJws(split[1]).getBody(); //값을 넣어서 되돌려받는다.
            return  (String) body.get("userEmail"); //우리가 그토록 원하는 이메일을 돌려받는다.

        } catch (Exception e) {
            throw e;
        }
    }


}
