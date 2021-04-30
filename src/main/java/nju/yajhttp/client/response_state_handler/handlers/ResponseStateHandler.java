package nju.yajhttp.client.response_state_handler.handlers;

import nju.yajhttp.message.Request;
import nju.yajhttp.message.Response;
import nju.yajhttp.message.Status;

import java.nio.charset.StandardCharsets;

public abstract class ResponseStateHandler {
   protected Status status;

   public ResponseStateHandler(Status status){
      this.status = status;
   }

   public Status getStatus(){
      return status;
   }

   public abstract String handle(Request oldRequest, Response response);

   protected static String bytes2Str(byte[] bytes){
      return new String(bytes, StandardCharsets.UTF_8);
   }
}
