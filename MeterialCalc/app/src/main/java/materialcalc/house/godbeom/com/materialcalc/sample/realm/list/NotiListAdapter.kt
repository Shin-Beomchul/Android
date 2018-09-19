package materialcalc.house.godbeom.com.materialcalc.sample.realm.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import materialcalc.house.godbeom.com.materialcalc.R
import materialcalc.house.godbeom.com.materialcalc.sample.realm.db.NotiADTO
import materialcalc.house.godbeom.com.materialcalc.sample.realm.db.RealmDBHelper

/**
 * Created by god on 2018. 7. 13..
 */

class NotiListAdapter(val mContext: Context) : RecyclerView.Adapter<NotiListAdapter.NotiBaseHolder>() {

    private val realmDB = RealmDBHelper()
    private var notis = ArrayList<NotiADTO>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NotiBaseHolder {
        return NotiHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_noti, parent, false));
    }



    override fun getItemCount(): Int {

        return notis.size
    }

    fun  getNoti(): ArrayList<NotiADTO> {
        return notis
    }
    fun setNoti(notis: ArrayList<NotiADTO>) {
        this.notis = notis
    }

    override fun onBindViewHolder(holder: NotiBaseHolder?, position: Int) {
        holder!!.bind(notis[position])

    }




    inner class NotiHolder(itemView: View?) : NotiBaseHolder(itemView) {

        var title: TextView = itemView!!.findViewById(R.id.noti_title)
        var subtitle: TextView = itemView!!.findViewById(R.id.noti_subtitle)


        override fun bind(notiADTO: NotiADTO) {
            title.text = notiADTO.title
            subtitle.text = notiADTO.category

            val mColors:IntArray= mContext.resources.getIntArray(R.array.colors)
            val categorys:Array<String> =realmDB.categorys

            var i:Int =0
             while(i < categorys.size){
                 if(categorys[i] == notiADTO.category){
                     subtitle.setBackgroundColor(mColors[i])
                 }
                 i++
             }


        }
    }


    inner abstract class NotiBaseHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(notiADTO: NotiADTO)
    }

}