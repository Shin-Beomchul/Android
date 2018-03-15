package materialcalc.house.godbeom.com.materialcalc.sample.purelists;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import materialcalc.house.godbeom.com.materialcalc.R;

/**
 * 단순 리스트들의 조합으로 구성
 *
 * */
public class ActMultiListSample extends AppCompatActivity {
	/*

	--------------------ScrollView-----------------------
	   -----------------------------------------
	   |		      RecyclerView		       |
	   -----------------------------------------

	    -----------------------------------------
	   |		        Fragment	 	       |
	   -----------------------------------------

	    -----------------------------------------
	   |		      RecyclerView		       |
	   -----------------------------------------

	     ----------------------------------------
	   |		        Pager				    |
	   -----------------------------------------

  --------------------ScrollView-----------------------


	*/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act_multi_list_sample);
	}
}
