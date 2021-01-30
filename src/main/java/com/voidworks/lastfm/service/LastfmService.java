package com.voidworks.lastfm.service;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.voidworks.lastfm.communication.Communicator;
import com.voidworks.lastfm.service.response.LastfmServiceResponse;

public class LastfmService implements Communicator {

    public LastfmServiceResponse getScrobblesByUserAndPage(String user, int page, int pageSize) {
        String url = buildUrl(user, page, pageSize);

        Response response = performHttpRequest(url);

        String jsonResponse = "";
        try {
            jsonResponse = response != null ? response.body().string() : "";
        } catch (Exception e) {
            printError(e.getMessage());
        }

        if (response == null || response.code() != 200) {
            return LastfmServiceResponse.builder()
                    .code(response != null ? response.code() : 500)
                    .json(jsonResponse)
                    .build();
        }

        DocumentContext jsonContext = JsonPath.parse(jsonResponse);

        return LastfmServiceResponse.builder()
                .code(response.code())
                .page(Integer.parseInt(jsonContext.read("$['recenttracks']['@attr']['page']")))
                .totalPages(Integer.parseInt(jsonContext.read("$['recenttracks']['@attr']['totalPages']")))
                .json(jsonResponse)
                .build();
    }

    private String buildUrl(String user, int page, int pageSize) {
        return "http://ws.audioscrobbler.com/2.0/?" +
                "api_key=9a5bb0d39039d14b7f3cfb2fafbabc73" +
                "&method=user.getrecenttracks" +
                "&user=" + user +
                "&limit=" + pageSize +
                "&page=" + page +
                "&format=json";
    }

    private Response performHttpRequest(String url) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (Exception e) {
            printError(e.getMessage());
        }

        return response;
    }

}
