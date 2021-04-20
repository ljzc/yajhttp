package nju.yajhttp.message;

import lombok.Data;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static nju.yajhttp.message.Constants.colonsep;
import static nju.yajhttp.message.Constants.crlf;

@Data
public class Response {
    @NonNull
    private Version version = Version.HTTP1_1;
    @NonNull
    private Status status = Status.OK;
    @NonNull
    private HashMap<String, String> headers = new HashMap<>();
    private byte[] body;

    public String header(String name) {
        return headers.get(name);
    }

    public Response header(String name, String value) {
        if (value == null)
            headers.remove(name);
        else
            headers.put(name, value);
        return this;
    }

    public static Response parse(InputStream stream) {
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
        s.write(version.toBytes());
        s.write(' ');
        s.write(status.toBytes());
        s.write(crlf);
        for (var h : headers.entrySet()) {
            s.write(h.getKey().getBytes(StandardCharsets.US_ASCII));
            s.write(colonsep);
            s.write(h.getValue().getBytes(StandardCharsets.US_ASCII));
            s.write(crlf);
        }
        s.write(crlf);
        if (body != null)
            s.write(body);
        return s.toByteArray();
    }
}
