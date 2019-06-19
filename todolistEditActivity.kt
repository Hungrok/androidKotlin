package com.hungrok.todolist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_edit.*
import java.util.*

class EditActivity : AppCompatActivity() {

    val calendar : Calendar = Calendar.getInstance() // 객체획득
    val realm = Realm.getDefaultInstance()  // 객체획득


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)


        // MainActivity 에서 보낸 Intent 내용으로 추가모드 / 갱신모드 판단
        val id = intent.getLongExtra("id",-1L)
        if(id==-1L) insertMode() // ListView 에서 특정 item click
        else updateMode(id) // FAB(추가) 에서 click

        // 이벤트 리스너객체 등록 (람다식 익명함수)
        calendarView.setOnDateChangeListener{ view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }
    }

    private fun insertMode(){
        //deleteFab.visibility = View.GONE  // error as ... replace as hide()
        System.out.printf("Insert mode \n")
        deleteFab.hide()
        doneFab.setOnClickListener{
            insertTodo()
        }
    }
    private fun updateMode(id:Long){
        System.out.printf("Update mode \n")
        val todo = realm!!.where<Todo>().equalTo("id",id).findFirst()!!
        todoEditText.setText(todo.title)
        calendarView.date = todo.date

        doneFab.setOnClickListener { updateTodo(id) }
        deleteFab.setOnClickListener { deleteTodo(id) }
    }
    private fun nextId(): Int{

        val maxId = realm!!.where<Todo>().max("id")
        if(maxId != null)
            return maxId.toInt() + 1

        return 0
    }
    private fun insertTodo(){ // DataBase Insert

        realm!!.beginTransaction()
        val newItem = realm!!.createObject<Todo> (nextId())
        newItem.title = todoEditText.text.toString()
        newItem.date = calendar.timeInMillis
        System.out.printf("Realm-Insert done \n")
        realm!!.commitTransaction()
    }
    private fun updateTodo(id:Long){  // DataBase Update

        realm!!.beginTransaction()
        val updateItem = realm!!.where<Todo>().equalTo("id",id).findFirst()!!

        updateItem.title = todoEditText.text.toString()
        updateItem.date = calendar.timeInMillis
        System.out.printf("Realm-Update done \n")
        realm!!.commitTransaction()
    }
    private fun deleteTodo(id:Long){  // DataBase Delete

        realm!!.beginTransaction()
        val deleteItem = realm!!.where<Todo>().equalTo("id",id).findFirst()!!

        deleteItem.deleteFromRealm()
        System.out.printf("Realm-Delete done \n")
        realm!!.commitTransaction()
    }
}
