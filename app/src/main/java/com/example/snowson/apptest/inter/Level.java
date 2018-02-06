package com.example.snowson.apptest.inter;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by snowson on 18-2-6.
 * 限定符test
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface Level {
    String value() default "";
}
