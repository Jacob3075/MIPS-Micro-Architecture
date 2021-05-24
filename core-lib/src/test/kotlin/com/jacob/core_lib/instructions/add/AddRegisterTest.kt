package com.jacob.core_lib.instructions.add

import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.core.*
import com.jacob.core_lib.createAddInstruction
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.parser.data.ParsedData
import com.jacob.core_lib.word.Word
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test

//TODO: ADD TESTS WITH SHIFT OPERATIONS
internal class AddRegisterTest {
    @Test
    internal fun `can create new add instruction with 2 source registers`() {
        val sourceRegister1 = RegisterAddress.REGISTER_0
        val sourceRegister2 = RegisterAddress.REGISTER_0
        val destinationRegister = RegisterAddress.REGISTER_2

        val addInstruction = createAddInstruction(destinationRegister, sourceRegister1, sourceRegister2)

        addInstruction.shouldNotBeNull()
        addInstruction `should be instance of` AddRegister::class
        addInstruction `should be instance of` Add::class
        addInstruction `should be instance of` Instruction::class
    }

    @Test
    internal fun `executing add instruction with register values reads and updates the correct registers`() {
        val memoryArray = MemoryArray()
        val labels = emptyList<Label>()
        val variables = emptyList<Variable>()
        val registerArray = RegisterArray()

        val executionEnvironment = ExecutionEnvironment(
            registerArray = registerArray,
            memoryArray = memoryArray,
            labels = labels,
            variables = variables
        )

        registerArray.setValueAtRegister(RegisterAddress.REGISTER_0, Word(10))
        registerArray.setValueAtRegister(RegisterAddress.REGISTER_1, Word(20))

        val sourceRegister1 = RegisterAddress.REGISTER_0
        val sourceRegister2 = RegisterAddress.REGISTER_1
        val destinationRegister = RegisterAddress.REGISTER_2

        val addInstruction = createAddInstruction(destinationRegister, sourceRegister1, sourceRegister2)

        addInstruction.execute(executionEnvironment)

        registerArray.getRegisterAt(destinationRegister)
            .getRegisterValue() `should be equal to` Word(30)
    }

    @Test
    internal fun `running add instructions using registers reads and updates the correct registers`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()
        val variables = listOf<ParsedData>()

        val registerAddress1 = RegisterAddress.REGISTER_1
        val registerAddress2 = RegisterAddress.REGISTER_2
        val registerAddress3 = RegisterAddress.REGISTER_3
        val registerAddress4 = RegisterAddress.REGISTER_4

        registerArray.setValueAtRegister(registerAddress1, Word(10))
        registerArray.setValueAtRegister(registerAddress2, Word(20))

        val add1 = createAddInstruction(registerAddress3, registerAddress1, registerAddress2)
        val add2 = createAddInstruction(registerAddress4, registerAddress2, registerAddress3)

        val instructions: List<Instruction> = listOf(
            add1,
            add2,
        )
        val program = Program(instructions, variables)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(registerAddress1)
            .getRegisterValue() `should be equal to` Word(10)

        registerArray.getRegisterAt(registerAddress2)
            .getRegisterValue() `should be equal to` Word(20)

        registerArray.getRegisterAt(registerAddress3)
            .getRegisterValue() `should be equal to` Word(30)

        registerArray.getRegisterAt(registerAddress4)
            .getRegisterValue() `should be equal to` Word(50)

        registerArray.getRegisterAt(RegisterAddress.REGISTER_5).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_6).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_7).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_8).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_9).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_10).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_11).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_12).getRegisterValue() `should be equal to` Word(0)
    }

}
