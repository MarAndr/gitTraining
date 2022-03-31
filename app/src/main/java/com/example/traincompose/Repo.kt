package com.example.traincompose

import kotlinx.coroutines.delay

class Repo {
    suspend fun fakeRequest(): String{
        delay(2000)
        return "New data!!!"
    }
}