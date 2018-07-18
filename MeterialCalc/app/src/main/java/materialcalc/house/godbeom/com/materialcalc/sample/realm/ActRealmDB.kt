package materialcalc.house.godbeom.com.materialcalc.sample.realm

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.yalantis.filter.adapter.FilterAdapter
import com.yalantis.filter.listener.FilterListener
import com.yalantis.filter.model.FilterModel
import com.yalantis.filter.widget.Filter
import com.yalantis.filter.widget.FilterItem
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmResults
import kotlinx.android.synthetic.main.act_realm_db.*
import materialcalc.house.godbeom.com.materialcalc.R
import materialcalc.house.godbeom.com.materialcalc.sample.realm.db.NotiADTO
import materialcalc.house.godbeom.com.materialcalc.sample.realm.db.NotiLinkTextADTO
import materialcalc.house.godbeom.com.materialcalc.sample.realm.db.RealmDBHelper
import materialcalc.house.godbeom.com.materialcalc.sample.realm.list.FiltersListItemAnimator
import materialcalc.house.godbeom.com.materialcalc.sample.realm.list.NotiListAdapter

class ActRealmDB : AppCompatActivity(),FilterListener<ActRealmDB.Tag>{
    var cnt =0
    private  val listAdapter = NotiListAdapter(this)
    private var notis: RealmResults<NotiADTO>? = null
    private val realmDB = RealmDBHelper()

    private var mColors: IntArray ?=null
    private var mCategory: Array<String>?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_realm_db)

        mColors= resources.getIntArray(R.array.colors)
        mCategory = realmDB.categorys

        val filter: Filter<Tag> = findViewById(R.id.filter)
        filter.adapter = Adapter(getTags())
        filter.noSelectedItemText = "필터"
        filter.listener = this
        filter.build()

        //gets
        notis = realmDB.allNotis


        //init
        val linearMgr = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        linearMgr.reverseLayout =true
//        linearMgr.stackFromEnd =true
        recyclerView.layoutManager = linearMgr

        recyclerView.adapter = listAdapter
        recyclerView.itemAnimator = FiltersListItemAnimator()
        listAdapter.setNoti((notis?.toCollection(ArrayList())!!))



        //TODO DB 업데이트 시 호출
        notis?.addChangeListener({t, changeSet ->
            listAdapter.setNoti((notis!!.toCollection(arrayListOf())))
            listAdapter.notifyDataSetChanged()
            notis?.size?.let { recyclerView.smoothScrollToPosition(it) }
        })

        //TODO live UI change Listener
        //delete All
         btndeleteTable.setOnClickListener({
            Realm.getDefaultInstance()
                    .executeTransaction {
                        it.where(NotiADTO::class.java)
                                .findAll()
                                .deleteAllFromRealm()
                    }
        })


        //추가
       btnAddNoti.setOnClickListener({

            //TODO in Another Thread Insert
            Thread({
                var texts: RealmList<NotiLinkTextADTO> = RealmList()
                var text1: NotiLinkTextADTO? = NotiLinkTextADTO()
                var text2: NotiLinkTextADTO? = NotiLinkTextADTO()
                text1?.text = "네이버"
                text1?.link = "www.naver.com"


                text2?.text = "구글"
                text2?.link = "www.google.com"

                texts.add(text1)
                texts.add(text2)

                cnt ++
                when(cnt){
                    1 ->  realmDB.addNoti(NotiADTO("push", "각종 알림을 선택하세요 -push", texts))
                    2 ->  realmDB.addNoti(NotiADTO("beom", "각종 알림을 선택하세요 -beom", texts))
                    3 ->  realmDB.addNoti(NotiADTO("chul", "각종 알림을 선택하세요 -chul", texts))
                    4 ->  realmDB.addNoti(NotiADTO("god", "각종 알림을 선택하세요 -god", texts))
                }




            }).start()
        })
        Toast.makeText(applicationContext, "사이즈 ? " + notis?.size, Toast.LENGTH_LONG).show()
    }


    private fun calculateDiff(oldList: List<NotiADTO>, newList: List<NotiADTO>) {

        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int {
                return oldList.size
            }

            override fun getNewListSize(): Int {
                return newList.size
            }

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition].equals(newList[newItemPosition])
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition].equals(newList[newItemPosition])
            }
        }).dispatchUpdatesTo(listAdapter)
    }

    override fun onFilterSelected(item: Tag) {

       /* notis = realmDB.getNitiByCategory(item.getText())
        listAdapter.setNoti(notis!!.toCollection(ArrayList()))
        listAdapter.notifyDataSetChanged()*/
    }


    override fun onFilterDeselected(item: Tag) {
    }


    override fun onFiltersSelected(filters: java.util.ArrayList<Tag>) {

        val newQuestions = findNotiByCagegorys(filters)
        val oldQuestions = listAdapter.getNoti()
        listAdapter.setNoti(newQuestions)
        calculateDiff(oldQuestions, newQuestions)
    }

    private fun findNotiByCagegorys(filters : ArrayList<Tag>): ArrayList<NotiADTO>{

        var resultsNotis:ArrayList<NotiADTO> = ArrayList()

        notis?.forEach { noti: NotiADTO ->
            filters.forEach { filter ->
                if(filter.getText().equals(noti.category)){
                    resultsNotis.add(noti)
                }
            }
        }
        return resultsNotis
    }


    override fun onNothingSelected() {
        if(recyclerView!=null){
            listAdapter.setNoti(notis?.toCollection(ArrayList())!!)
            listAdapter.notifyDataSetChanged()
        }
    }
    override fun onStop() {
        notis?.removeAllChangeListeners()
        super.onStop()
    }


    internal inner class Adapter(items: List<Tag>) : FilterAdapter<Tag>(items) {

        override fun createView(position: Int, item: Tag): FilterItem {
            val filterItem = FilterItem(this@ActRealmDB)

            filterItem.strokeColor = mColors!![0]
            filterItem.textColor = mColors!![0]
            filterItem.cornerRadius = 14F
            filterItem.checkedTextColor = ContextCompat.getColor(this@ActRealmDB, android.R.color.white)
            filterItem.color = ContextCompat.getColor(this@ActRealmDB, android.R.color.white)
            filterItem.checkedColor = mColors!![position]
            filterItem.text = item.getText()
            filterItem.deselect()

            return filterItem
        }
    }


    private fun getTags(): List<Tag> {
        val tags = ArrayList<Tag>()

        for (i in mCategory!!.indices) {
            tags.add(Tag(mCategory!![i], mColors!![i]))
        }
        return tags
    }



    class Tag(private var category: String, var color: Int) : FilterModel {

        override fun getText(): String {
            return category
        }

        fun setText(text: String) {
            this.category = text
        }

        override fun equals(o: Any?): Boolean {
            if (this === o) return true
            if (o !is Tag) return false

            val tag = o as Tag?

            return if (color != tag!!.color) false else getText() == tag.getText()

        }

        override fun hashCode(): Int {
            var result = getText().hashCode()
            result = 31 * result + color
            return result
        }

    }
}
