package com.yf.pet.web.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.yf.pet.common.ReturnMessageEnum;
import com.yf.pet.common.exception.YFException;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Map;

/**
 * 异常拦截器
 *
 * @author infi.wang
 */
public class ExceptionInterceptor extends HandlerInterceptorAdapter {

    /**
     * log
     */
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (ex != null) {
            String code = ReturnMessageEnum.SYSTEM_ERROR.getResult();
            String msg = "";
            if (ex instanceof YFException) {
                code = ((YFException) ex).getResult();
                msg = ex.getMessage();
            } else {
                msg = ReturnMessageEnum.SYSTEM_ERROR.getMessage();
            }
            logger(request, handler, ex);
//            Map<String, Object> resultMap = ResultMapUtils.getResultMap();
            Map<String, Object> resultMap = new HashedMap();
            resultMap.put("result", code);
            resultMap.put("message", msg);

            response.setContentType("application/json;charset=UTF-8");
            JSON.writeJSONStringTo(resultMap, response.getWriter());
            response.getWriter().flush();
            response.getWriter().close();
            return;
        } else {
            super.afterCompletion(request, response, handler, ex);
        }
        super.afterCompletion(request, response, handler, ex);
    }

    /**
     * 记录日志
     *
     * @param request
     * @param handler
     * @param ex
     */
    public void logger(HttpServletRequest request, Object handler, Exception ex) {
        StringBuffer msg = new StringBuffer();
        msg.append("异常拦截日志");
        msg.append("[uri:").append(request.getRequestURI()).append("]");
        Enumeration<String> enumer = request.getParameterNames();
        while (enumer.hasMoreElements()) {
            String name = enumer.nextElement();
            String[] values = request.getParameterValues(name);
            msg.append("[").append(name).append("=");
            if (values != null) {
                int i = 0;
                for (String value : values) {
                    i++;
                    msg.append(value);
                    if (i < values.length) {
                        msg.append("｜");
                    }
                }
            }
            msg.append("]");
        }
        log.error(msg.toString());
    }
}
