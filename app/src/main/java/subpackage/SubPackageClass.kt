// SubPackageClass.kt
package com.omerbatuhantandogan.hw1.subpackage

class SubPackageClass(primaryParam: String) {

    // Primary constructor property
    private val primaryProperty: String = primaryParam

    // Secondary constructor with default values
    constructor(primaryParam: String, secondaryParam: Int = 0) : this(primaryParam) {
        // Secondary constructor logic
    }

    // Member method
    fun memberMethod(): String {
        return "This is a member method. Primary property value: $primaryProperty"
    }

    // Companion object for static members and methods
    companion object {
        // Static member
        private const val staticMember: String = "This is a static member."

        // Static method
        fun staticMethod(): String {
            return "This is a static method. Static member value: $staticMember"
        }
    }
}
