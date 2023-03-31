package com.example.flow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val channel = Channel<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val job = GlobalScope.launch {
            val data: Flow<Int> = producer()
            //data.collect{} - its called consumer;
            //when you comment out consumer, producer also stops emiting data
            data.collect {
                Log.d("KOTLIN_DEBUG", it.toString())
            }
        }

        GlobalScope.launch {
            delay(3500)
            job.cancel()
        }
    }

    fun producer() = flow<Int> {
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        list.forEach {
            delay(1000)
            emit(it) //producer
        }
    }
}