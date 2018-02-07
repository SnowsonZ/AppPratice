package com.example.snowson.apptest.inter;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by snowson on 18-2-7.
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface Dagger2Fragment {
}
