package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    var lastNumeric:Boolean = false
    var lastDecimal:Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
        val tvinput = findViewById<TextView>(R.id.tvInput)

        tvinput.append((view as Button).text)
        lastNumeric=true
    }
    fun onClear(view: View){
        val tvinput = findViewById<TextView>(R.id.tvInput)

    tvinput.text=""
        lastNumeric=false
        lastDecimal=false
    }

    fun onDecimal(view: View){
        val tvinput = findViewById<TextView>(R.id.tvInput)

        if(lastNumeric && !lastDecimal){
            tvinput.append(".")
            lastDecimal=true
        }

    }
    fun onOperator(view: View){
        val tvinput = findViewById<TextView>(R.id.tvInput)

        if (lastNumeric && !isOperatorAdded(tvinput.text.toString()))
        {
            tvinput.append((view as Button).text)
            lastDecimal=false
            lastNumeric=false
        }
    }

    fun onEqual(view: View){
        val tvinput = findViewById<TextView>(R.id.tvInput)

        if (lastNumeric){
           var tvValue= tvinput.text.toString()

            var prefix = ""

            try {


                if (tvValue.startsWith("-")){
                   tvValue= tvValue.substring(1)
                    prefix="-"
                }
                if (tvValue.contains("-")) {
                    val tvSplit = tvValue.split("-")
                    var one = tvSplit[0]
                    val two = tvSplit[1]

                    if (prefix.isNotEmpty()){
                        one =prefix+one
                    }


                    tvinput.text= checkZero((one.toDouble() - two.toDouble()).toString())
                }
                else  if (tvValue.contains("+")) {
                    val tvSplit = tvValue.split("+")
                    var one = tvSplit[0]
                    val two = tvSplit[1]

                    if (prefix.isNotEmpty()){
                        one =prefix+one
                    }


                    tvinput.text= checkZero((one.toDouble() + two.toDouble()).toString())
                }
                else  if (tvValue.contains("/")) {
                    val tvSplit = tvValue.split("/")
                    var one = tvSplit[0]
                    val two = tvSplit[1]

                    if (prefix.isNotEmpty()){
                        one =prefix+one
                    }


                    tvinput.text= checkZero((one.toDouble() / two.toDouble()).toString())
                }
                else  if (tvValue.contains("*")) {
                    val tvSplit = tvValue.split("*")
                    var one = tvSplit[0]
                    val two = tvSplit[1]

                    if (prefix.isNotEmpty()){
                        one =prefix+one
                    }


                    tvinput.text= checkZero((one.toDouble() * two.toDouble()).toString())
                }



            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun checkZero(result:String):String{
        var value = result

        if (value.contains(".0")){
            return value.substring(0,value.length-2)
        }
    else return value
    }


    private fun isOperatorAdded(value: String):Boolean{
        return if (value.startsWith("-")){ false}
        else{
            value.contains("/")
            value.contains("-")
            value.contains("+")
            value.contains("*")
        }
    }
}
