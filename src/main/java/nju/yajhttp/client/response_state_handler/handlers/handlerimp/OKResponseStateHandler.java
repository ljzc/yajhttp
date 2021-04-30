package nju.yajhttp.client.response_state_handler.handlers.handlerimp;

import nju.yajhttp.client.response_state_handler.handlers.ResponseStateHandler;
import nju.yajhttp.message.Request;
import nju.yajhttp.message.Response;
import nju.yajhttp.message.Status;

public class OKResponseStateHandler extends ResponseStateHandler{

    public OKResponseStateHandler(){
        super(Status.OK);
    }

    @Override
    public String handle(Request oldRequest, Response response) {
        if(response.body() != null){
            return bytes2Str(response.body());
        }else {
            return "";
        }
    }
}
