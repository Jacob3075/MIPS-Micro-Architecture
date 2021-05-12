package com.jacob.core_lib.common

import com.jacob.core_lib.common.addresses.RegisterAddress
import kotlin.reflect.KFunction1

private val pattern = Regex("R\\d+")

fun <T> List<T?>.padListTill(newLength: Int): MutableList<T?> =
    List(maxOf(newLength, size)) { index -> getOrNull(index) }.toMutableList()

fun <T> String.toRegisterAddress(convertToAddressOfType: KFunction1<RegisterAddress, T>): T {
    require(pattern.matches(this))

    return this.replace("R", "REGISTER_")
        .let(RegisterAddress::valueOf)
        .let(convertToAddressOfType)
}

fun List<String>.toRegisterAddresses(): List<RegisterAddress> {
    return this.map { it.replace("R", "REGISTER_") }
        .map(RegisterAddress::valueOf)
}
