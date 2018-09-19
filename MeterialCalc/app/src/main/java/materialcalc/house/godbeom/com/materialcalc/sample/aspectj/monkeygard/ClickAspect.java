/*
package materialcalc.house.godbeom.com.materialcalc.sample.aspectj.monkeygard;

import android.view.View;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;


/**
 *
 * Created by BeomChul.Shin on 2018-03-16.
 *  다발성 클릭 방지 관점 삽입.
 *
 * *//*

@Aspect
public class ClickAspect {

    */
/**포인트 컷 생성.*//*

    @Pointcut("execution(* android.view.View.OnClickListener.*(..))")
    public void onClickPoincut() {
    }


    */
/**포인트 컷 실행 전 행동*/
/*

    @Before("onClickPoincut()")
    public void beforeOnClick(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            View view = (View) args[0];
            view.setEnabled(false);
        }
    }

    */
/**포인트 컷 실행 후 행동*//*

    @After("onClickPoincut()")
    public void atferOnClick(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            final View view = (View) args[0];
            view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.setEnabled(true);
                }
            }, 700);
        }
    }
}*/
