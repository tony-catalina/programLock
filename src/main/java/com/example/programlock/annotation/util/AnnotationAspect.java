package com.example.programlock.annotation.util;


import com.example.programlock.annotation.entity.Student;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect//声明这是一个切面
@Component//把这个类交给spring管理
public class AnnotationAspect {

    // 把切面的连接点放在了我们的注解上
    @Pointcut("@annotation(com.example.programlock.annotation.config.CherryAnnotation)")
    public void Aspect() {
    }
    // 在这里定义前置切面
    @Before("Aspect()")
    public void beforeMethod(JoinPoint joinPoint) {
        // 这里执行保存日志的动作
        System.out.println("方法前.......");
        Student.age="11";
        //得到被切方法的参数
        System.out.println(joinPoint.getArgs()[0]);
    }

    @After("Aspect()")
    public void afterMethod(JoinPoint joinPoint) {

        // 这里执行保存日志的动作
        System.out.println("方法后.......");
        //得到被切方法的参数
        System.out.println(joinPoint.getArgs()[0]);
    }

    //环绕增强
    @Around(value="Aspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕开始...");
        Object retValue = pjp.proceed();// 执行目标方法
        System.out.println("环绕结束...");
        return retValue;
    }


    //返回后通知：在调用目标方法结束后执行【出现异常，不执行】
    @AfterReturning(value="Aspect()",returning="retValue")
    public void afterReturning(JoinPoint jp, Object retValue) {
        System.out.println("Aop.afterReturning() 目标方法+"+jp.getSignature().getName()+"返回值:" + retValue);
        /*System.exit(0);*/
    }



}
