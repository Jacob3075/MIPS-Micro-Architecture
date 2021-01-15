package com.jacob.mips.executors.sub_instruction;

import com.jacob.mips.executors.InstructionExecutorBuilder;
import com.jacob.mips.executors.InstructionExecutor;
import com.jacob.mips.models.MemoryArray;
import com.jacob.mips.models.RegisterFile;
import com.jacob.mips.models.Word32;

import java.util.BitSet;

public class SignExtendImmediate implements SubInstruction {
	private final Word32 immediate;
	public SignExtendImmediate(BitSet immediate) {
		immediate.set(16, 32, false);
		this.immediate = new Word32(immediate);
	}

	@Override
	public InstructionExecutor run(
			InstructionExecutor instructionExecutor, RegisterFile registerFile, MemoryArray memoryArray) {
		return new InstructionExecutorBuilder()
				.using(instructionExecutor)
				.setSignExtendedImmediate(immediate)
				.build();
	}
}