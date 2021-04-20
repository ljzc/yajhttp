package nju.yajhttp;

import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.NonNull;
import lombok.SneakyThrows;
import nju.yajhttp.message.Request;
import nju.yajhttp.message.Response;
import nju.yajhttp.message.Status;

public class Server {
    @SneakyThrows
    public static void main(String[] args) {
        @Cleanup
        var ss = new ServerSocket(8000);
        var pool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                2 * Runtime.getRuntime().availableProcessors(), 1, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        while (true) {
            var s = ss.accept();
            System.out.println("Accepted connection from " + s.getRemoteSocketAddress());
            pool.execute(new RequestHandler(s));
        }
    }
}

@AllArgsConstructor
class RequestHandler implements Runnable {
    @NonNull
    Socket socket;

    @Override
    @SneakyThrows
    public void run() {
        // TODO: handle requests
        @Cleanup
        var socket = this.socket;
        var request = Request.read(socket.getInputStream());
        var response = new Response().status(Status.NOT_FOUND).body("Not Found".getBytes(StandardCharsets.US_ASCII));
        response.write(socket.getOutputStream());
    }

}
