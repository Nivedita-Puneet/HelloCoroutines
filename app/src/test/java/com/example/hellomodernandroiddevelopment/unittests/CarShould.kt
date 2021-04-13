package com.example.hellomodernandroiddevelopment.unittests

import com.example.hellomodernandroiddevelopment.Car
import com.example.hellomodernandroiddevelopment.Engine
import com.example.outsideintddexample.acceptancetests.MainCoroutineScopeRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

class CarShould {

    private val engine: Engine = mock()
    private val car:Car

    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()
    init {
        runBlockingTest {

            whenever(engine.turnOn()).thenReturn(flow{
                kotlinx.coroutines.delay(2000)
                emit(25)
                kotlinx.coroutines.delay(2000)
                emit(50)
                kotlinx.coroutines.delay(2000)
                emit(95)
            })
        }

        car = Car(engine, 5.0)

    }
    @Test
    fun looseFuelWhenItTurnsOn()= runBlockingTest {

        car.turnOn()

        assertEquals(4.5, car.fuel)
    }

    @Test
    fun turnOnItsEngine() = runBlockingTest {
        car.turnOn()

        verify(engine, times(1)).turnOn()
    }

}