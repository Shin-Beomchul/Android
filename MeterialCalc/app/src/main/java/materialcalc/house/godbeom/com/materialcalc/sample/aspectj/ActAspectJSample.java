package materialcalc.house.godbeom.com.materialcalc.sample.aspectj;

import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import materialcalc.house.godbeom.com.materialcalc.R;
import materialcalc.house.godbeom.com.materialcalc.sample.aspectj.threadinjector.bBackground;
import materialcalc.house.godbeom.com.materialcalc.sample.aspectj.threadinjector.bUiThread;

public class ActAspectJSample extends AppCompatActivity implements View.OnClickListener {


	@BindView(R.id.ClickMe)
	Button ClickMe;

	@BindView(R.id.btnMainHooker)
	Button btnMainHooker;

	@BindView(R.id.btnSubHooker)
	Button btnSubHooker;

	@BindView(R.id.tv)
	TextView tv;

	@BindView(R.id.tvUiObject)
	TextView tvUiObject;


	StringBuilder sb = new StringBuilder();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act_aspect_jsample);
		ButterKnife.bind(this);
		ClickMe.setOnClickListener(this);
		btnMainHooker.setOnClickListener(this);
		btnSubHooker.setOnClickListener(this);

		Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate); //무한회전 (Ui Thread 상태 확인)
		tvUiObject.setAnimation(animation);
	}


	@Override
	public void onClick(View view) {
		int id = view.getId();

		switch (id) {
			case R.id.ClickMe:
				tv.setText(sb.append("클릭 !").append("\n").toString());
				break;
			case R.id.btnMainHooker:
				delayedUiThread(2000);
				break;
			case R.id.btnSubHooker:
				subThreadStart();
				break;
		}
	}

	/**포인트컷 조인-> SubThread 생성 ->함수 스코프 실행.
	 * :Annotation 제거 : UiCheck SpinObject Stop
	 * */
	@bBackground
	public void subThreadStart() {
		for (int i = 0; i < 5; i++) {
			mainUiThreadPrinter(i);
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**Annotation 삭제 시 ui Thread 접근 불가. (죽지 않는 이유 : 포인트컷에 예외 처리가 되어 있기 때문.)
	 *  i : SubThread 에서 처리된 값을 핸들러 없이  Ui스레드로 전달 .
	 */
	@bUiThread
	public void mainUiThreadPrinter(int i) {
		tv.setText(sb.append("Hi here is ").append(threadChecker()).append("Count " + i).append("\n").toString());
	}

	private String threadChecker() {
		String thread = null;
		if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
			thread = "[Ui] Thread !";
		} else {
			thread = "[sub] Thread !";
		}

		return thread;
	}


	/**2초간 Ui 정지.
	 * */

	@bUiThread
	public void delayedUiThread(int uiBlockTime){
		tv.setText(sb.append("BlockUiThread "+uiBlockTime +"Ms").append("\n").toString());
		try {
			Thread.sleep(uiBlockTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
