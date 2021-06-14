package com.greenfodor.medicalreports.presentation.patient.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.greenfodor.medicalreports.R
import com.greenfodor.medicalreports.databinding.ActivityViewReportBinding
import com.greenfodor.medicalreports.model.enums.*
import com.greenfodor.medicalreports.model.responses.GetReportResponse
import com.greenfodor.medicalreports.utils.viewBinding

class ViewReportActivity : AppCompatActivity() {

    companion object {
        const val REPORT_EXTRA_KEY = "REPORT_EXTRA_KEY"
    }

    private val binding by viewBinding(ActivityViewReportBinding::inflate)
    private lateinit var report: GetReportResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        report = intent.getParcelableExtra(REPORT_EXTRA_KEY) ?: return

        initViews()
    }

    private fun initViews() {
        binding.titleTv.text = getString(R.string.report_no, report.reportNo)
        binding.nameTv.text = getString(R.string.name_label, report.patientName)
        binding.authorTv.text = getString(R.string.author_name_label, report.authorName)

        setGeneralCondition()
        setHeartAction()
        setHeartSound()
        setBreathing()
        setHeadInjury()
    }

    private fun setGeneralCondition() {
        val isVisible = report.generalCondition != null
        binding.generalConditionTv.isVisible = isVisible
        if (!isVisible) return

        val text = when (report.generalCondition) {
            GeneralConditionEnum.CONSCIOUS.id -> GeneralConditionEnum.CONSCIOUS.text
            GeneralConditionEnum.SOMNOLENT.id -> GeneralConditionEnum.SOMNOLENT.text
            GeneralConditionEnum.SOPOR.id -> GeneralConditionEnum.SOPOR.text
            GeneralConditionEnum.COMA.id -> GeneralConditionEnum.COMA.text
            GeneralConditionEnum.DELIRIUM.id -> GeneralConditionEnum.DELIRIUM.text
            GeneralConditionEnum.DESORIENTATED.id -> GeneralConditionEnum.DESORIENTATED.text
            GeneralConditionEnum.SALLOW.id -> GeneralConditionEnum.SALLOW.text
            else -> ""
        }

        binding.generalConditionTv.text = getString(R.string.general_condition_label, text)
    }

    private fun setHeartAction() {
        val isVisible = report.heartAction != null
        binding.heartActionTv.isVisible = isVisible
        if (!isVisible) return

        val text = when (report.heartAction) {
            HeartActionEnum.RHYTHMIC.id -> HeartActionEnum.RHYTHMIC.text
            HeartActionEnum.ARHYTHMIC.id -> HeartActionEnum.ARHYTHMIC.text
            HeartActionEnum.GALOPPING.id -> HeartActionEnum.GALOPPING.text
            else -> ""
        }

        binding.heartActionTv.text = getString(R.string.heart_action_label, text)
    }

    private fun setHeartSound() {
        val isVisible = report.heartSound != null
        binding.heartSoundTv.isVisible = isVisible
        if (!isVisible) return

        val text = when (report.heartSound) {
            HeartSoundEnum.CLEAR.id -> HeartSoundEnum.CLEAR.text
            HeartSoundEnum.DULL.id -> HeartSoundEnum.DULL.text
            HeartSoundEnum.INAUDIBLE.id -> HeartSoundEnum.INAUDIBLE.text
            else -> ""
        }

        binding.heartSoundTv.text = getString(R.string.heart_sound_label, text)
    }

    private fun setBreathing() {
        val isVisible = report.breathing != null
        binding.breathingTv.isVisible = isVisible
        if (!isVisible) return

        val text = when (report.breathing) {
            BreathingEnum.VESICULAR.id -> BreathingEnum.VESICULAR.text
            BreathingEnum.WEAK_VESICULAR.id -> BreathingEnum.WEAK_VESICULAR.text
            BreathingEnum.PROLONGED_EXPIRIUM.id -> BreathingEnum.PROLONGED_EXPIRIUM.text
            BreathingEnum.PARADOXICAL.id -> BreathingEnum.PARADOXICAL.text
            BreathingEnum.ADDITIONAL_RESPIRATION_EFFORT.id -> BreathingEnum.ADDITIONAL_RESPIRATION_EFFORT.text
            else -> ""
        }

        binding.breathingTv.text = getString(R.string.breathing_label, text)
    }

    private fun setHeadInjury() {
        val isVisible = report.headInjury != null
        binding.headInjuryTv.isVisible = isVisible
        if (!isVisible) return

        val text = when (report.headInjury) {
            HeadInjuryEnum.PARIETAL_SCALP_WOUNDS.id -> HeadInjuryEnum.PARIETAL_SCALP_WOUNDS.text
            HeadInjuryEnum.HEMATOMA.id -> HeadInjuryEnum.HEMATOMA.text
            HeadInjuryEnum.LIQUID_BLOOD_NOSE_EARS.id -> HeadInjuryEnum.LIQUID_BLOOD_NOSE_EARS.text
            HeadInjuryEnum.PERIORBITAL_CHANGES.id -> HeadInjuryEnum.PERIORBITAL_CHANGES.text
            HeadInjuryEnum.FACIAL_INJURIES.id -> HeadInjuryEnum.FACIAL_INJURIES.text
            else -> ""
        }

        binding.headInjuryTv.text = getString(R.string.head_injury_label, text)
    }
}