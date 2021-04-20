package nju.yajhttp;

import java.net.Socket;
import java.nio.charset.StandardCharsets;

import lombok.Cleanup;
import lombok.SneakyThrows;
import nju.yajhttp.message.Method;
import nju.yajhttp.message.Request;
import nju.yajhttp.message.URI;
import nju.yajhttp.message.Version;

public class Client {
    @SneakyThrows
    public static void main(String[] args) {
        var uri = new URI(args[0]);
        var port = 0;
        switch (uri.scheme()) {
        case "http":
            port = 80;
            break;
        default:
            throw new Error();
        }
        var request = new Request().method(Method.GET).version(Version.HTTP1_0).uri(uri).header("Host", uri.host());
        @Cleanup
        var socket = new Socket(uri.host(), port);
        var os = socket.getOutputStream();
        request.write(os);
        var is = socket.getInputStream();
        System.out.println(new String(is.readAllBytes(), StandardCharsets.UTF_8));
    }
}
