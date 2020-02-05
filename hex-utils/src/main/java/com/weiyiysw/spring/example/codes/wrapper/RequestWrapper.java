package com.weiyiysw.spring.example.codes.wrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * @author yishiwei
 * @date 2019/9/5
 */
public class RequestWrapper extends HttpServletRequestWrapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestWrapper.class);

    private final String body;

    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try (InputStream in = request.getInputStream()) {
            if (in != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(in));
                char[] charBuffer = new char[128];
                int n = -1;
                while ((n = bufferedReader.read(charBuffer)) > 0) {
                    builder.append(charBuffer, 0, n);
                }
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader!=  null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw  ex;
                }
            }
        }
        body = builder.toString();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        ServletInputStream servletInputStream = new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {}

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
        return servletInputStream;

    }
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public String getBody() {
        return this.body;
    }
}
