package controllers;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import controllers.actions.VersionedAction;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.With;

import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeoutException;

@With(VersionedAction.class)
class PizzaAppController extends Controller {

    static Result okJson(Object o) {
        return ok(Json.toJson(o));
    }

    static <T> CompletionStage<Result> promiseJson(CompletionStage<T> t) {
        return t.thenApply(PizzaAppController::okJson)
                .exceptionally(PizzaAppController::errorJson);
    }

    static Result errorJson(Throwable t) {
        final int code;

        if(t instanceof CompletionException){
            t=t.getCause();
        }

        if (t instanceof TimeoutException) {
            code = Http.Status.TOO_MANY_REQUESTS;
        } else {
            code = Http.Status.SERVICE_UNAVAILABLE;
        }
        return status(code, JsonNodeFactory.instance
                .objectNode()
                .put("code", code)
                .put("error", t.getMessage()));
    }

}
