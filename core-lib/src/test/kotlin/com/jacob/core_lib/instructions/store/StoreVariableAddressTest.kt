package com.jacob.core_lib.instructions.store

import com.jacob.core_lib.common.addresses.MemoryAddress
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.*
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.word.Word
import io.mockk.mockk
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class StoreVariableAddressTest {
    @Test
    internal fun `executing instruction reads and updates correct registers and memory array positions`() {
        val sourceRegister = SourceRegister(RegisterAddress.REGISTER_1)
        val variableName = "A"

        val registerArray = RegisterArray()
        val memoryArray = MemoryArray()
        val labels = mockk<List<Label>>()
        val variable = Variable("A", MemoryAddress(5))
        val variables = listOf(variable)

        registerArray.setValueAtRegister(sourceRegister.registerAddress, Word(10))

        val executionEnvironment = ExecutionEnvironment(registerArray, memoryArray, labels, variables)

        val storeVariableAddress = StoreVariableAddress(sourceRegister, variableName)
        storeVariableAddress.execute(executionEnvironment)

        memoryArray.getWordAt(variable.address) `should be equal to` Word(10)
    }

    @Test
    internal fun `running store instructions reads and updates correct register and memory addresses`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()
        val variables = listOf(
            Variable("A", MemoryAddress(1)),
            Variable("ABC", MemoryAddress(2))
        )

        val sourceRegister1 = SourceRegister(RegisterAddress.REGISTER_1)
        val sourceRegister2 = SourceRegister(RegisterAddress.REGISTER_2)

        registerArray.setValueAtRegister(sourceRegister1.registerAddress, Word(10))
        registerArray.setValueAtRegister(sourceRegister2.registerAddress, Word(20))

        val store1 = StoreVariableAddress(sourceRegister1, "A")
        val store2 = StoreVariableAddress(sourceRegister2, "ABC")

        val instructions: List<Instruction> = listOf(
            store1,
            store2,
        )
        val program = Program(instructions, variables)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        memoryArray.getWordAt(MemoryAddress(1)) `should be equal to` Word(10)
        memoryArray.getWordAt(MemoryAddress(2)) `should be equal to` Word(20)
    }
}