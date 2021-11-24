package se.ifmo.labs.s311723.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

@Slf4j
@Component
public class RequestLogFilter extends GenericFilterBean {

    private BufferedReader reader;

    public void setReader(HttpServletRequest request) throws IOException {
        if (reader == null) {
            reader = request.getReader();
        }
    }

    private String extractPostRequestBody(HttpServletRequest request) {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            Scanner s;
            try {
                s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
                return s.hasNext() ? s.next() : "";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest wrappedRequest = new BufferedServletRequestWrapper((HttpServletRequest) servletRequest);
        String body = extractPostRequestBody(wrappedRequest);
        log.info(wrappedRequest.getRequestURL().toString());
        log.info(body);
//        reader.close();
        filterChain.doFilter(wrappedRequest, servletResponse);
    }
}
