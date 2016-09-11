package disono.webmons.com.utilities.helpers.Encryption;

import java.util.Date;

import disono.webmons.com.clean_architecture.domain.models.MeModel;
import disono.webmons.com.clean_architecture.storage.Configurations;
import disono.webmons.com.utilities.helpers.WBSecurity;
import disono.webmons.com.utilities.helpers.WBTime;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/1/2016 5:06 PM
 */
public class JWT {

    /**
     * Generate token
     *
     * @return
     */
    public static String generateToken() {
        MeModel meModel = new MeModel();

        if (meModel.check()) {
            String sub = Integer.toString(meModel.single().primary_id);
            long currentTime = WBTime.unixTimeStamp();

            Date nbf = WBTime.minusMinuteDate(Configurations.envInt("JWT_Nbf"));
            Date iat = new Date(currentTime);
            Date exp = WBTime.addMinuteDate(Configurations.envInt("JWT_Exp"));

            String jti = WBSecurity.MD5("jti." + "." + sub + "." + Long.toString(currentTime));

            return Jwts.builder()
                    // subject (sub)
                    .setSubject(sub)

                    // not before (nbf)
                    .setNotBefore(nbf)
                    // issued at (iat)
                    .setIssuedAt(iat)
                    // expiry (exp)
                    .setExpiration(exp)

                    // JWT Id (jti)
                    // unique identifier for the token (md5 of the sub and iat claims)
                    .setId(jti)

                    .signWith(SignatureAlgorithm.HS256, WBSecurity.encodeBase64(meModel.single().secret_key))
                    .compact();
        }

        return null;
    }
}
