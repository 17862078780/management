import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: 孟德坤
 * @Date: 2019/4/23 15:47
 */
public class JwtTest {
    /**
     *
     * 功能描述: 用于生成token
     *
     * @return:
     * @auther: 孟德坤
     * @date: 2019/4/23 15:52
     */
@Test
public void test (){
    String str = CreateJwt();

    ParseJwt(str);
}


    public String CreateJwt() {
        long now = System.currentTimeMillis();//当前时间
        long exp = now + 1000*60;//过期时间为1分钟
        JwtBuilder builder= Jwts.builder().setId("888")
                .setSubject("小白")
                .setIssuedAt(new Date())//签发时间
                .signWith(SignatureAlgorithm.HS256,"token")//签名秘钥
                .setExpiration(new Date(exp))
                //自定义自定义claims
                .claim("roles","admin")
                .claim("logo","logo.png");
        System.out.println( builder.compact() );
        System.out.println("-------------------------------");
        return builder.compact();
    }
    public void ParseJwt(String jwtToken){
        Claims claims = Jwts.parser().setSigningKey("token").parseClaimsJws(jwtToken).getBody();
        System.out.println("id:"+claims.getId());
        System.out.println("subject:"+claims.getSubject());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy‐MM‐dd hh:mm:ss");
        System.out.println("签发时间:"+sdf.format(claims.getIssuedAt()));
        System.out.println("过期时间:"+sdf.format(claims.getExpiration()));
        System.out.println("当前时间:"+sdf.format(new Date()) );
        System.out.println("roles:"+claims.get("roles"));
        System.out.println("logo:"+claims.get("logo"));
    }
}
