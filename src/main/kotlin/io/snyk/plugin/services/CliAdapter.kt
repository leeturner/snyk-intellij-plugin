package io.snyk.plugin.services

import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.project.Project
import io.snyk.plugin.cli.CliNotExistsException
import io.snyk.plugin.cli.ConsoleCommandRunner
import io.snyk.plugin.getCliFile
import io.snyk.plugin.getPluginPath
import io.snyk.plugin.pluginSettings
import org.jetbrains.annotations.TestOnly

/**
 * Wrap work with Snyk CLI.
 */
abstract class CliAdapter<R>(val project: Project) {

    private var consoleCommandRunner = ConsoleCommandRunner()

    private val logger = logger<CliAdapter<R>>()

    protected val projectPath: String = project.basePath
        ?: throw IllegalStateException("Scan should not be performed on Default project (with `null` project base dir)")

    fun isCliInstalled(): Boolean {
        logger.debug("Check whether Snyk CLI is installed by plugin in: ${getPluginPath()}")
        return getCliFile().exists()
    }

    fun execute(commands: List<String>): R = try {
        val cmds = buildCliCommandsList(commands)
        val apiToken = pluginSettings().token ?: ""
        val rawResultStr = consoleCommandRunner.execute(cmds, projectPath, apiToken, project)
        convertRawCliStringToCliResult(rawResultStr)
    } catch (exception: CliNotExistsException) {
        getErrorResult(exception.message ?: "Snyk CLI not installed.")
    }

    protected abstract fun getErrorResult(errorMsg: String): R

    /**
     * if rawStr == [ConsoleCommandRunner.PROCESS_CANCELLED_BY_USER] - CLI scan process
     * was intentionally terminated by user.
     */
    abstract fun convertRawCliStringToCliResult(rawStr: String): R

    /**
     * Build list of commands for run Snyk CLI command.
     * @return List<String>
     */
    fun buildCliCommandsList(cmds: List<String>): List<String> {
        logger.debug("Enter buildCliCommandsList")
        val settings = pluginSettings()

        val commands: MutableList<String> = mutableListOf()
        commands.add(getCliCommandPath())
        commands.addAll(cmds)
        commands.add("--json")

        val customEndpoint = settings.customEndpointUrl
        if (customEndpoint != null && customEndpoint.isNotEmpty()) {
            commands.add("--API=$customEndpoint")
        }

        if (settings.ignoreUnknownCA) {
            commands.add("--insecure")
        }

        val organization = settings.organization
        if (organization != null && organization.isNotEmpty()) {
            commands.add("--org=$organization")
        }

        if (!settings.usageAnalyticsEnabled) {
            commands.add("--DISABLE_ANALYTICS")
        }

        val additionalParameters = settings.getAdditionalParameters(project)

        if (additionalParameters != null && additionalParameters.trim().isNotEmpty()) {
            commands.addAll(additionalParameters.trim().split(" "))
        }

        logger.debug("Cli parameters: $commands")

        return commands.toList()
    }

    @TestOnly
    fun setConsoleCommandRunner(newRunner: ConsoleCommandRunner) {
        this.consoleCommandRunner = newRunner
    }

    private fun getCliCommandPath(): String =
        if (isCliInstalled()) getCliFile().absolutePath else throw CliNotExistsException()
}