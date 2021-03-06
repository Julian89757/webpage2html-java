package com.cedricblondeau.webpage2html.http.resource;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.net.URL;

public final class HttpResponseResource implements HttpResource {

    private URL url;
    private ResponseBody responseBody;
    private String mediaType;

    public HttpResponseResource(ResponseBody responseBody, URL url) {
        this.responseBody = responseBody;
        this.url = url;
        buildMediaType();
    }

    private void buildMediaType() {
        MediaType contentType = responseBody.contentType();
        this.mediaType = String.format("%s/%s", contentType.type(), contentType.subtype());
    }

    @Override
    public URL getUrl() {
        return url;
    }

    @Override
    public byte[] getData() {
        try {
            return this.responseBody.bytes();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getContent() {
        try {
            return this.responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getMediaType() {
        return mediaType;
    }
}
