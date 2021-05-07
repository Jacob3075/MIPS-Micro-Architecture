package com.jacob.core_lib.parser

import com.jacob.core_lib.instructions.Instruction
import java.io.File
import java.net.URI

object Parser {
    fun parseInstructionsFromFile(filePath: URI): List<Instruction> {
        val file: File = File(filePath)

        return file.readLines()
            .map(::InstructionString)
            .map(InstructionString::parse)
    }
}
