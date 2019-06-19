package com.hungrok.todolist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.format.DateFormat
import android.view.*
import android.widget.BaseAdapter
import android.widget.TextView
import io.realm.*
import io.realm.annotations.PrimaryKey
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    var useRealmDB = true

    val realm = Realm.getDefaultInstance()  // 객체획득

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar) // 참고 , ToolBar 가 아닌 ActionBar 사용

        System.out.printf("MainActivity-onCreate \n")

        // 추가 FAB , 이벤트 리스너객체 등록 (람다식 익명함수)
        fab.setOnClickListener { view ->
            var intent = Intent (this,EditActivity::class.java)
                startActivity(intent)

        }
        // ListView , 이벤트 리스너객체 등록
        listView.setOnItemClickListener { parent, view, position, id ->
            var intent = Intent (this,EditActivity::class.java)
            intent.putExtra ("id",id)
            startActivity(intent)
        }

        if(!useRealmDB)
            setupListView() // Using Local ArrayList
        else
            setupRealmDB() ; // Using Realm DataBase

    }

    override fun onResume() {
        super.onResume()
        System.out.printf("MainActivity-onResume \n")
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupListView(){

        // 1단계. data 객체생성
        var data = ArrayList<TodoSimple>()
        var todo1 = TodoSimple("2015-06-21", "Watching the TV1")
        var todo2 = TodoSimple("2015-06-22", "Watching the TV2")
        var todo3 = TodoSimple("2015-06-23", "Watching the TV3")
        var todo4 = TodoSimple("2015-06-24", "Watching the TV4")
        data.add(todo1)
        data.add(todo2)
        data.add(todo3)
        data.add(todo4)

        // 2단계 Adapter 생성 및 data 전달
        var adapter = CustomListAdapter(this, data)
        // 3단계 ListView-Adapter 연결
        listView.adapter = adapter
    }

    private fun setupRealmDB(){


        // 1단계. data 객체 획득  - OrderedRealmCollection
        val realmResult = realm!!.where<Todo>().findAll().sort("date", Sort.DESCENDING)

        // 2단계 Adapter 생성 및 data 전달
        val adapter = TodoListAdapter(realmResult)
        // 3단계 ListView-Adapter 연결
        listView.adapter = adapter
    }
}


// For ListAdapter 기초활용
private class CustomListAdapter(var context: Context, var items: ArrayList<TodoSimple>) : BaseAdapter() {

    override fun getCount(): Int {
        return items!!.size //returns total of items in the list
    }

    override fun getItem(position: Int): Any {
        return items!![position] //returns list item at the specified position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        // inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false)
        }

        // get current item to be displayed
        val currentItem = getItem(position) as TodoSimple

        // get the TextView for item name and item description
        val textViewItemName1 = convertView!!.findViewById(R.id.text1) as TextView
        val textViewItemName2 = convertView!!.findViewById(R.id.text2) as TextView

        //sets the text for item name and item description from the current item object
        textViewItemName1.setText(currentItem.date)
        textViewItemName2.setText(currentItem.things)

        // returns the view for the current row
        return convertView
    }
}


// 코틀린 class 선언시 , 필드멤버와 콘스트럭터가 함께 선언된다
private class TodoSimple(var date : String, var things : String) {
   /*  참고 JAVA style
       class TodoSimple{
           // 필드
           public String date = null ;
           public String things = null ;

           public TodoSimple(String date1, String things1){ // 콘스트럭터
              this.date = date1 ;
              this.things1 = things1 ;
           }
       }
    */
}

private class TodoSimple2(var date : String, var things : String, var icon: Int)
{

}

// For Realm DB Adapter
class TodoListAdapter(realmResult : OrderedRealmCollection<Todo>) :
    RealmBaseAdapter<Todo>(realmResult) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertview = convertView


        if(convertview == null){
            convertview = LayoutInflater.from(parent?.context).inflate(R.layout.item_todo, parent, false)
        }

        // adapterData is field of RealmBaseAdapter
        if (adapterData != null){
            val item = adapterData!![position]
            // get the TextView for item name and item description
            val textViewItemName1 = convertview!!.findViewById(R.id.text1) as TextView
            val textViewItemName2 = convertview!!.findViewById(R.id.text2) as TextView

            //sets the text for item name and item description from the current item object
            textViewItemName1.text = item.title
            textViewItemName2.text = DateFormat.format("yyyy/MM/dd",item.date)

        }

        return convertview!!
    }

    override fun getItemId(position: Int): Long {
        if (adapterData != null)
            return adapterData!![position].id

        return super.getItemId(position)
    }


}

open class Todo( // Realm Model Class (Table 구성) - 코틀린 style, open = 상속가능을 의미,
    @PrimaryKey var id: Long = 0,
    var title: String="",
    var date: Long = 0) : RealmObject() {

}