package com.example.criticalnewsfeedapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.testing.TestLifecycleOwner
import com.example.criticalnewsfeedapp.domain.service.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ServiceTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun getTopHeadlinesTest() {
        val newsRepository = NewsRepository("bbc-news")
        val topHeadLinesFromMainSource = newsRepository.getTopHeadLinesFromMainSource()
        val testLifecycleOwner = TestLifecycleOwner(Lifecycle.State.RESUMED, Dispatchers.Default)
        topHeadLinesFromMainSource.observe(testLifecycleOwner) { value ->
            assertNotNull(value)
            assertNotEquals(0, value.size)

            for (article in value) {
                assertEquals("bbc-news", article.source.id)
            }
        }
    }
}