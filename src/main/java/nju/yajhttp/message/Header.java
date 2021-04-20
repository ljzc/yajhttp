package nju.yajhttp.message;

import java.io.ByteArrayOutputStream;

import lombok.Data;
import lombok.SneakyThrows;

/**
 * HTTP Header {@link https://tools.ietf.org/html/rfc2616#section-4.2}
 */
@Data
public class Header {
    private final String name;
    private final String value;

    @SneakyThrows
    public byte[] toBytes() {
        var s = new ByteArrayOutputStream();
        s.write(Util.toBytes(name));
        s.write(Constants.colonsep);
        s.write(Util.toBytes(value));
        return s.toByteArray();
    }
}
