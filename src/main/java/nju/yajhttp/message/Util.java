package nju.yajhttp.message;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import lombok.SneakyThrows;

public class Util {
    @SneakyThrows
    static byte[] readUntil(InputStream stream, char c) {
        var s = new ByteArrayOutputStream();
        for (int i = stream.read(); i != -1 && (char) i != c; i = stream.read()) {
            s.write(i);
        }
        return s.toByteArray();
    }

    static byte[] toBytes(String str) {
        return str.getBytes(StandardCharsets.US_ASCII);
    }

    static String fromBytes(byte[] b) {
        return new String(b, StandardCharsets.UTF_8);
    }
}
