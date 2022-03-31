package com.example.traincompose

import android.os.Bundle
import android.util.Log
import android.widget.Switch
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import java.math.BigInteger
import kotlin.random.Random
import kotlin.random.asJavaRandom
import kotlin.time.measureTime


class MainActivity : ComponentActivity() {

    private val users = listOf(
        User(name = "Bob", 22),
        User(name = "Smith", 22),
        User(name = "Cark", 22),
        User(name = "Tom", 22),
        User(name = "Jerry", 22),
        User(name = "John", 22),
        User(name = "Slava", 22),
        User(name = "Piter", 22),
        User(name = "Harry", 22),
        User(name = "John", 22),
        User(name = "Slava", 22),
        User(name = "Piter", 22),
        User(name = "Harry", 22)
    )

    private val listNames = listOf("Himanshu", "Amit", "Janishar")
    private val listLastNames = listOf("Singh", "Shekhar", "Ali")

    private val viewModel by viewModels<KindsViewModel> { MyViewModelFactory(Repo()) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App().appComponent?.inject(this)

        lifecycleScope.launch {
        }

        setContent {
            OutlinedTextField(value = "", onValueChange = {})
        }
    }

    private suspend fun searchUsers(query: String): List<User>{
        delay(1000)
        return users.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    @Composable
    fun UsersList(users: List<User>, textField: String) {
        LazyColumn {
            items(users.filter { it.name ==  textField}) {
                Text(text = it.name, fontSize = 50.sp)
            }
        }
    }


    @Composable
    private fun userTextField(textField: String, onValueChange: (String) -> Unit): Flow<String> {
        Column {
            OutlinedTextField(value = textField, onValueChange = onValueChange)
        }
        return callbackFlow {
            send(textField)
            awaitClose {

            }
        }
    }

    private fun userTextField2(onValueChangeText: String): Flow<String> {

        return callbackFlow {
            send(onValueChangeText)
            awaitClose {

            }
        }
    }

    private fun flowUsers(users: List<User>): Flow<User> {
        return flow {
            users.forEach {
                emit(it)
            }
        }
    }
}

data class User(val name: String = "null", val age: Int = 0)

