package com.example.criticalnewsfeedapp

import android.content.pm.ActivityInfo
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.criticalnewsfeedapp.ui.articlelisting.ArticleListAdapter
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowActivity
import java.time.OffsetDateTime

@RunWith(RobolectricTestRunner::class)
class MainActivityTests {
    @Test
    fun mainActivityIsStarted() {
        Robolectric.buildActivity(MainActivity::class.java).use { controller ->
            controller.setup()
            val activity: MainActivity = controller.get()
            val contentView: View = activity.findViewById(R.id.nav_host_fragment)
            assertNotNull(contentView)
        }
    }

    @Test
    fun articlesListingFragmentLoaded() {
        Robolectric.buildActivity(MainActivity::class.java).use { controller ->
            controller.setup()
            val activity: MainActivity = controller.get()
            val articlesListingView: View = activity.findViewById(R.id.articles_listing_rv)
            assertNotNull(articlesListingView)
        }
    }

    @Test
    fun clickOnItemNavigatesToArticleFragment() {
        Robolectric.buildActivity(MainActivity::class.java).use { controller ->
            controller.setup()
            val activityShadowed: ShadowActivity? = shadowOf(controller.get())
            val articlesListingView: View = activityShadowed!!.contentView.findViewById(R.id.articles_listing_rv)
            assertNotNull(articlesListingView)

            val navController = activityShadowed.contentView.findViewById<FragmentContainerView>(R.id.nav_host_fragment).findNavController()
            val currentRecyclerView: RecyclerView = activityShadowed.contentView.findViewById(R.id.articles_listing_rv)
            currentRecyclerView.getChildAt(0).performClick()

            assertThat(navController.currentDestination?.id, equalTo(R.id.articleFragment))
        }
    }

    @Test
    fun titleIsDisplayed() {
        Robolectric.buildActivity(MainActivity::class.java).use { controller ->
            controller.setup()
            val activity: MainActivity = controller.get()
            val toolbar: Toolbar = activity.findViewById(R.id.topAppBar)

            assertThat(toolbar.title, equalTo(activity.getString(R.string.newsSourceName)))
        }
    }

    @Test
    fun screenOrientationChanges() {
        Robolectric.buildActivity(MainActivity::class.java).use { controller ->
            controller.setup()
            val activity: MainActivity = controller.get()
            val currentRecyclerView: RecyclerView = activity.findViewById(R.id.articles_listing_rv)
            val currentAdapter = currentRecyclerView.adapter as ArticleListAdapter

            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
            val newRecyclerView: RecyclerView = activity.findViewById(R.id.articles_listing_rv)
            val newAdapter = newRecyclerView.adapter as ArticleListAdapter

            assertThat(currentAdapter, equalTo(newAdapter))
        }
    }

    @Test
    fun checkArticleOrder() {
        Robolectric.buildActivity(MainActivity::class.java).use { controller ->
            controller.setup()
            val activityShadowed: ShadowActivity? = shadowOf(controller.get())
            val currentRecyclerView: RecyclerView = activityShadowed!!.contentView.findViewById(R.id.articles_listing_rv)
            val currentAdapter = currentRecyclerView.adapter as ArticleListAdapter

            for (i in 1..<currentAdapter.data!!.size) {
                assertTrue(
                    OffsetDateTime.parse(currentAdapter.data!![i].publishedAt).isBefore(
                        OffsetDateTime.parse(currentAdapter.data!![i - 1].publishedAt)
                    )
                )
            }
        }
    }

    @Test
    @Config(qualifiers = "h1024dp-land")
    fun checkExpandedLayoutArticleListGrid() {
        Robolectric.buildActivity(MainActivity::class.java).use { controller ->
            controller.setup()
            val activity: MainActivity = controller.get()
            val currentRecyclerView: RecyclerView = activity.findViewById(R.id.articles_listing_rv)

            assertTrue(currentRecyclerView.layoutManager is GridLayoutManager)

            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

            assertTrue(currentRecyclerView.layoutManager is LinearLayoutManager)
        }
    }
}