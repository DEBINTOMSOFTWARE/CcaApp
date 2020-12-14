package com.debin.challengechip.presentation

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.debin.challengechip.R
import com.debin.challengechip.presentation.breeds.BreedsFragment
import org.junit.Test
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
class BreedsFragmentTest {

    @Test
    fun testNavigationToBreedDetailsFragment() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.breeds_navigation)
        val senario = launchFragmentInContainer<BreedsFragment>()
        senario.onFragment {fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.rv_breeds)).check(matches(isDisplayed()))

    }

    @Test
    fun breedFragmentIsDisplayed() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.breeds_navigation)
        launchFragmentInContainer<BreedsFragment>()
        onView(withId(R.id.rv_breeds)).check(matches(isDisplayed()))
    }

    @Test
    fun test_breed_fragment_is_opened() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.breeds_navigation)
        onView(withId(R.layout.fragment_breeds)).check( matches(ViewMatchers.isDisplayed()) )
    }
}