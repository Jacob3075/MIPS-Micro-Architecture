package com.jacob.core_lib.instructions.conditionals

import com.jacob.core_lib.core.ExecutionEnvironment
import com.jacob.core_lib.instructions.Instruction

class Equal(private val instruction: Instruction) : Conditional, Instruction by instruction {
    override fun execute(executionEnvironment: ExecutionEnvironment) {
        if (executionEnvironment.registerArray.statusRegister.zero) {
            instruction.execute(executionEnvironment)
        }
    }
}
