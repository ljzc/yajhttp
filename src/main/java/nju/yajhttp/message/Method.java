package nju.yajhttp.message;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public enum Method {
    OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT;

    public static Method parse(InputStream stream) {
    }

    public byte[] toBytes() {
        return toString().getBytes(StandardCharsets.US_ASCII);
    }
}
