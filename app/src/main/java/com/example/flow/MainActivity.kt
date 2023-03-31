package com.example.flow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val channel = Channel<Int>()
    val TAG: String = "KOTLIN_DEBUG"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch {
            producer().onStart() {
                emit(-1)
                Log.d(TAG, "Starting out")
            }.onCompletion {
                emit(6)
                Log.d(TAG, "Completed")

            }.onEach {
                Log.d(TAG, "About to emit")

            }

                //data.collect{} - its called consumer;
                //when you comment out consumer, producer also stops emiting data
                .collect {
                    Log.d(TAG, it.toString())
                }
        }
    }

    private fun producer(): Flow<Int> {
        return flow<Int> {
            val list = listOf(1, 2, 3, 4, 5)
            list.forEach {
                delay(1000)
                emit(it) //producer
            }
        }
    }
}