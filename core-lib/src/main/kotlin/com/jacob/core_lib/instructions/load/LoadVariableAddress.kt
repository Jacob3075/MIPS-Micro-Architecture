package com.jacob.core_lib.instructions.load

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.core.ExecutionEnvironment

class LoadVariableAddress(internal val destinationRegister: DestinationRegister, internal val variableName: String) :
    Load {

    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val variable = executionEnvironment.variables.find { it.name == variableName }

        require(variable != null)

        val wordFromMemory = executionEnvironment.memoryArray.getWordAt(variable.address)
        executionEnvironment.registerArray.setValueAtRegister(destinationRegister.registerAddress, wordFromMemory)
    }
}