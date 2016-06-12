package controllers.actions;

import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletionStage;

/**
   Decorate each response with predefined application version
*/
public class VersionedAction extends Action.Simple {

    public final static String VERSION = "pizza-sample-v1" + "+json;charset=utf-8";

    @Override
    public CompletionStage<Result> call(Http.Context context) {
        context.response().setHeader("Content-Type",VERSION);
        return delegate.call(context);
    }
}

//more: https://www.playframework.com/documentation/2.3.2/JavaActionsComposition
