package materialcalc.house.godbeom.com.materialcalc.sample.utill

import android.content.Context

/**
 * Created by god on 2018. 8. 10..
 */

class Spref{
    val PREFS_FILENAME = "data"

    fun setString(context : Context, key : String, value : String) {
        val prefs = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
        val editor = prefs!!.edit()
        editor.putString(key, value).apply()
    }

    fun getString(context : Context, key : String) : String{
        val prefs = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
        return prefs.getString(key, "")
    }

    fun setInt(context : Context, key : String, value : Int) {
        val prefs = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
        val editor = prefs!!.edit()
        editor.putInt(key, value).apply()
    }

    fun getInt(context : Context, key : String) : Int{
        val prefs = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
        return prefs.getInt(key, 0)
    }


    fun setBool(context : Context, key : String, value : Boolean) {
        val prefs = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
        val editor = prefs!!.edit()
        editor.putBoolean(key, value).apply()
    }

    fun getBool(context : Context, key : String) : Boolean{
        val prefs = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(key, false)
    }

}

