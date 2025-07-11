/*
 *  The MIT License (MIT)
 *
 *  Copyright © 2021-2025, Alps BTE <bte.atchli@gmail.com>
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package com.alpsbte.alpslib.web;

import com.alpsbte.alpslib.web.model.LinkRequest;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.UUID;

public class BTEWebAPI {

    private static final String urlBase = "https://api.buildtheearth.net/api/v1";

    public static void main(String[] args) throws Exception {
        LinkRequest linkRequest = new LinkRequest(123456, UUID.randomUUID(), "rijsberhp");
        sendLinkRequest(linkRequest);
    }

    public static void sendLinkRequest(LinkRequest linkRequest) throws Exception {
        Gson gson = new Gson();
        String linkRequestJson = gson.toJson(linkRequest, LinkRequest.class);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(urlBase.concat("/minecraft/code")))
                .PUT(BodyPublishers.ofString(linkRequestJson))
                .build();

        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpResponse<String> response = httpClient.send(httpRequest, BodyHandlers.ofString());
            System.out.println(response.body());
        }
    }
}
