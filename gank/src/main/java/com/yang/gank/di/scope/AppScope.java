package com.yang.gank.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Describe:
 * Created by Yang on 2019/4/19.
 */
@Documented
@Scope
@Retention(value = RetentionPolicy.RUNTIME)
public @interface AppScope {
}
