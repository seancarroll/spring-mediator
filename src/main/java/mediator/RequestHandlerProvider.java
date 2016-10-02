package mediator;

/**
 * 
 *
 */
public interface RequestHandlerProvider {

    /**
     * 
     * @param request
     * @return
     */
    RequestHandler<Request, Object> getRequestHandler(Request request);

}
