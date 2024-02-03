package org.moaydogdu.enoca_challenge.auth.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnocaConfigurationParameter {
    ENOCA_ISSUER("ENOCA"),

    AUTH_ACCESS_TOKEN_EXPIRE_MINUTE("30"),
    AUTH_REFRESH_TOKEN_EXPIRE_DAY("1"),
    AUTH_PUBLIC_KEY("""
            -----BEGIN PUBLIC KEY-----
            MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgkZ9bvgzLRXIv9thFLU4
            Ffs2a9chCRkPNpJt6MyBnYIbmcsUguh1QfoktyzE06EuAFR6DHnybk38kUGvd0+d
            VL1yxJmNqu9Q+nBE9vJaL05tSLUxa3RJ+hJbdrGYiBundwefDdTNavK0l1Sq+KpT
            UnEx1XVGCV54hDrSuuugTDwEsPrjR6kf0XVQh5x+kqY0DihxWQMJT6bmMzNFIBA0
            C6kjLf3XFqBxUXBgYy3wx2fYhQfDLFwwNtOLVNwGoX9uqkIyjwziXWeeDpVnCg1l
            HtnOI0uR03/k5OToVL7NXpoShirJsZW9GwcRZOxbYNAg1tqo+JhjPReOFDii0Nop
            fQIDAQAB
            -----END PUBLIC KEY-----
                        """),
    AUTH_PRIVATE_KEY("""
            -----BEGIN PRIVATE KEY-----
            MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCCRn1u+DMtFci/
            22EUtTgV+zZr1yEJGQ82km3ozIGdghuZyxSC6HVB+iS3LMTToS4AVHoMefJuTfyR
            Qa93T51UvXLEmY2q71D6cET28lovTm1ItTFrdEn6Elt2sZiIG6d3B58N1M1q8rSX
            VKr4qlNScTHVdUYJXniEOtK666BMPASw+uNHqR/RdVCHnH6SpjQOKHFZAwlPpuYz
            M0UgEDQLqSMt/dcWoHFRcGBjLfDHZ9iFB8MsXDA204tU3Aahf26qQjKPDOJdZ54O
            lWcKDWUe2c4jS5HTf+Tk5OhUvs1emhKGKsmxlb0bBxFk7Ftg0CDW2qj4mGM9F44U
            OKLQ2il9AgMBAAECggEAUxi5a64fnpN1oINDklzhcIvzs6feZ5o3bPzL5KGFz70s
            89VouPOjz7jJ8Jk61y7JU+aOjyS98YQHJXQ7gyQiW+oXqbW0eqL6CTTRmJ8PzXr+
            FFGXO05K3ZsWAkjX3YGeOTuARedGPACiFqEoCUNDsKOJQPnz+Yfr4ZRP1HD1p4n1
            fquJB7pc4DDOiYHfsD1E2onFxiw74b2AlVLd79Pm8GGn/lfc25Z2jfQopk9zTP8p
            2fIxah/JiU+1peXZ3e6FYRGG0hVCdVpIaquo1mUCUcFuwyvKjzSNkI+yXK07SkfI
            /qMx4uXyYfcFJx6IoGqGBh2wXrW5adtG2gobBPvBwQKBgQDnaf4/rYclrNnRG3aq
            XPjxrtNG85UkH5dodujEEC0ob2H60ALno6G19oCqKIV5tqAjyvG7usPLDcM5vORJ
            LoxA1a0W6LDT58SNvvZM4Kq+CLtJa1cixmQ8/LVOmgXsUbwnyjvkHDPstY6jS3EC
            7Ni3oMc0BQX9eciliuotsHXXcQKBgQCQHbkynQVU5yGClJs0quIxuPccpGIxKWnl
            sNrWlcasqa3+vhcvF8CN7NRMGqdUE5zW0w8uJr86sj0u4RjXbA1NTD7JCjwcTuRp
            91NjdxS9QB5t9U6yT4pDHjwDpaulOm9Pc8xBqB9XxGCLwDutHfkMrJtn5Il2Pr0U
            iPtvQlzkzQKBgDZSUzRvb4IQiTDk2X40RNu2xjGpjCDmqYwEHa+ofHE5dC6EFap6
            ZsNoEdvflzVzATRlt0jyFnPbzzAGfzAOlS3C+tad2KRli2YapYZ2Vp41PoGFiPbI
            y07lo0nILgvDQ/+zIUvyst2l4M3EL8sX/edc/mXfqABQTiFN8pq3CXKBAoGBAI1K
            VtNkVmHS6GuZPkD4eCK/cKXIE+yW6ZejOCnBVbLZ+BgUTa6B3upGEi06xGoNgGqt
            KejZXAozzQA096Hb8X6jDbsUKbKnNhEdxcdybAbO4gZzxd/TU35SGgyJVCr9izue
            D9ce+PuXMABTSy6CgDUI1KOaz5n6EG4djkSaD3DRAoGATQlduhExTRQuGOHeRW6s
            BHUCL/u2dko0L84iP0BRaJyBPDXVD+2uro0n/NAoz5TjI1dWASPFQG06t9d86w/L
            IAA3gMGzphQ+bswQOpFQ3eB5GFPt3vRt0wj/V4xmYCgoAEJ8AqTl1OD3bu3c2O6k
            EBHR9Inj+3Rh2ku6gDUeGFM=
            -----END PRIVATE KEY-----
            """);

    private final String defaultValue;
}
