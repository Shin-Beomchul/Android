package materialcalc.house.godbeom.com.materialcalc

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import materialcalc.house.godbeom.com.materialcalc.sample.FCMNoti.ActFCM
import materialcalc.house.godbeom.com.materialcalc.sample.baidu.push.CloudMessagingIntentService
import materialcalc.house.godbeom.com.materialcalc.sample.license.ActLicenseList
import materialcalc.house.godbeom.com.materialcalc.sample.realm.ActRealmDB
import materialcalc.house.godbeom.com.materialcalc.sample.remoteconfig.ActRemoteConfig
import materialcalc.house.godbeom.com.materialcalc.sample.ripple.ActFreeRippleSample
import materialcalc.house.godbeom.com.materialcalc.sample.section.ActSectionSample
import materialcalc.house.godbeom.com.materialcalc.sample.stickys.ActStickySample

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnRipple.setOnClickListener { view -> startActivity(Intent(this@MainActivity, ActFreeRippleSample::class.java))}
        sectionRecyclerView.setOnClickListener { view -> startActivity(Intent(this@MainActivity, ActSectionSample::class.java)) }
        btnSticky.setOnClickListener { view -> startActivity(Intent(this@MainActivity, ActStickySample::class.java)) }
        btnConfig.setOnClickListener { view -> startActivity(Intent(this@MainActivity, ActRemoteConfig::class.java)) }
//        btnAspectJ.setOnClickListener(View.OnClickListener { view -> startActivity(Intent(this@MainActivity, ActAspectJSample::class.java)) })
        btnNotiRealm.setOnClickListener(View.OnClickListener { view -> startActivity(Intent(this@MainActivity, ActRealmDB::class.java)) })
        btnNotification.setOnClickListener {view -> startActivity(Intent(this@MainActivity, ActFCM::class.java)) }
        licenseInfo.setOnClickListener{ view -> startActivity(Intent(this, ActLicenseList::class.java)) }

        val intent = Intent(applicationContext, CloudMessagingIntentService::class.java)
        applicationContext.startService(intent)
    }

}
