package materialcalc.house.godbeom.com.materialcalc.sample.aspectj.threadinjector;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by BeomChul.Shin on 2016-07-05.
 *
 *  Method에 붙으면 조인 포인트에서 Background Thread로 Join 되어 실행 됩니다.
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface bBackground {
}