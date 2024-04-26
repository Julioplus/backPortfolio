package com.jcm.portfolio.utils.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;


@Component
@Slf4j
public class RestResponseStatusExceptionResolver extends AbstractHandlerExceptionResolver {

  @Override
  protected ModelAndView doResolveException(HttpServletRequest request,
      HttpServletResponse response, Object handler, Exception ex) {
    try {
      if (ex instanceof IllegalArgumentException) {
        return handleIllegalArgument(
            (IllegalArgumentException) ex, request, response, handler);
      }
    } catch (Exception handlerException) {
      log.warn("Handling of [{}] resulted in Exception", ex.getClass().getName(), handlerException);
    }
    return null;
  }

  private ModelAndView handleIllegalArgument(IllegalArgumentException ex,
      final HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    final String accept = request.getHeader(HttpHeaders.ACCEPT);

    response.sendError(HttpServletResponse.SC_CONFLICT);
    response.setHeader("ContentType", accept);

    final ModelAndView modelAndView = new ModelAndView("error");
    modelAndView.addObject("error", prepareErrorResponse(accept));
    return modelAndView;
  }

  private String prepareErrorResponse(String accept) throws JsonProcessingException {
    final Map<String, String> error = new HashMap<>();
    error.put("Error", "Application specific error message");

    final String response;
    if (MediaType.APPLICATION_JSON_VALUE.equals(accept)) {
      response = new ObjectMapper().writeValueAsString(error);
    } else {
      response = new XmlMapper().writeValueAsString(error);
    }

    return response;
  }


}
