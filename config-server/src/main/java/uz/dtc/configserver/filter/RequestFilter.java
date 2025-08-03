package uz.dtc.configserver.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class RequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Wrap request and response to read body
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        // Log request
        showRequestInfo(wrappedRequest);

        // Continue filter chain
        filterChain.doFilter(wrappedRequest, wrappedResponse);

        // Log response
        showResponseInfo(wrappedRequest, wrappedResponse);

        // Important: copy response back to the output stream
        wrappedResponse.copyBodyToResponse();
    }

    private void showRequestInfo(ContentCachingRequestWrapper request) {
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String query = request.getQueryString();
        String fullUrl = uri + (query != null ? "?" + query : "");

        System.out.println("==> Req -> " + method + " " + fullUrl);

        byte[] buf = request.getContentAsByteArray();
        if (buf.length > 0) {
            String body = new String(buf, StandardCharsets.UTF_8);
            System.out.println("Request Body: " + body);
        }
    }

    private void showResponseInfo(ContentCachingRequestWrapper request,
                                  ContentCachingResponseWrapper response) {
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String query = request.getQueryString();
        String fullUrl = uri + (query != null ? "?" + query : "");

        int status = response.getStatus();

        System.out.println("<== Res -> " + status + " " + method + " " + fullUrl);

        byte[] buf = response.getContentAsByteArray();
        if (buf.length > 0) {
            String body = new String(buf, StandardCharsets.UTF_8);
            System.out.println("Response Body: " + body);
        }
    }
}