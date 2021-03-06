package com.jacob.core_lib.parser.instructions.sub

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.RegisterAddresses
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.toRegisterAddresses
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.sub.Sub
import com.jacob.core_lib.parser.instructions.InstructionString

class SubRegisterParser internal constructor(
    private val instructionString: InstructionString,
) : SubInstructionParser {
    override fun parse(): Instruction {
        val registers: List<RegisterAddresses> = instructionString.operands.toRegisterAddresses()

        val destinationRegister = registers.first().let(::DestinationRegister)
        val sourceRegister1 = registers[1].let(::SourceRegister)
        val sourceRegister2 = registers.last().let(::SourceRegister)

        return Sub.of(
            destinationRegister,
            sourceRegister1,
            sourceRegister2,
            instructionString.shiftOperation,
            instructionString.conditional
        )
    }

}
