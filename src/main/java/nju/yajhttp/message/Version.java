package nju.yajhttp.message;

public enum Version {
    HTTP1_0, HTTP1_1, HTTP2_0;

    public byte[] toBytes() {
        return Util.toBytes(toString().replace('_', '.').replace("HTTP", "HTTP/"));
    }
}
