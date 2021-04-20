package nju.yajhttp.message;

import java.nio.charset.StandardCharsets;

public class Constants {
    static final byte[] crlf = "\r\n".toString().getBytes(StandardCharsets.US_ASCII);
    static final byte[] colonsep = ": ".toString().getBytes(StandardCharsets.US_ASCII);
}
