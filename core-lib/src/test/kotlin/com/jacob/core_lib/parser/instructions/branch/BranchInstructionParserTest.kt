package com.jacob.core_lib.parser.instructions.branch

import arrow.core.getOrElse
import com.jacob.core_lib.instructions.Branch
import com.jacob.core_lib.instructions.conditionals.Always
import com.jacob.core_lib.parser.instructions.InstructionString
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class BranchInstructionParserTest {
    @Test
    internal fun `creates correct instruction with single letter label name`() {
        val instructionString = InstructionString("B A")
        val branch = BranchInstructionParser.from(instructionString).getOrElse { null }!! as Always

        (branch.instruction as Branch).labelName `should be equal to` "A"
    }

    @Test
    internal fun `creates correct instruction with multiple letter label name`() {
        val instructionString = InstructionString("B ABC_A-D1_A")
        val branch = BranchInstructionParser.from(instructionString).getOrElse { null }!! as Always

        (branch.instruction as Branch).labelName `should be equal to` "ABC_A-D1_A"
    }
}
