package com.example.hellomodernandroiddevelopment

import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class EngineTest {
    private val engine:Engine = Engine(200,true);
    @Test
    suspend fun isEngineTurnedOn() {
        engine.turnOn()

        assertEquals(true, engine.isTurnedOn)
        assertEquals(95, engine.temperature)
    }


    @Test
    fun isEngineTurnedOff(){
        engine.turnedOff()

        assertEquals(false, engine.isTurnedOn)
        assertEquals(15, engine.temperature)
    }
}