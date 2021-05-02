package client;

import org.apache.commons.cli.*;
import org.junit.Test;

public class TestOptions {
    @Test
    void test01(){
        CommandLine commandLine;
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
        );




    }
}
