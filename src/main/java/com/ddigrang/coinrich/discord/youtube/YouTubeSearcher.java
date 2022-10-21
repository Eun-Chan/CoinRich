package com.ddigrang.coinrich.discord.youtube;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class YouTubeSearcher {
    private static final String API_KEY = "AIzaSyB8Zj_UoFQuoDXFiINIoFWCjFDZcLfRrVE";
    private static final long NUMBER_OF_MUSIC_RETUNED = 1;

    public String searchFor(String input) {
        try {
            YouTube youTube = new YouTube.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JacksonFactory.getDefaultInstance(),
                    null).setApplicationName("musicbot").build();
            List<SearchResult> results = initSearch(youTube, input);
            if (!results.isEmpty())
                return results.get(0).getId().getVideoId();
        } catch (GoogleJsonResponseException e) {
            System.out.println();
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return "";
    }

    private List<SearchResult> initSearch(YouTube youTube, String input) throws IOException {
        return youTube.search()
                .list("id")
                .setQ(input)
                .setMaxResults(NUMBER_OF_MUSIC_RETUNED)
                .setType("video")
                .setFields("items(id)")
                .setKey(API_KEY)
                .execute()
                .getItems();
    }
}
