package materialcalc.house.godbeom.com.materialcalc.sample.license

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.act_license_list.*
import materialcalc.house.godbeom.com.materialcalc.R

class ActLicenseList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_license_list)


        aspectJ.setOnClickListener({
            startLicenseDetail("AspectJ"
                    , "Apache License, Version 2.0"
                    , "uPhyca"
                    , "https://github.com/uPhyca/gradle-android-aspectj-plugin"
                    , "Copyright 2014 uPhyca, Inc.\n" +
                    "\n" +
                    "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                    "you may not use this file except in compliance with the License.\n" +
                    "You may obtain a copy of the License at\n" +
                    "\n" +
                    "   http://www.apache.org/licenses/LICENSE-2.0\n" +
                    "\n" +
                    "Unless required by applicable law or agreed to in writing, software\n" +
                    "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                    "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                    "See the License for the specific language governing permissions and\n" +
                    "limitations under the License."
            )
        })


        glide.setOnClickListener({
            startLicenseDetail("Glide"
                    , "Apache License, V ersion 2.0"
                    , "Twitter"
                    , "https://github.com/bumptech/glide"
                    , "BSD, part MIT and Apache 2.0. See the LICENSE file for details."
            )
        })


        sticky.setOnClickListener({
            startLicenseDetail("Sticky"
                , "MIT License"
                , "ShamylZakariya"
                , "https://github.com/ShamylZakariya/StickyHeaders"
                , "The MIT License (MIT)\n" +
                    "\n" +
                    "Copyright (c) 2016 Shamyl Zakariya\n" +
                    "\n" +
                    "Permission is hereby granted, free of charge, to any person obtaining a copy\n" +
                    "of this software and associated documentation files (the \"Software\"), to deal\n" +
                    "in the Software without restriction, including without limitation the rights\n" +
                    "to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\n" +
                    "copies of the Software, and to permit persons to whom the Software is\n" +
                    "furnished to do so, subject to the following conditions:\n" +
                    "\n" +
                    "The above copyright notice and this permission notice shall be included in all\n" +
                    "copies or substantial portions of the Software.\n" +
                    "\n" +
                    "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\n" +
                    "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\n" +
                    "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\n" +
                    "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\n" +
                    "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\n" +
                    "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE\n" +
                    "SOFTWARE.."
        ) })

        section.setOnClickListener({
            startLicenseDetail("sectionedRecyclerViewAdapter"
                    , "MIT License"
                    , "luizgrp"
                    , "https://github.com/luizgrp/SectionedRecyclerViewAdapter"
                    , "The MIT License (MIT)\n" +
                    "\n" +
                    "Copyright (c) 2016 Gustavo Pagani\n" +
                    "\n" +
                    "Permission is hereby granted, free of charge, to any person obtaining a copy\n" +
                    "of this software and associated documentation files (the \"Software\"), to deal\n" +
                    "in the Software without restriction, including without limitation the rights\n" +
                    "to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\n" +
                    "copies of the Software, and to permit persons to whom the Software is\n" +
                    "furnished to do so, subject to the following conditions:\n" +
                    "\n" +
                    "The above copyright notice and this permission notice shall be included in all\n" +
                    "copies or substantial portions of the Software.\n" +
                    "\n" +
                    "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\n" +
                    "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\n" +
                    "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\n" +
                    "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\n" +
                    "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\n" +
                    "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE\n" +
                    "SOFTWARE."
            )
        })

    }


    fun startLicenseDetail(libName: String, licenseType: String, CodeOwner: String, gitUrl: String, detail: String): Unit {
        var detailStartIntent = Intent(this@ActLicenseList, ActLicenseDetail::class.java)
        detailStartIntent.putExtra(ActLicenseDetail.Key_libName, libName)
        detailStartIntent.putExtra(ActLicenseDetail.Key_ownerName, CodeOwner)
        detailStartIntent.putExtra(ActLicenseDetail.Key_gitUrl, gitUrl)
        detailStartIntent.putExtra(ActLicenseDetail.Key_licenseType, licenseType)
        detailStartIntent.putExtra(ActLicenseDetail.Key_Detail, detail)
        startActivity(detailStartIntent);
    }
}
