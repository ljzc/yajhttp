package nju.yajhttp;

import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import lombok.Cleanup;
import lombok.SneakyThrows;
import nju.yajhttp.client.command_handler_pipeline.CommandHandlerPipeline;
import nju.yajhttp.message.Method;
import nju.yajhttp.message.Request;
import nju.yajhttp.message.URI;
import nju.yajhttp.message.Version;
import org.apache.commons.cli.*;

public class Client {
    @SneakyThrows
    public static void main(String[] args) {
       /* var uri = new URI(args[0]);
        var port = 0;
        switch (uri.scheme()) {
        case "http":
            port = 80;
            break;
        default:
            throw new Error();
        }
        var request = new Request().method(Method.GET).version(Version.HTTP1_0).uri(uri).header("Host", uri.host());
        @Cleanup
        var socket = new Socket(uri.host(), port);
        var os = socket.getOutputStream();
        request.write(os);
        var is = socket.getInputStream();
        System.out.println(new String(is.readAllBytes(), StandardCharsets.UTF_8));*/


        /*CommandLine commandLine;
        CommandLineParser commandLineParser = new DefaultParser();
        Options options = new Options();

        options.addOption(
                        Option.builder("u")
                                .longOpt("user")
                                .hasArg()
                                .argName("username:password")
                                .desc("指定用户名和密码")
                                .build()
                )
                .addOption(
                        Option.builder("o")
                                .hasArg()
                                .argName("file")
                                .desc("输出到文件")
                                .build()
                )
                .addOption(
                        Option.builder("d")
                                .longOpt("data")
                                .hasArg()
                                .argName("data")
                                .desc("指定使用POST请求以及body")
                                .build()
                ).addOption(
                    Option.builder("H")
                            .longOpt("header")
                            .hasArg()
                            .argName("header")
                            .desc("指定发送请求时的 header，可以多次使用以指定多个 header")
                            .build()
                ).addOption(
                Option.builder("h")
                        .longOpt("help")
                        .desc("打印帮助信息")
                        .build()
        );


        commandLine = commandLineParser.parse(options, args);

        String[] allOptions = {
                "u",
                "o",
                "d",
                "H",
                "h"
        };

        for(String opt : allOptions){
            if(commandLine.hasOption(opt)){
                System.out.println(Arrays.toString(commandLine.getOptionValues(opt)));
            }
        }*/


        CommandHandlerPipeline.getInstance().handleCommand(args);



    }
}
