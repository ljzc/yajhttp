package nju.yajhttp.message;

import java.io.InputStream;

public enum Method {
    OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT;

    public static Method parse(InputStream stream) {
        // TODO: parse method
        return null;
    }

    public byte[] toBytes() {
        return Util.toBytes(toString());
    }
}
