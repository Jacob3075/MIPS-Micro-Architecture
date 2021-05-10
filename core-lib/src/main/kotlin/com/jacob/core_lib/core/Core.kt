package com.jacob.core_lib.core

class Core(
        private val memoryArray: MemoryArray = MemoryArray(),
        private val registerArray: RegisterArray = RegisterArray(),
        private val program: Program,
) {

    fun runProgram() {
        while (program.hasMoreInstructions(registerArray.programCounter)) {
            val nextInstruction = program.getInstructionAt(registerArray.programCounter) ?: return
            nextInstruction.execute(memoryArray, registerArray, program.labels)
        }
    }
}
