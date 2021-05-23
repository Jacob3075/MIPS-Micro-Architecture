package com.jacob.core_lib.common

import com.jacob.core_lib.common.addresses.MemoryAddress
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.word.ImmediateValue
import com.jacob.core_lib.word.Word
import kotlin.math.pow
import kotlin.reflect.KFunction1

fun Boolean.toInt() = if (this) 1 else 0

infix fun Int.`**`(exponent: Int): Int = toDouble().pow(exponent).toInt()

fun <T> List<T?>.padListTill(newLength: Int): MutableList<T?> =
    List(maxOf(newLength, size)) { index -> getOrNull(index) }.toMutableList()

fun <T> String.toRegisterAddress(convertToAddressOfType: KFunction1<RegisterAddress, T>) =
    this.replace("R", "REGISTER_")
        .let(RegisterAddress::valueOf)
        .let(convertToAddressOfType)

fun List<String>.toRegisterAddresses(): List<RegisterAddress> {
    return this.map { it.replace("R", "REGISTER_") }
        .map(RegisterAddress::valueOf)
}

val Int.W: Word
    get() = Word(this)

val Int.I: ImmediateValue
    get() = ImmediateValue(this)

val Int.MA: MemoryAddress
    get() = MemoryAddress(this)

val Int.RA: RegisterAddress
    get() {
        require(this <= 15)
        return RegisterAddress.valueOf("REGISTER_$this")
    }

fun String.immediateFromDec() = this.removePrefix("#")
    .toInt()
    .let(::ImmediateValue)

fun String.immediateFromHex() = this.removePrefix("#0X")
    .toInt(16)
    .let(::ImmediateValue)
