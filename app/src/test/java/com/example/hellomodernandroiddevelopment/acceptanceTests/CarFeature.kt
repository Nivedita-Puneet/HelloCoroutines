package com.example.hellomodernandroiddevelopment.acceptanceTests

import com.example.hellomodernandroiddevelopment.Car
import com.example.hellomodernandroiddevelopment.Engine
import com.example.outsideintddexample.acceptancetests.MainCoroutineScopeRule
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

class CarFeature {

    val engine = Engine()
    var  car = Car(engine,6.0)

    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    @Test
    fun carISLosingFuelWhenTurnedOn() = runBlockingTest{

        car.turnOn()
        assertEquals(5.5, car.fuel)
    }

    @Test
    fun carIsTurningOnItsEngineAndIncreasesTheTemperature() = runBlockingTest{
        car.turnOn()

        coroutinesTestRule.advanceTimeBy(2000)
        assertEquals(25, car.engine.temperature)

        coroutinesTestRule.advanceTimeBy(2000)
        assertEquals(50, car.engine.temperature)

        coroutinesTestRule.advanceTimeBy(2000)
        assertEquals(95, car.engine.temperature)


        assertTrue(car.engine.isTurnedOn)
    }
}