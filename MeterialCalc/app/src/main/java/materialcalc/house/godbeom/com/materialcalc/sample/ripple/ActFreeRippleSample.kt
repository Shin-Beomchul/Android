package materialcalc.house.godbeom.com.materialcalc.sample.ripple

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_act_free_ripple_sample.*
import materialcalc.house.godbeom.com.materialcalc.R

class ActFreeRippleSample : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppThemeRippleGREEN)
        //		setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_act_free_ripple_sample)

        fl_activity_main_background.setOnClickListener {
            Toast.makeText(applicationContext,
                    "Clicked FrameLayout (Background)", Toast.LENGTH_SHORT)
                    .show()
        }

        fl_activity_main_foreground.setOnClickListener {
            Toast.makeText(applicationContext,
                    "Clicked FrameLayout (Foreground)", Toast.LENGTH_SHORT)
                    .show()
        }

        cv_activity_main.setOnClickListener {
            Toast.makeText(applicationContext,
                    "Clicked CardView", Toast.LENGTH_SHORT)
                    .show()
        }
    }
}
