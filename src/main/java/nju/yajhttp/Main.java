package nju.yajhttp;

import java.net.Socket;
import java.nio.charset.StandardCharsets;

import lombok.Cleanup;

import nju.yajhttp.message.Method;
import nju.yajhttp.message.Request;
import nju.yajhttp.message.Response;
import nju.yajhttp.message.Version;

public class Main {
    public static void main(String[] args) {
        try {
            var request = new Request().method(Method.GET).uri("/etc/passwd")
                    .version(Version.HTTP1_0);
            var socket = new Socket("localhost", 8000);
            var os = socket.getOutputStream();
            request.write(os);
            var is = socket.getInputStream();
            System.out.println(new String(is.readAllBytes(), StandardCharsets.US_ASCII));
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
