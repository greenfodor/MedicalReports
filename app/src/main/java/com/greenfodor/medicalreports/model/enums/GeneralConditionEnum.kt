package com.greenfodor.medicalreports.model.enums

enum class GeneralConditionEnum(val id: Int, val text: String) {
    CONSCIOUS(1, "Conscious"),
    SOMNOLENT(2, "Somnolent"),
    SOPOR(3, "Sopor"),
    COMA(4, "Coma"),
    DELIRIUM(5, "Delirium"),
    DESORIENTATED(6, "Desorientated"),
    SALLOW(7, "Sallow")
}