@file:Suppress("DuplicatedCode")

package com.jacob.core_lib.parser.instructions.load

import com.jacob.core_lib.common.I
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.immediateFromDec
import com.jacob.core_lib.common.toRegisterAddress
import com.jacob.core_lib.instructions.OffsetTypes
import com.jacob.core_lib.instructions.load.Load

class LoadRegisterImmediateOffsetParser(private val instructionString: String) : LoadInstructionParser {
    override fun parse(): Load {
//        LDR R1, [R2, #3]
        val operands = instructionString.removePrefix("LDR")
            .replace("[", "")
            .replace("]", "")
            .split(",")
            .map(String::trim)

        val destinationRegister = operands.first().toRegisterAddress(::DestinationRegister)
        val sourceRegister = operands[1].toRegisterAddress(::SourceRegister)
        val immediateOffset = operands.getOrNull(2)?.immediateFromDec() ?: 0.I

        return Load.of(destinationRegister, sourceRegister, immediateOffset, OffsetTypes.IMMEDIATE)
    }

}