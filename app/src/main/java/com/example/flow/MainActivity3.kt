package com.example.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivity3 : AppCompatActivity() {
    val TAG:String = "KOTLIN_DEBUG"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        GlobalScope.launch(Dispatchers.Main) {
            getNotes()
                .map {
                    FormattedNote(it.isActive, it.title.uppercase(), it.description)
                }
                .filter {
                    it.isActive
                }
                .collect{
                    Log.d(TAG, it.toString())
                }
        }
    }

    private fun producer(): Flow<Int> {
        return flow {
            val list = listOf(1,2,3,4,5)
            list.forEach{
                delay(1000)
                emit(it)
            }
        }
    }
}