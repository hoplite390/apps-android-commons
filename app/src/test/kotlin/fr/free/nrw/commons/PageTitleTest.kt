package fr.free.nrw.commons

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.net.URLEncoder

class PageTitleTest {
    @Test
    fun displayTextShouldNotBeUnderscored() {
        val pageTitle = PageTitle("Ex_1 ")
        assertEquals("Ex 1", pageTitle.displayText)
    }

    @Test
    fun moreThanTwoColons() {
        val pageTitle = PageTitle("File:sample:a.jpg")
        assertEquals("File:Sample:a.jpg", pageTitle.prefixedText)
    }

    @Test
    fun getTextShouldReturnWithoutNamespace() {
        val pageTitle = PageTitle("File:sample.jpg")
        assertEquals("Sample.jpg", pageTitle.text)
    }


    @Test
    fun capitalizeNameAfterNamespace() {
        val pageTitle = PageTitle("File:sample.jpg")
        assertEquals("File:Sample.jpg", pageTitle.prefixedText)
    }

    @Test
    fun prefixedTextShouldBeUnderscored() {
        val pageTitle = PageTitle("Ex 1 ")
        assertEquals("Ex_1", pageTitle.prefixedText)
    }

    @Test
    fun getMobileUriForTest() {
        val pageTitle = PageTitle("Test")
        assertEquals(BuildConfig.MOBILE_HOME_URL + "Test", pageTitle.mobileUri.toString())
    }

    @Test
    fun spaceBecomesUnderscoreInUri() {
        val pageTitle = PageTitle("File:Ex 1.jpg")
        assertEquals(BuildConfig.HOME_URL + "File:Ex_1.jpg", pageTitle.canonicalUri.toString())
    }

    @Test
    fun leaveSubpageNamesUncapitalizedInUri() {
        val pageTitle = PageTitle("User:Ex/subpage")
        assertEquals(BuildConfig.HOME_URL + "User:Ex/subpage", pageTitle.canonicalUri.toString())
    }

    @Test
    fun unicodeUri() {
        val pageTitle = PageTitle("User:例")
        assertEquals(BuildConfig.HOME_URL + "User:" + URLEncoder.encode("例", "utf-8"), pageTitle.canonicalUri.toString())
    }
}
