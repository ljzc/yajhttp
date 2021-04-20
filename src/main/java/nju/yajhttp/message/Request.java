package nju.yajhttp.message;

import lombok.Data;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import static nju.yajhttp.message.Constants.colonsep;
import static nju.yajhttp.message.Constants.crlf;

@Data
public class Request {
    @NonNull
    private Method method = Method.GET;
    @NonNull
    private URI uri = new URI("/");
    @NonNull
    private Version version = Version.HTTP1_1;
    @NonNull
    private HashMap<String, String> headers = new HashMap<>();
    private byte[] body;

    public String header(String name) {
        return headers.get(name);
    }

    public Request header(String name, String value) {
        if (value == null)
            headers.remove(name);
        else
            headers.put(name, value);
        return this;
    }

    public Request uri(String str) {
        uri = new URI(str);
        return this;
    }

    public Request uri(URI uri) {
        this.uri = uri;
        return this;
    }

    public static Request parse(InputStream stream) {
        // TODO: parse request
        return null;
    }

    @SneakyThrows
    public void write(OutputStream stream) {
        stream.write(toBytes());
        stream.flush();
    }

    @SneakyThrows
    public byte[] toBytes() {
        var s = new ByteArrayOutputStream();
        s.write(method.toBytes());
        s.write(' ');
        s.write(uri.toBytes());
        s.write(' ');
        s.write(version.toBytes());
        s.write(crlf);
        for (var h : headers.entrySet()) {
            s.write(Util.toBytes(h.getKey()));
            s.write(colonsep);
            s.write(Util.toBytes(h.getValue()));
            s.write(crlf);
        }
        s.write(crlf);
        if (body != null)
            s.write(body);
        return s.toByteArray();
    }
}
