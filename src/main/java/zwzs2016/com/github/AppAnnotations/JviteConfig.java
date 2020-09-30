package zwzs2016.com.github.AppAnnotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface JviteConfig {
    String value() default "";
}
