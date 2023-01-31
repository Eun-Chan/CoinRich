package com.ddigrang.coinrich.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class WebCrawlingTest {

    @Value("${apikey.generate.password}")
    private String key;

    WebCrawling webCrawling;

    @Test
    void test() throws NullPointerException{
        webCrawling.crawling("test");
    }

    @Test
    void test2() throws EncryptionOperationNotPossibleException {

        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword(key);

        // 암호화
        System.out.println(pbeEnc.encrypt("BZ3od6RJ5IwotlFVGrdr7pfYMn4lyF6QPE3jsaVF"));
        System.out.println(pbeEnc.encrypt("jYeSPB11friDCUXhRKS47BD6ZTjPMyqy7H2Rq2Vy"));
        System.out.println(pbeEnc.encrypt("eunchan"));
        // 복호화
//        pbeEnc.decrypt("");
    }
}