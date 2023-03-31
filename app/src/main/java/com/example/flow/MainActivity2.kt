package com.example.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity2 : AppCompatActivity() {
    val channel = Channel<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch {
            val data: Flow<Int> = producer()
            //data.collect{} - its called consumer;
            //when you comment out consumer, producer also stops emiting data
            data.collect {
                Log.d("KOTLIN_DEBUG - 1", it.toString())
            }
        }

        GlobalScope.launch {
            val data: Flow<Int> = producer()
            delay(2500)
            //data.collect{} - its called consumer;
            //when you comment out consumer, producer also stops emiting data
            data.collect {
                Log.d("KOTLIN_DEBUG - 2", it.toString())
            }
        }
    }

    fun producer() = flow<Int> {
        val list = listOf(1, 2, 3, 4, 5)
        list.forEach {
            delay(1000)
            emit(it) //producer
        }
    }
}