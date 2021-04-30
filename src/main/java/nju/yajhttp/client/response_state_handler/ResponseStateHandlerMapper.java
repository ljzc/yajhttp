package nju.yajhttp.client.response_state_handler;

import nju.yajhttp.client.response_state_handler.handlers.DefaultResponseStateHandler;
import nju.yajhttp.client.response_state_handler.handlers.ResponseStateHandler;
import nju.yajhttp.message.Status;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

public class ResponseStateHandlerMapper {
    private HashMap<Status, ResponseStateHandler> handlerHashMap;
    private ResponseStateHandler defaultHandler;

    public static ResponseStateHandlerMapper getInstance(){
        try {
            return new ResponseStateHandlerMapper();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ResponseStateHandlerMapper() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        handlerHashMap = new LinkedHashMap<>();
        Reflections reflections = new Reflections("nju.yajhttp.client.response_state_handler.handlers.handlerimp");
        Set<Class<? extends ResponseStateHandler>> handlers = reflections.getSubTypesOf(ResponseStateHandler.class);
        for(Class<? extends ResponseStateHandler> cls : handlers){
            ResponseStateHandler handler = cls.getDeclaredConstructor().newInstance();
            handlerHashMap.put(handler.getStatus(), handler);
        }
        this.defaultHandler = new DefaultResponseStateHandler();
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("default: ").append(defaultHandler.toString());
        builder.append(handlerHashMap.toString());
        return builder.toString();
    }

    public ResponseStateHandler map(Status status){
        ResponseStateHandler handler = handlerHashMap.get(status);
        if(handler == null){
            handler = defaultHandler;
        }
        return handler;
    }
}
