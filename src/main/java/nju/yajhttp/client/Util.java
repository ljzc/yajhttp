package nju.yajhttp.client;

import lombok.Cleanup;
import nju.yajhttp.message.Request;
import nju.yajhttp.message.Response;
import nju.yajhttp.message.URI;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Util {
    public static String bytes2Str(byte[] bytes){
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static Response sendRequest(URI uri, Request request) throws IOException {
        var port = 0;
        switch (uri.scheme()) {
            case "http":
                port = 80;
                break;
            default:
                throw new Error();
        }

        @Cleanup
        Socket socket = new Socket(uri.host(), port);
        //System.out.println(uri.host());
        OutputStream os = socket.getOutputStream();
        request.write(os);
        //byte[] bytes = socket.getInputStream().readAllBytes();
        //InputStream is = new ByteArrayInputStream(bytes);
        //System.out.println(new String(bytes, StandardCharsets.UTF_8));

        Response response = Response.read(socket.getInputStream());
        //System.out.println(new String(response.toBytes(), StandardCharsets.UTF_8));

        return response;
    }
}
