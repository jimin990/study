package praticeServlet.praticeServlet.web.frontcontroller.v5.adapter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import praticeServlet.praticeServlet.web.frontcontroller.ModelView;
import praticeServlet.praticeServlet.web.frontcontroller.v4.ControllerV4;
import praticeServlet.praticeServlet.web.frontcontroller.v5.MyHandlerAdapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {

        ControllerV4 controller = (ControllerV4) handler;

        Map<String, String> paramMap = createParamMap(request);

        Map<String, Object> model = new HashMap<>();

        String viewName = controller.process(paramMap, model);
        ModelView modelView = new ModelView(viewName);
        modelView.setModel(model);
        return modelView;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(paramName-> paramMap.put(paramName, request.getParameter(paramName)) );
        return paramMap;
    }
}
