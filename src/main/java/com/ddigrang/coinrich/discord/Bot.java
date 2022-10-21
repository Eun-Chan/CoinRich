package com.ddigrang.coinrich.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.security.auth.login.LoginException;

@Component
public class Bot {


    @Autowired
    public Bot(CommandListener commandListener) throws LoginException {

        JDA jda = JDABuilder.createDefault("OTkyMzE0OTc4NTMzMTI2MTg2.GnRMRl.WEx8_M1oCzwTzHkyH-MUpKy_l3lozS7zV9XDvI")
                .enableCache(CacheFlag.VOICE_STATE)
                .build();

        jda.addEventListener(commandListener);

        jda.getPresence().setActivity(Activity.watching("코인으로 인생역전"));
    }
}
