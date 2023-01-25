package com.ddigrang.coinrich.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.security.auth.login.LoginException;

@Component
public class Bot {

    @Autowired
    public Bot(CommandListener commandListener, @Value("${apikey.discord.token}") String token) throws LoginException {

        JDA jda = JDABuilder.createDefault(token)
                .enableCache(CacheFlag.VOICE_STATE)
                .build();

        jda.addEventListener(commandListener);

        jda.getPresence().setActivity(Activity.watching("코인으로 인생역전"));
    }

}

