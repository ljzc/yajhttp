package nju.yajhttp.message;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import lombok.SneakyThrows;

public class Util {
    @SneakyThrows
    static byte[] readUntil(InputStream stream, byte b) {
        var s = new ByteArrayOutputStream();
        for (int i = stream.read(); i != -1 && (byte) i != b; i = stream.read()) {
            s.write(i);
        }
        return s.toByteArray();
    }

    static byte[] toBytes(String str) {
        return str.getBytes(StandardCharsets.US_ASCII);
    }
}
