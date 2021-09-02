package com.lowes.codelessautomation.main

import com.lowes.codelessautomation.commands.BackClickCommand
import com.lowes.codelessautomation.commands.IsVisibleCommand
import com.lowes.codelessautomation.commands.LaunchCommand
import com.lowes.codelessautomation.commands.MatchesWithCommand
import com.lowes.codelessautomation.commands.PerformClickCommand
import com.lowes.codelessautomation.commands.ReplaceTextCommand
import com.lowes.codelessautomation.commands.WaitCommand
import com.lowes.codelessautomation.model.Action
import com.lowes.codelessautomation.utils.CommonUtils
import com.lowes.codelessautomation.utils.CommonUtils.readExcelFileFromAssets

/**
 * Created by Sandeep on 31/08/21.
 */
object TestSuiteRunner {

    fun runTests(fileName: String) {
        val resultMap = LinkedHashMap<String, Boolean>()
        val inputStream = javaClass.classLoader.getResourceAsStream(fileName)
        val testCases = readExcelFileFromAssets(inputStream)
        val instructionExecutor = InstructionExecutor()
        testCases.forEach { (key, value) ->
            value.forEach {
                when (it.action) {
                    Action.LAUNCH.action -> instructionExecutor.addToQueue(LaunchCommand(it))
                    Action.IS_VISIBLE.action -> instructionExecutor.addToQueue(IsVisibleCommand(it))
                    Action.MATCHES_WITH.action -> instructionExecutor.addToQueue(
                        MatchesWithCommand(
                            it
                        )
                    )
                    Action.PERFORM_CLICK.action -> instructionExecutor.addToQueue(
                        PerformClickCommand(it)
                    )
                    Action.BACK_CLICK.action -> instructionExecutor.addToQueue(BackClickCommand())
                    Action.WAIT_MORE.action, Action.WAIT_LESS.action -> instructionExecutor.addToQueue(
                        WaitCommand(it)
                    )
                    Action.REPLACE_TEXT.action -> instructionExecutor.addToQueue(
                        ReplaceTextCommand(
                            it
                        )
                    )
                }
            }
            try {
                instructionExecutor.executeInstructions()
                resultMap[key] = true
            } catch (e: Exception) {
                resultMap[key] = false
            } catch (e: Error) {
                resultMap[key] = false
            }
            instructionExecutor.queue.clear()
        }
        CommonUtils.sendTestResults(resultMap)
    }
}