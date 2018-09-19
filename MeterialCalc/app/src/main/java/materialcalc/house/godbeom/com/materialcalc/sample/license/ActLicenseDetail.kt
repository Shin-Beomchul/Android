package materialcalc.house.godbeom.com.materialcalc.sample.license

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.act_license.*
import materialcalc.house.godbeom.com.materialcalc.R

class ActLicenseDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_license)

        libName.text = NPEmpt(intent.getStringExtra(Key_libName))
        licenseType.text = NPEmpt(intent.getStringExtra(Key_licenseType))
        ownerName.text = NPEmpt(intent.getStringExtra(Key_ownerName))
        gitUrl.text = NPEmpt(intent.getStringExtra(Key_gitUrl))
        licenseDetail.text = NPEmpt(intent.getStringExtra(Key_Detail))
    }


    companion object {
        const val Key_libName:String = "libName";
        const val Key_licenseType:String = "licenseType";
        const val Key_ownerName:String = "ownerName";
        const val Key_gitUrl:String = "gitUrl";
        const val Key_Detail:String = "detail";
    }


    fun NPEmpt(textL:String?):String{
        return if(textL !=null)  textL else "empt"
    }
}
