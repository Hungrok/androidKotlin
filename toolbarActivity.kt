package com.hungrok.mytoolbar

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    var imagesrc1 : Int = R.drawable.jpg1
    var imagesrc2 : Int = R.drawable.jpg2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        makeSBListener()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId){
            R.id.action_home -> {
                System.out.printf("action_homed called \n")
                return true
            }
            R.id.action_search -> {
                System.out.printf("action_search called \n")
                return true
            }
            R.id.action_game -> {
                System.out.printf("action_game called \n")
                return true
            }
            R.id.action_item1 -> {
                System.out.printf("action_item1 called \n")
                return true
            }
            R.id.action_item2 -> {
                System.out.printf("action_item2 called \n")
                return true
            }
            R.id.action_item3 -> {
                System.out.printf("action_item3 called \n")
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun makeSBListener(){

        switchButton.setOnCheckedChangeListener{ buttonView, isChecked ->  // 람다식
            if(isChecked) {

                imageView1.setImageResource(imagesrc2)
                imageView2.setImageResource(imagesrc1)
            }
            else {
                imageView1.setImageResource(imagesrc1)
                imageView2.setImageResource(imagesrc2)
            }
        }

    }
}
