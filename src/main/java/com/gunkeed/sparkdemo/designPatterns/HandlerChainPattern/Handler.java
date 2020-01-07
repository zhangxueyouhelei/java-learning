package com.gunkeed.sparkdemo.designPatterns.HandlerChainPattern;

/**
 * 责任链模式
 */
public abstract class Handler {

    private Handler nextHandler;

    public final Response handlerMessage(Request request){
        Response response = null;
        if(this.getHandlerLever().equals(request.getRequestLevel())){
            response = this.echo(request);
        }else {
            if(nextHandler!=null){
                response = nextHandler.handlerMessage(request);
            }
        }
        return response;
    }

    protected abstract Level getHandlerLever();

    protected abstract Response echo(Request request);

    public Handler getNextHandler() {
        return nextHandler;
    }

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }
}

