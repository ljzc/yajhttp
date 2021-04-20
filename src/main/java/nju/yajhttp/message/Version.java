package nju.yajhttp.message;

import java.nio.charset.StandardCharsets;

public enum Version {
    HTTP1_0, HTTP1_1, HTTP2_0;

    public byte[] toBytes() {
        return toString().replace('_', '.')
                .replace("HTTP", "HTTP/")
                .getBytes(StandardCharsets.US_ASCII);
    }
}
