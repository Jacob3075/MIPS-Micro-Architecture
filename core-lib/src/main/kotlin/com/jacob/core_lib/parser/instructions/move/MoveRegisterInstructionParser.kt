package com.jacob.core_lib.parser.instructions.move

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.toRegisterAddresses
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.move.Move
import com.jacob.core_lib.parser.InstructionString
import com.jacob.core_lib.parser.instructions.InstructionParser

class MoveRegisterInstructionParser internal constructor(private val instructionString: InstructionString) :
    InstructionParser {
    override fun parse(): Instruction {
        val registers = instructionString.operands.toRegisterAddresses()

        val destinationRegister = registers.first().let(::DestinationRegister)
        val sourceRegister = registers.last().let(::SourceRegister)

        return Move.of(destinationRegister, sourceRegister)
    }

}
