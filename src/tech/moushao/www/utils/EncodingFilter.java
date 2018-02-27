package tech.moushao.www.utils;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 全站乱码过滤器
 * Created by Yu on 2017/5/21 0021.
 */
public class EncodingFilter implements Filter {
    private FilterConfig config = null;
    private String encode = null;
    //GET中编码转换只能转一次，不然多次转换会把编码转乱了，不是你要的了
    private boolean isNotEncod = true;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
        System.out.println("EncodingFilter---init----------------------");
        encode = config.getInitParameter("encode") == null ? "utf-8" : config.getInitParameter("encode");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        System.out.println("EncodingFilter--doFilter-------------------");
//        servletRequest.setCharacterEncoding(encode);//POST请求乱码解决
        servletResponse.setContentType("text/html;charset=" + encode);

        filterChain.doFilter(new MyHttpServletRequest((HttpServletRequest) servletRequest), servletResponse);
    }

    public void destroy() {
        System.out.println("EncodingFilter--destroy-------------------------");
    }

    class MyHttpServletRequest extends HttpServletRequestWrapper {
        private HttpServletRequest request = null;

        public MyHttpServletRequest(HttpServletRequest request) {
            super(request);
            this.request = request;

        }

        @Override
        public Map<String, String[]> getParameterMap() {

            try {
                //如果是post提交，一行代码解决post乱码
                if (request.getMethod().equalsIgnoreCase("POST")) {
                    request.setCharacterEncoding(encode);
                    return request.getParameterMap();
                    //如果是GET提交
                } else if (request.getMethod().equalsIgnoreCase("GET") && isNotEncod) {
                    Map<String, String[]> map = request.getParameterMap();
                    for (Map.Entry<String, String[]> entry : map.entrySet()) {
                        String[] vs = entry.getValue();
                        for (int i = 0; i < vs.length; i++) {
                            vs[i] = new String(vs[i].getBytes("iso8859-1"), encode);
                        }
                    }
                    isNotEncod = false;
                    return map;
                } else {
                    //处理两种常用主要的请求访问方式，其它的后期处理
                    return request.getParameterMap();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        @Override
        public String[] getParameterValues(String name) {
            return getParameterMap().get(name);
        }

        @Override
        public String getParameter(String name) {
            return getParameterMap().get(name) == null ? null : getParameterMap().get(name)[0];
        }
    }
}
