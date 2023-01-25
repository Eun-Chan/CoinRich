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
        System.out.println(pbeEnc.encrypt("ByfOIVUozvsdK5oxnxKtv7hIpsKpLxikoUcx7WxF"));
        System.out.println(pbeEnc.encrypt("xzoHXlFdP0A3xqFxqxx8TcbacTPvet1kvczxlAYh"));
        System.out.println(pbeEnc.encrypt("eunchan"));
        // 복호화
//        pbeEnc.decrypt("");
    }
}