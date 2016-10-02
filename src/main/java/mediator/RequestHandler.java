package mediator;


/**
 * Defines a handler for a request
 * 
 * @param <TRequest>
 *            The type of message to be handled
 * @param <TResponse>
 *            The type of response to be returned by the handler
 */
public interface RequestHandler<T extends Request, R> {

    /**
     * Handles a request
     * 
     * @param request
     *            message to be handled
     * @return Response from the request
     */
    R handle(T request);
}
