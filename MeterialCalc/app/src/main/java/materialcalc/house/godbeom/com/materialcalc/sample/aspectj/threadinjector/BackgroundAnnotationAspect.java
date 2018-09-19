/*
package materialcalc.house.godbeom.com.materialcalc.sample.aspectj.threadinjector;


//주의 : AspectJ 에서 Lambda 등(J8 Exp) 사용하지 말것.
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.UiThread;
import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

*/
/**
 *
 * Created by BeomChul.Shin on 2016-07-05.
 * 어노테이션을 붙이면 백그라운드 및 UI쓰레드에서 실행되도록  포인트 컷
 *
 * <pre>
*     @See  MainActivity 에 back()을 참조.
 * {@link bBackground},
 * {@link UiThread}
 * </pre>
 *//*



@Aspect
public class BackgroundAnnotationAspect {

	private Handler uiHandler = new Handler(Looper.getMainLooper());//Main Thread Pool Excutor
	private Executor threadExecutor = Executors.newCachedThreadPool();// SubThread Pool Excutor

	//Sub Thread Hooker
	@Around("execution(@materialcalc.house.godbeom.com.materialcalc.sample.aspectj.threadinjector.bBackground * *.*(..))")
	public void proceedInBackground(final ProceedingJoinPoint pjp) {


		threadExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					Log.d("AspectJ SubThread", "before");
					pjp.proceed();
					Log.d("AspectJ SubThread", "After");
				} catch (final Throwable e) {
					// error
				}
			}
		});
	}

	//Main Thread Hooker
	@Around("@annotation(uithread) && execution(@materialcalc.house.godbeom.com.materialcalc.sample.aspectj.threadinjector.bUiThread * *.*(..))")
	public void proceedInUiThread(final ProceedingJoinPoint pjp, bUiThread uithread) {
		runInUiThread(new Runnable() {
			@Override
			public void run() {
				try {
					Log.d("AspectJ MainThread", "before");
					pjp.proceed();
					Log.d("AspectJ MainThread", "apter");
				} catch (Throwable e) {
					// error
				}
			}
		}  , uithread.delay());
	}


	*/
/**
	 * {@link Runnable}을 UI쓰레드에서 실행.
	 *
	 * @param run
	 * @param delayMillis 지연시간
	 */
/*

	private void runInUiThread(Runnable run, long delayMillis) {
		if (delayMillis <= 0) {
			// 현재 쓰레드가 UI 쓰레드인 경우 걍 run 실행.
			if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
				run.run();
				return;
			}

			uiHandler.post(run);
			return;
		}

		uiHandler.postDelayed(run, delayMillis);
	}
}


  */
/*  AspectJ Pointcut 표현식(aspectj __EXPRESSION__), execution, within, bean

         pointcut :  이란 충고를 받을 메소드를 정의한 것이다.
        execution: 메소드 실행 결합점(join points)과 일치시키는데 사용된다.
        within: 특정 타입에 속하는 결합점(join point)을 정의.



        execution(public * *(..)) : public 메소드가 포인트 컷

        execution(* onj.spring.aop.*.*(..)) : onj.spring.aop 패키지의 모든 메소드가 포인트 컷

        execution(* onj.spring.aop..*.*(..)) : onj.spring.aop 패키지와 하위 패키지의 모든 메소드가 포인트 컷

        execution(public void insert*(..)) : public에 리턴값, 패키지명 없고 메서드 이름은 insert로 시작, 인자값은 0개 이상인 메서드가 포인트 컷

        execution(* onj.spring.aop.*.*()) : 리턴형 관계없고 onj.spring.aop 패키지의 모든 클래스, 인자값이 없는 모든 메서드가 포인트 컷

        execution(* onj.spring.aop..*.*(..)) : 리턴형 관계없고 onj.spring.aop 패키지 및 하위 패키지에 있는 모든 클래스,  인자값이 0개 이상인 메서드가 포인트 컷

        execution(* delete*(*)) : 메서드 이름이 delete으로 시작하는 인자값이 1개인 메서드가 포인트 컷

        execution(* delete*(*,*)) : 메서드 이름이 delete로 시작하는 인자값이 2개인 메서드가 포인트 컷

        execution(* onj*(Integer, ..)) : 메서드 이름이 onj로 시작하고 첫번째 인자값의 타입이 Integer, 1개 이상의 매개변수를 갖는 메서드가 포인트 컷

        within(onj.spring.aop.*) : onj.spring.aop 패키지 내의 모든 메소드가 포인트 컷

        within(onj.spring.aop..*) : onj.spring.aop패키지 및 하위 패키지의 모든 메소드가 포인트 컷

        bean(oraclejava*) : 이름이 oraclejava로 시작되는 모든 빈의 메소드가 포인트 컷

        bean(*dataSource) || bean(*DataSource) : 빈 이름이 “dataSource” 나 “DataSource” 으로 끝나는 모든 빈의 메소드가 포인트 컷

        !bean(onjoraclejava) : onjoraclejava빈을 제외한 모든 빈의 메소드가 포인트 컷*/
