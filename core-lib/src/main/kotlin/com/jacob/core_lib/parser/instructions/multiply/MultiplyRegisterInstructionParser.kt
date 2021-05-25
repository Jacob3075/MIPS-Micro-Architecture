// TODO: FIX DUPLICATE CODE
@file:Suppress("DuplicatedCode")

package com.jacob.core_lib.parser.instructions.multiply

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.toRegisterAddresses
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.multipy.Multiply

class MultiplyRegisterInstructionParser(private val instructionString: String) : MultiplyInstructionParser {
    override fun parse(): Instruction {
//        MUL R1, R2, R3
        val registers = instructionString.removePrefix("MUL")
            .split(",")
            .map(String::trim)
            .toRegisterAddresses()

        val destinationRegister = registers.first().let(::DestinationRegister)
        val sourceRegister1 = registers[1].let(::SourceRegister)
        val sourceRegister2 = registers.last().let(::SourceRegister)

        return Multiply.of(destinationRegister, sourceRegister1, sourceRegister2)
    }
}