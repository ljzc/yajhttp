package nju.yajhttp.message;

import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class URI {
    private final java.net.URI uri;

    @SneakyThrows
    public URI(String str) {
        uri = new java.net.URI(str);
    }

    public String authority() {
        return uri.getAuthority();
    }

    public String fragment() {
        return uri.getFragment();
    }

    public String host() {
        return uri.getHost();
    }

    public String path() {
        return uri.getPath();
    }

    public int port() {
        return uri.getPort();
    }

    public String query() {
        return uri.getQuery();
    }

    public String scheme() {
        return uri.getScheme();
    }

    public String userInfo() {
        return uri.getUserInfo();
    }

    public byte[] toBytes() {
        return Util.toBytes(uri.toASCIIString());
    }
}
