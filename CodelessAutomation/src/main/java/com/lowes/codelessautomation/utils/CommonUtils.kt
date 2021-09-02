package com.lowes.codelessautomation.utils

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.test.platform.app.InstrumentationRegistry
import com.lowes.codelessautomation.model.Instruction
import org.apache.poi.hssf.usermodel.HSSFCell
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.poifs.filesystem.POIFSFileSystem
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.io.InputStream

// Created by Sandeep on 14/07/21.

object CommonUtils {

    private val TAG = "TestCasesUtil"

    fun getClass(id: String): Class<AppCompatActivity> {
        var packageName = getPackageName()
        packageName += "."
        return Class.forName(packageName + id) as Class<AppCompatActivity>
    }

    fun getId(id: String) =
        InstrumentationRegistry.getInstrumentation().targetContext.resources.getIdentifier(
            id,
            "id",
            getPackageName()
        )

    fun readExcelFileFromAssets(myInput: InputStream): MutableMap<String, List<Instruction>> {
        val testCasesMap = mutableMapOf<String, List<Instruction>>()
        try {
            // Create a POI File System object
            val myFileSystem = POIFSFileSystem(myInput)
            // Create a workbook using the File System
            val myWorkBook = HSSFWorkbook(myFileSystem)
            // Get the first sheet from workbook
            val mySheet = myWorkBook.getSheetAt(0)
            // We now need something to iterate through the cells.
            val rowIterator: Iterator<Row> = mySheet.rowIterator()

            while (rowIterator.hasNext()) {
                val myRow = rowIterator.next() as HSSFRow
                val listOfInstructions = mutableListOf<Instruction>()
                val cellIterator = myRow.cellIterator()
                if (cellIterator.hasNext()) {
                    val testCaseTitle = (cellIterator.next() as HSSFCell).toString()
                    prepareInstructions(cellIterator, listOfInstructions)
                    if (listOfInstructions.isNotEmpty())
                        if (testCaseTitle.isNotEmpty()) {
                            testCasesMap[testCaseTitle] = listOfInstructions
                        } else {
                            testCasesMap["Test Case: ${myRow.rowNum}"] = listOfInstructions
                        }
                }
            }
            Log.d(TAG, "testCasesMap: $testCasesMap")
            return testCasesMap
        } catch (e: Exception) {
            Log.e(TAG, "error $e")
        }
        return testCasesMap
    }

    fun sendTestResults(resultMap: LinkedHashMap<String, Boolean>) {
        val htmlBuilder = StringBuilder()
        htmlBuilder.append(
            "<html>\n" +
                "<head>\n" +
                "<style>\n" +
                "table, tr, td {\n" +
                "  border: 1px solid black;\n" +
                "border-collapse: collapse;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h2>Test Cases Results</h2>\n"
        )
        htmlBuilder.append("<table id=\"mytable\"")

        for ((key, value) in resultMap) {
            htmlBuilder.append(String.format("<tr><td>%s</td>", key))
            if (value) {
                htmlBuilder.append(String.format("<td style=\"color: green;\">%s</td>", "Pass"))
            } else {
                htmlBuilder.append(String.format("<td style=\"color: red;\">%s</td>", "Fail"))
            }
        }
        htmlBuilder.append("</tr>")
        htmlBuilder.append("</table>")
        htmlBuilder.append(
            "</body>\n" +
                "</html>"
        )
        saveStringToHtmlFile(
            InstrumentationRegistry.getInstrumentation().context,
            htmlBuilder.toString()
        )
    }

    private fun prepareInstructions(
        cellIterator: MutableIterator<Cell>,
        listOfInstructions: MutableList<Instruction>
    ) {
        while (cellIterator.hasNext()) {
            val myCell = cellIterator.next() as HSSFCell
            val instruction = myCell.toString()
            if (instruction.isEmpty().not()) {
                val id = if (instruction.contains('#')) instruction.substringAfter("#")
                    .substringBefore(" ") else ""
                val action = instruction.substringAfter("$").substringBefore(" ")
                var text = ""
                if (instruction.contains('(')) {
                    val bracketStart = instruction.indexOf('(')
                    val bracketEnd = instruction.lastIndexOf(')')
                    text = instruction.substring(bracketStart + 1, bracketEnd)
                }
                listOfInstructions.add(Instruction(id, action, text))
                Log.e(TAG, " Index :" + myCell.columnIndex + " -- " + myCell.toString())
            }
        }
    }

    private fun getPackageName() =
        InstrumentationRegistry.getInstrumentation().targetContext.packageName

    private fun saveStringToHtmlFile(context: Context, htmlString: String?): File? {
        var htmlFile: File? = null
        try {
            // If the file does not exists, it is created.
            htmlFile = File(context.getExternalFilesDir(null), "TestResultsFile.html")
            if (!htmlFile.exists()) {
                htmlFile.createNewFile()
            }

            // Adds a line to the file
            val writer = BufferedWriter(FileWriter(htmlFile, false))
            writer.write(htmlString)
            writer.close()
        } catch (e: IOException) {
            Log.e("Test", "Unable to write to TestResultsFile.html file.")
        }
        return htmlFile
    }
}