package com.hungrok.kotlinexpr

import android.content.Context
import android.content.ContextWrapper
import android.view.ContextMenu
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.*


// Global variable , declaration-initialize
// KOTLIN data 의 특징
// 1. 모든 data 의 data-type 은 참조형 (reference) 이다
// 2. Static data-type 특징을 지니며, 암묵적인 data type 표현이 가능하다
// 3. function 도 객체이며, reference 로 표현 될 수 있다
// 4. 모든 data 는 참조형이기 때문에, null safety 를 지향한다
var gvar1 : Int = 10    // 전역변수
var gvar2 = 20          // 전역변수 - 암묵적 data-type
val gstr1 = "HelloWorld\n" // 전역상수
var gstr2 : String? = null // null safety - nullable, data-type 에 ? 을 추가한다
const val gstr3 : String = "abc"  // 전역상수 - static final (모듈내에서만 전역)
fun nullTest1(param1:String){}
fun nullTest2(){
    nullTest1(gstr1!!)  // null guaranty - assertion if null
    nullTest1(gstr1?.toString()) // null safety call, gstr1 이 null 아니면 ...
}
// 변수의 type casting
var gvar3 : Byte = 10
var gvar4 : Number = gvar3 // 자동 type casting as Number is super of Byte
//var gvar5 : Int = gvar3    // 자동 type casting 불가능
var gvar5 : Int = gvar3 as Int  // 자동 type casting
var obj1 = MyCls1() // class data-type 에 대한 reference
var obj2 = MyCls1() as MyCls2  // 강제 type casting


// Global function
// 함수 declaration 방법
// 1. 일반 함수
// 2. Function literal : 람다식, 익명함수
// 3. Function Type : 고차함수 parameter 에 사용되는 함수 type (ADT)
// 고차함수 : 펑션을 입력매개변수 혹은 리턴으로 사용하는 함수

// 일반함수
fun func1(param1:Int, param2:String):Unit {System.out.printf("",gstr1)}
var funref1 = ::func1  // function reference
// 고차함수 및 Function Literal
fun applyop(x: Int, y: Int, op: (Int, Int) -> Int): Int = op(x, y)  // 고차함수
val sum1: (Int, Int) -> Int = { x, y -> x + y }     // 람다식 함수객체 reference
val ret1 = sum1(2,3)                                // 일반 함수로 사용
val sum2 =  fun(x: Int, y: Int) = x+y      // 익명 함수객체 reference
val ret2 = sum2(2,3)                       // 일반 함수로 사용
fun sum3(x: Int, y: Int) = x+y     // 일반함수

fun test(){ // 고차함수 argument test
    applyop(2, 3, sum1) // 고차함수 argument - 람다식 reference
    applyop(2, 3, sum2) // 고차함수 argument - 익명함수 reference
    // applyop(2, 3, sum3) // Error as 일반함수는 reference 가 아니다
    applyop(2, 3, ::sum3) // 고차함수 argument - 일반함수

    applyop (2,3, { x, y -> x + y }) // 고차함수 argument - 람다식 expression
    applyop (2,3,  fun(x: Int, y: Int) = x+y) // 고차함수 argument - 익명함수 expression
}
// function 기타
inline fun func2() {} // 고차함수에 대한 closure overhead 개선목적
class globalTest{ // to test global variable and function access
    // CAN access all the global variable and function in class
    var field1 = gvar1
    var field2 = sum1(2,3)
    fun method1(){
        sum1(2,3) // lambda
        sum2(2,3) // 익명함수
        sum3(2,3) // 일반함수
    }
}

// Class declaration-definition
// JAVA 와 유사하나  간편성 및 개선을 도모
// -. 접근지정자 와 속성은 (default) 로 public 과 final 을 사용
// -. 접근지정자 : public , private, internal (java 의 default 해당) , protected
// -. 사용 용도별로 class 앞에 속성 keyword 신설 : open, data, inner, sealed
// -. Java 의 속성값 (static, final) 중 static 은 미사용
// -. Class 유형은 JAVA 와 마찬가지로 3가지 : class, abstract class, interface
// -. 상속관계 keyword : JAVA extends, implements 를 ‘:’ 로 대체
// -. Constructor : primary constructor, secondary constructor, init{}
// -. Override Method : Keyword - open, abstract, override
class MyCls1{}   // same as public final class with JAVA - 상속불가
open class MyCls2{open fun method1(){}} // same as public class with JAVA - 상속가능
class MyCls3:MyCls2() {override fun method1(){}}  // Class-Class 상속 (extends)
abstract class MyCls4{abstract fun method2()}   // abstract class - abstract method
interface MyInt1{fun method3()}        // Interface - abstract method
interface MyInt2:MyInt1{} // Interface 상속 (extends)
class MyCls5: MyInt2{override fun method3(){}}  // Class - Interface (implements)
class MyCls6{
    val outer = 10
    inner class InnerCls1{val inner = outer}  // Inner class - access outer class member
}
// With field initialize - primary constructor
class MyCls7(val field1:Int, val field2:Int, val field3:String){}
// Data-set class, componentX(), copy() method
data class MyCls8(val field1:Int, val field2:Int, val field3:String){}
sealed class MyCls9{}  // enum class extension
fun testClass1(){
    var obj1 = MyCls3()
    var obj2 = MyCls7(10,20,"abc")
    System.out.printf("%d",obj2.field1) ;
    var obj3 = MyCls8(10,20,"abc")
    var field1 = obj3.component1()
    var obj4 = obj3.copy()
    var obj5 = MyCls6::InnerCls1
}
// Class 세부특징
// 1. runtime 에 class 에 method 추가 가능
// 2. 필드멤버 지연 초기화 - lateinit, lazy keyword
// 3. JAVA 와 호환 - object keyword
// 4. JAVA class 사용시 - filed 직접접근 (편법)
fun MyCls3.addFunc() {System.out.printf("addFunc")} // Class 에 method 추가
fun classAddTest(){
    val obj1 = MyCls3()
    obj1.addFunc()
}
class lateInit{ // 필드멤버 지연 초기화
    lateinit var obj1: MyCls3     // 지연 필드멤버 선언
    fun method1(){obj1 = MyCls3()} // 초기화 적용

    val obj2: MyCls3 by lazy { MyCls3()} // 지연 필드멤버 선언 및 초기화 (언제 호출되는지 ?), lazy {람다식}

}
// object - sinlgTone class
object SingleTone{}
// object - 익명 class 표현
fun setOnClickListener1 (listener: MyInt1){}
fun setOnClickListener2 (listener: View.OnClickListener){}
fun testClass2(){
    setOnClickListener1(object: MyInt1 { // 익명 class with Kotlin interface
        override fun method3(){;}})
    setOnClickListener2(object: View.OnClickListener { // 익명 class with Java Interface
        override fun onClick(v:View){;}})
}
class companionObject{ // object - static 멤버
    companion object {
        const val staticfinal1 = 10       // static상수 - only primitive or string
        const val staticfinal2 = "abcde" // static상수 - only primitive or string

        @JvmField val staticobj = MyCls3() // 객체에 대한 static 변수
        @JvmStatic fun staticMethod1(){}    // static method
    }
}
fun testClass3(){ // to test companion object - same with Java static member access
    val staticfinal1 = companionObject.staticfinal1
    companionObject.staticobj.method1()
    companionObject.staticMethod1()
}
// Class 특징4
// Java class 를 사용시 특징 - private field 에 직접 접근하는 것처럼 한다 (편법)
// Kotlin class 에는 적용이 안된다
class testClass4{
    private var var1 = 10
    fun setVar1(a: Int){ this.var1=a}
    fun getVar1():Int {return this.var1}
}
fun testJavaClass(){
    var obj1 = testClass4()   // Kotlin class
    // obj1.var1 = 10         // ERROR with Kotlin
    // var int1 = obj1.var1   // ERROR with Kotlin

    var obj2 = TextView(this) // Java class
    obj2.gravity = 10 ; // Direct Field access via setter and getter
}


// 람다식
var lambda1 = { System.out.printf("NoneInputReturn")} // none input and return
var lambda2: () -> String = { "String return"} // none input and String return
var lambda2b = { "String return"} // none input and String return 추론
var lambda3: (Int) -> Unit = { x -> x.toString() } // 정규식
//var lambda3:  Int -> Unit = { x -> x.toString() } // ERROR, must ()
//var lambda3: (Int) -> Unit = x -> x.toString()  // ERROR, must {}
var lambda3b = { x:Int -> x.toString() } // data-type 추론 - 생략
val lambda4: (Int, Int) -> Int = { x, y -> x + y } // 정규식
val lambda4b = { x:Int, y:Int -> x + y } // data-type 추론 - 생략
val str1 = lambda3(2)
val sumresult = lambda4(2,3)
fun MyCls3.addSum1() = lambda4   // class add function 에 대입가능
fun MyCls3.addSum2() = { x:Int, y:Int -> x + y }   // class add function 에 대입가능

// 자바를 사용하는 람다식
// Java 인터페이스가 지니는 추상메쏘드에 대하여 익명함수 객체화
// 조건 : 자바 메소드 (자바 인터페이스) - kotlin 함수 (자바 인터페이스) 에는 적용 안된다
// 인터페이스의 추상함수를 마치 고차함수로 처리
// 인터페이스가 지니는 추상함수의 parameter 에 type 이 명시되어 있음으로, type 추론 가능
// button.setOnClickListener (View.OnClickListener listener){}
// edittext.setOnClickListener (OnEditorActionListener listener){}
fun testInterfaceLambda(){
    lateinit var button: Button
    button.setOnClickListener{ view-> System.out.printf("%s",view) }
    button.setOnClickListener{ System.out.printf("cccc") } // 사용되는 argument 생략가능


    lateinit var edittext: EditText
    // 입력매개변수 3개, return boolean
    edittext.setOnEditorActionListener {_, actionId, _ ->
       if (actionId !=null) true
        else    false
    }
}


// Scoping function (범위함수) Test
// 1. 고차함수 - kotlin 이 제공하는 function 과 동일
inline fun <T> T.apply2(block: T.() -> Unit): T {
    block() // block 함수객체 실행, block 함수객체의 모양은 function-type 대로
    return this // this 는 T 로 지정된 객체
}
inline fun <T, R> T.run2(block: T.() -> R): R {
    return block() // block 함수객체를 실행하고 그 결과를 return 한다
}
inline fun <T, R> T.let2(block: (T) -> R): R {
    return block(this) // block 함수객체의 매개변수 인자로 this 를 주고 실행 - 그 결과를 return
}
inline fun <T> T.also2(block: (T) -> Unit): T {
    block(this) // block 함수객체의 매개변수 인자로 this 를 주고 실행
    return this // this 를 return
}
inline fun <T, R> with2(receiver: T, block: T.() -> R): R {
    return receiver.block()
}

// Data-type
class Person{
    var name:String = "unknown" ; var pid:String = "unknown"
    var mylist: LinkedList<Int> = LinkedList<Int>()
    fun method1():String{ return "abc"}
    fun method2():LinkedList<Int>{ return mylist}
}
//
fun testScoping(){
    var person = Person()
    // 접근 test, Person().apply2 == person.apply2{}
    person.apply2{} // 마치 apply2 를 Person 의 멤버처럼 사용가능 (T.x 로 되어진 function)
    person.run2{} ; person.let2{} ; person.also2{} ; with2(person){}
    // 사용
    var person1:Person = Person().apply2{ // this: object
            name = "hong1" ; pid = "710912-1654321" } // return this , 객체 field (property) 초기화시 사용

    var str1:String = Person().run2 { // this: object
        method1() } // return function expression, 객체가 지니는 멤버를 수행시 사용

    person1.method2().let2 { // this: @class, it: object
        item -> item.add(10) } // return function expression, 객체의 멤버 null check

    var person4:Person = Person().also2 { // this: @class, it: object
        item -> item.mylist } // return this, 객체의 멤버 유효성 검사

}






