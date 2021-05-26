@file:Suppress("DuplicatedCode")

package com.jacob.core_lib.parser.instructions.sub

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.RegisterAddresses
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.toRegisterAddresses
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.shift.ShiftOperation
import com.jacob.core_lib.instructions.sub.Sub
import com.jacob.core_lib.word.ImmediateValue

class SubImmediateInstructionParser internal constructor(
    private val instructionString: String,
    private val shiftOperation: ShiftOperation,
    private val strategy: (String) -> ImmediateValue
) : SubInstructionParser {

    override fun parse(): Instruction {
        val operands = instructionString.removePrefix("SUB")
            .split(",")
            .map(String::trim)

        val immediateValue = operands.last().let(strategy)

        val registers: List<RegisterAddresses> = operands.take(2)
            .toRegisterAddresses()

        val destinationRegister = registers.first().let(::DestinationRegister)
        val sourceRegister = registers.last().let(::SourceRegister)

        return Sub.of(destinationRegister, sourceRegister, immediateValue, shiftOperation)
    }

}
