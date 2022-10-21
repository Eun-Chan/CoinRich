package com.ddigrang.coinrich.config;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEncryptableProperties
public class JasyptConfig {

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        // -Djasypt.encryptor.password="패스워드"
        config.setPassword("eunchan");
        //config.setPassword(System.getProperty("jasypt.encryptor.password"));
        config.setAlgorithm("PBEWithMD5AndDES"); // 암호화 알고리즘
//        config.setKeyObtentionIterations("1000"); // 반복할 해싱 횟수
        config.setPoolSize("1"); // 인스턴스 pool
//        config.setProviderName("SunJCE");
//        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator"); // salt 생성 클래스
//        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64"); // 인코딩 방식

        encryptor.setConfig(config);

        return encryptor;
    }
}