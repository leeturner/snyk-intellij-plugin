package io.snyk.plugin.services

import com.intellij.notification.NotificationAction
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.project.Project
import com.intellij.util.io.HttpRequests
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.slot
import io.mockk.verify
import io.snyk.plugin.getCliFile
import io.snyk.plugin.ui.SnykBalloonNotificationHelper
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException
import kotlin.test.assertEquals

class SnykCliDownloaderErrorHandlerTest {

    private lateinit var cut: SnykCliDownloaderErrorHandler

    @Before
    fun setUp() {
        cut = SnykCliDownloaderErrorHandler()
        mockkObject(SnykBalloonNotificationHelper)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun showErrorWithWithRetryAndContactAction_shouldShowErrorWithActions() {
        val notificationMessage = "Test message"
        val project = mockk<Project>()
        val retryActionSlot = slot<NotificationAction>()
        val contactActionSlot = slot<NotificationAction>()
        val messageSlot = slot<String>()
        val projectSlot = slot<Project>()

        every {
            SnykBalloonNotificationHelper.showError(
                capture(messageSlot),
                capture(projectSlot),
                capture(retryActionSlot),
                capture(contactActionSlot)
            )
        } returns mockk()

        cut.showErrorWithRetryAndContactAction(notificationMessage, mockk(), project)

        assertEquals("Retry CLI download", retryActionSlot.captured.templateText)
        assertEquals("Contact support...", contactActionSlot.captured.templateText)
        assertEquals(notificationMessage, messageSlot.captured)
        assertEquals(project, projectSlot.captured)

        verify(exactly = 1) {
            SnykBalloonNotificationHelper.showError(any(), any(), any(), any())
        }
    }

    @Test
    fun handleIOException_shouldTryAgainAndShowErrorWithActions() {
        val project = mockk<Project>()
        val indicator = mockk<ProgressIndicator>()
        val retryActionSlot = slot<NotificationAction>()
        val contactActionSlot = slot<NotificationAction>()
        val messageSlot = slot<String>()
        val projectSlot = slot<Project>()
        val downloaderService = mockk<SnykCliDownloaderService>()
        val latestReleaseInfo =
            LatestReleaseInfo(1, "release-url", "release-name", "release-tagName")
        val exception = IOException("Read Timed Out")
        val notificationMessage = cut.getNetworkErrorNotificationMessage(exception)

        every {
            SnykBalloonNotificationHelper.showError(
                capture(messageSlot),
                capture(projectSlot),
                capture(retryActionSlot),
                capture(contactActionSlot)
            )
        } returns mockk()

        every { project.getService(SnykCliDownloaderService::class.java) } returns downloaderService
        every { downloaderService.getLatestReleaseInfo() } returns latestReleaseInfo
        every { downloaderService.downloadFile(any(), any(), any()) } returns getCliFile()

        cut.handleIOException(exception, indicator, project)

        // verify notification
        assertEquals("Retry CLI download", retryActionSlot.captured.templateText)
        assertEquals("Contact support...", contactActionSlot.captured.templateText)
        assertEquals(notificationMessage, messageSlot.captured)
        assertEquals(project, projectSlot.captured)

        verify(exactly = 1) {
            downloaderService.downloadFile(getCliFile(), latestReleaseInfo.tagName, indicator)
            SnykBalloonNotificationHelper.showError(any(), any(), any(), any())
        }
    }

    @Test
    fun handleIOException_shouldOnlyShowErrorWithActionsWhenReleaseInfoEmpty() {
        val project = mockk<Project>()
        val indicator = mockk<ProgressIndicator>()
        val retryActionSlot = slot<NotificationAction>()
        val contactActionSlot = slot<NotificationAction>()
        val messageSlot = slot<String>()
        val projectSlot = slot<Project>()
        val downloaderService = mockk<SnykCliDownloaderService>()
        val exception = IOException("Read Timed Out")

        every {
            SnykBalloonNotificationHelper.showError(
                capture(messageSlot),
                capture(projectSlot),
                capture(retryActionSlot),
                capture(contactActionSlot)
            )
        } returns mockk()

        every { project.getService(SnykCliDownloaderService::class.java) } returns downloaderService
        every { downloaderService.getLatestReleaseInfo() } returns null

        cut.handleIOException(exception, indicator, project)

        // verify notification
        assertEquals("Retry CLI download", retryActionSlot.captured.templateText)
        assertEquals("Contact support...", contactActionSlot.captured.templateText)
        assertEquals(cut.getNetworkErrorNotificationMessage(exception), messageSlot.captured)
        assertEquals(project, projectSlot.captured)

        verify(exactly = 1) {
            SnykBalloonNotificationHelper.showError(any(), any(), any(), any())
        }

        verify(exactly = 0) {
            downloaderService.downloadFile(any(), any(), any())
        }
    }

    @Test
    fun handleHttpStatusException_shouldDisplayErrorMessage() {
        val project = mockk<Project>()
        val exception = HttpRequests.HttpStatusException("Forbidden", 403, "url")

        val contactActionSlot = slot<NotificationAction>()
        val messageSlot = slot<String>()
        val projectSlot = slot<Project>()
        every {
            SnykBalloonNotificationHelper.showError(
                capture(messageSlot),
                capture(projectSlot),
                capture(contactActionSlot)
            )
        } returns mockk()

        cut.handleHttpStatusException(exception, project)

        assertEquals(cut.getHttpStatusErrorNotificationMessage(exception), messageSlot.captured)
        assertEquals(project, projectSlot.captured)
        assertEquals("Contact support...", contactActionSlot.captured.templateText)

        verify(exactly = 1) {
            SnykBalloonNotificationHelper.showError(any(), any(), any())
        }
    }
}