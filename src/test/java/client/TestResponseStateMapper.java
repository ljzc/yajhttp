package client;

import nju.yajhttp.client.response_state_handler.ResponseStateHandlerMapper;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class TestResponseStateMapper {
    @Test
    public void test01() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println(ResponseStateHandlerMapper.getInstance().toString());
    }
}
