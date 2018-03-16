package materialcalc.house.godbeom.com.materialcalc.sample.aspectj.threadinjector;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by BeomChul.Shin on 2016-07-05.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface bUiThread {
	long delay() default 0;
}
