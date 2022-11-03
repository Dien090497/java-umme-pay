package vn.unicloud.umeepay.common;

import java.lang.annotation.*;

/**
 * Usage:
 * @Authorized(actions = {"SOME_ACTION"})
 * public Object method(Object ...params) {}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Inherited
public @interface Authorized {

    String[] actions() default "";

    boolean enabled() default true;

}