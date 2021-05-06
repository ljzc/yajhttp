package nju.yajhttp;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import lombok.Cleanup;
import lombok.NonNull;
import lombok.SneakyThrows;
import nju.yajhttp.message.*;

public class Client {
    private Response response;
    private Request request;
    private int requestCnt = 0;
    private String msg;
    private OutputStream os = System.out;
    @SneakyThrows
    public static void main(String[] args) {


        Client client = new Client();

        client.handleResponseStatus();
        client.output();


    }






    Response sendRequest() throws IOException {


        URI uri = request.uri();
        var port = 0;
        switch (uri.scheme()) {
            case "http":
                port = 80;
                break;
            default:
                throw new Error();
        }

        @Cleanup
        Socket socket = new Socket(uri.host(), port);
        //System.out.println(uri.host());
        OutputStream os = socket.getOutputStream();
        request.write(os);
        //byte[] bytes = socket.getInputStream().readAllBytes();
        //InputStream is = new ByteArrayInputStream(bytes);
        //System.out.println(new String(bytes, StandardCharsets.UTF_8));

        response = Response.read(socket.getInputStream());
        //System.out.println(new String(response.toBytes(), StandardCharsets.UTF_8));

        return response;
    }

    void handleResponseStatus() throws IOException {

        switch (response.status().code()){
            case 200:
                break;
            case 302:
            case 301:
                @NonNull
                String location = response.header("Location");
                URI newUri = request.uri().resolve(location);
                request.uri(newUri);
                response = sendRequest();
                if(requestCnt > 10){
                    msg = "重定向次数过多";
                    break;
                }
                requestCnt++;
                handleResponseStatus();


                break;
            case 304:
                msg = "页面未作调整";
                break;
            default:
                msg = "未处理的状态：" + response.status().toString();

        }

    }

    void output() throws IOException {
        @NonNull
        @Cleanup
        OutputStream os = this.os;
        System.out.println(msg);
        if(response.body() != null){
            String[] contentType = response.header("Content-Type").split("; *");
            switch (contentType[0].split("/")[0]){
                case "text":
                    @Cleanup
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
                    String charset = "us-ascii";
                    if(contentType.length > 1){
                        charset = contentType[1].split("=")[1];
                    }
                    String content = new String(response.body(), charset);
                    writer.write(content);
                    break;
                case "image":
                    if(os == System.out){
                        System.out.println("返回类型为图片类型：" + contentType[0] + "请重新发送请求并使用-o选项将结果保存到文件");
                    }else {
                        System.out.println("返回类型为图片类型：" + contentType[0] + "请检查文件后缀是否正确");
                        os.write(response.body());
                    }

                    break;
                default:
                    os.write(("unsupported content type" + contentType[0] + "\n").getBytes(StandardCharsets.US_ASCII));
                    os.write(response.body());

            }
        }


    }


}

