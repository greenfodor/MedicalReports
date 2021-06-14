package com.greenfodor.medicalreports.model.enums

enum class BreathingEnum(val id: Int, val text: String) {
    VESICULAR(1, "Vesicular"),
    WEAK_VESICULAR(2, "Weak Vesicular"),
    PROLONGED_EXPIRIUM(3, "Prolonged Expirium"),
    PARADOXICAL(4, "Paradoxical"),
    ADDITIONAL_RESPIRATION_EFFORT(5, "Additional Respiration Effort")
}