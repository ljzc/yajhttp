package nju.yajhttp.message;

import java.io.InputStream;

/**
 * Request Methods
 * {@link https://tools.ietf.org/html/rfc2616#section-5.1.1}
 * {@link https://tools.ietf.org/html/rfc2616#section-9}
 */
public enum Method {
    OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT;

    public static Method read(InputStream stream) {
        // TODO: parse method
        return null;
    }

    public byte[] toBytes() {
        return Util.toBytes(toString());
    }
}
