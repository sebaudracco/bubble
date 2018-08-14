package com.oneaudience.sdk.p134b;

import android.util.Base64;
import com.oneaudience.sdk.p135c.C3833d;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;

final class C3813a {
    private static final String f9164a = C3813a.class.getSimpleName();
    private static final C3813a f9165c = new C3813a(new C38121());
    private RSAPublicKey f9166b;

    interface C3811a {
        RSAPublicKey mo6806a();
    }

    static class C38121 implements C3811a {
        private final String f9162a = "B76D0E90467ECC6FC2E609149E1C83F53E1CC1B9FE43FD9A2B3938DAF9AED2E878C2B67E3AFC84E5E650C1B18B544ADE0A3BDEC87FAD1028774330191C0D675578B89D264E63E4E1819952962D33C47CEB154E24161980C8F75AF7755EDC387A1F96CD1222D9880B08D48BADCAC6FD20B60875820619AE4FA767B64FD63D7FBE916417ADCAFAABA925BAEC0DE0B52EA8C7178985AEEFE0F3B26D554131C513EAD5DD389C07E58E4D7C6120360C1F5542BF9F944094CE84A1D53613F91C0B97E18B28B12197FEE941C9E58791CF443DCA05605807E87D787D4A6361BFD1DF8CE2C51F5CB1832276CECEEAA0ED689525F60C55AF07438738EF94E925357D1D5C8D";
        private final String f9163b = "010001";

        C38121() {
        }

        public RSAPublicKey mo6806a() {
            try {
                return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(new BigInteger("B76D0E90467ECC6FC2E609149E1C83F53E1CC1B9FE43FD9A2B3938DAF9AED2E878C2B67E3AFC84E5E650C1B18B544ADE0A3BDEC87FAD1028774330191C0D675578B89D264E63E4E1819952962D33C47CEB154E24161980C8F75AF7755EDC387A1F96CD1222D9880B08D48BADCAC6FD20B60875820619AE4FA767B64FD63D7FBE916417ADCAFAABA925BAEC0DE0B52EA8C7178985AEEFE0F3B26D554131C513EAD5DD389C07E58E4D7C6120360C1F5542BF9F944094CE84A1D53613F91C0B97E18B28B12197FEE941C9E58791CF443DCA05605807E87D787D4A6361BFD1DF8CE2C51F5CB1832276CECEEAA0ED689525F60C55AF07438738EF94E925357D1D5C8D", 16), new BigInteger("010001", 16)));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e2) {
                e2.printStackTrace();
            }
            return null;
        }
    }

    private C3813a(C3811a c3811a) {
        this.f9166b = c3811a.mo6806a();
    }

    public static C3813a m12203a() {
        return f9165c;
    }

    public String m12204a(String str) {
        String str2 = null;
        try {
            if (this.f9166b != null) {
                Cipher instance = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
                instance.init(1, this.f9166b);
                str2 = Base64.encodeToString(instance.doFinal(str.getBytes("UTF-8")), 0);
            }
        } catch (Throwable e) {
            C3833d.m12250b(f9164a, "unable to create cipher.", e);
        } catch (Throwable e2) {
            C3833d.m12250b(f9164a, "unable to create cipher.", e2);
        } catch (Throwable e22) {
            C3833d.m12250b(f9164a, "unable to initialize cipher.", e22);
        } catch (Throwable e222) {
            C3833d.m12250b(f9164a, "unable to cipher input text.", e222);
        } catch (Throwable e2222) {
            C3833d.m12250b(f9164a, "unable to cipher input text.", e2222);
        } catch (UnsupportedEncodingException e3) {
            e3.printStackTrace();
        }
        return str2;
    }
}
