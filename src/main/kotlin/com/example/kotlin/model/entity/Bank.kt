package com.example.kotlin.model.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

// primary constructor
// default level is public --> exposed default getter / setter publicly
// data class auto generate equals, hash code, toString
@Entity
@Table(name = "tbl_bank")
data class Bank(
    @Id val accountNumber: String,
    val trust: Double,
    val transactionFee: Int
) {
//// to provided field ////
//    private [val/var] field_name: type ( ex: private val accountNumber: String )
//        get() = field                // defined getter for the field > No need because Kotlin val auto-generate getter
//        set(value) {field = value}   // defined setter for the field > Kotlin *var* also auto-generate setter
}