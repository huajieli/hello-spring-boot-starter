package cn.huajieli.log;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author huajieli
 * @create 2021-10-02 16:39
 * <p>
 * 自定义日志拦截器
 */
public class MyLogInterceptor extends HandlerInterceptorAdapter {

    private static final ThreadLocal<Long> startTimeThreadLocal = new ThreadLocal<>();

    /**
     * controller方法执行前执行此方法
     *
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //获得被拦截对象的方法
        Method method = handlerMethod.getMethod();
        //获得方法上的注解
        MyLog annotation = method.getAnnotation(MyLog.class);
        if (annotation != null) {
            //说明方法上加了MyLog自定义注解
            long startTime = System.currentTimeMillis();
            //这里使用了ThreadLocal是为了能够在下一个方法中使用到
            startTimeThreadLocal.set(startTime);
        }
        return true;
    }

    /**
     * controller方法执行后执行此方法
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //获得被拦截对象的方法
        Method method = handlerMethod.getMethod();
        //获得方法上的注解
        MyLog annotation = method.getAnnotation(MyLog.class);
        if (annotation != null) {
            //方法上加了MyLog注解，需要进行日志记录
            long endTime = System.currentTimeMillis();
            Long startTime = startTimeThreadLocal.get();
            //方法执行时间
            Long operateTime = endTime - startTime;

            //请求的uri
            String requestURI = request.getRequestURI();
            //请求方法名
            String methodName = method.getDeclaringClass().getName() + "." + method.getName();
            //方法描述
            String desc = annotation.desc();
            System.out.println("请求的uri:"+requestURI);
            System.out.println("请求的方法名:"+methodName);
            System.out.println("方法描述:"+desc);
            System.out.println("方法执行时间"+operateTime);
        }

    }
}















