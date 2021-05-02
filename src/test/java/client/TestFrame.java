package client;

import nju.yajhttp.client.command_handler_pipeline.CommandHandlerPipeline;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class TestFrame {
    @Test
    public void test01() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
        CommandHandlerPipeline.getInstance().handleCommand(new String[]{
                "-u", "test:test", "-o", "filename", "-d", "datadata", "-H", "headerheader", "-H", "headerheader2",
                "localhost:8000", "localhost8001"
        });
    }
    @Test
    public void test02() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
        CommandHandlerPipeline.getInstance().handleCommand(new String[]{
                "-h" ,"-u", "test:test", "-o", "filename", "-d", "datadata", "-H", "headerheader", "-H", "headerheader2",
                "localhost:8000", "localhost8001"
        });
    }
}
