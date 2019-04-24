package app.utils.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface SearchField {
    String code() default "";
    String name() default "";
    float price() default 0;
}
