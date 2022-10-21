package com.ddigrang.coinrich.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class WebCrawlingTest {

    WebCrawling webCrawling;

    @Test
    void test() throws NullPointerException{
        webCrawling.crawling("test");
    }

    @Test
    void test2() throws EncryptionOperationNotPossibleException {
        String key = "eunchan";

        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword(key);

        String accesskeyHouse = "U1JQHpfbpPIEMJFSF0ylIElRjTnjxQkPxPrbI18C";
        String secretkeyHouse = "ZBrRJqTrGxaZhl1Lzq3Av7WSJRPXhtuCO3kQ5IPB";
        String accesskeyCafe = "BZ3od6RJ5IwotlFVGrdr7pfYMn4lyF6QPE3jsaVF";
        String secretkeyCafe = "jYeSPB11friDCUXhRKS47BD6ZTjPMyqy7H2Rq2Vy";
        String accesskeyImhak = "ByfOIVUozvsdK5oxnxKtv7hIpsKpLxikoUcx7WxF";
        String secretkeyImhak = "xzoHXlFdP0A3xqFxqxx8TcbacTPvet1kvczxlAYh";

        String test1 = "";
        String test2 = "";

        System.out.println(pbeEnc.encrypt(accesskeyHouse));
        System.out.println(pbeEnc.encrypt(secretkeyHouse));
        System.out.println("-----------------------------");
        System.out.println(pbeEnc.encrypt(accesskeyCafe));
        System.out.println(pbeEnc.encrypt(secretkeyCafe));
        System.out.println("-----------------------------");
        System.out.println(pbeEnc.encrypt(accesskeyImhak));
        System.out.println(pbeEnc.encrypt(secretkeyImhak));

        System.out.println(pbeEnc.decrypt("qWDDSdag4lqRJfRwiNlt83FEX07nhP4GhD4HHLjjlxXu8tDf1Eg6kaj9WmW6fDBWskYWzrsFVXw="));
        System.out.println(pbeEnc.decrypt("5s9kGafpzfj2VH4AAIUmjOCr+acjxmOq8FZ5hOJ24G7nEcbw18IEsSMlBeZz0snxn3Dr2Lo+i28="));
    }
}