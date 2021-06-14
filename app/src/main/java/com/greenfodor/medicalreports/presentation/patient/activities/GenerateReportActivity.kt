package com.greenfodor.medicalreports.presentation.patient.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.greenfodor.medicalreports.R
import com.greenfodor.medicalreports.databinding.ActivityGenerateReportBinding
import com.greenfodor.medicalreports.model.dbo.Patient
import com.greenfodor.medicalreports.model.enums.*
import com.greenfodor.medicalreports.presentation.patient.viewmodels.GenerateReportViewModel
import com.greenfodor.medicalreports.utils.viewBinding
import org.koin.android.viewmodel.ext.android.viewModel

class GenerateReportActivity : AppCompatActivity() {

    companion object {
        const val PATIENT_EXTRA_KEY = "PATIENT_EXTRA_KEY"
    }

    private val binding by viewBinding(ActivityGenerateReportBinding::inflate)
    private val generateReportViewModel: GenerateReportViewModel by viewModel()

    private lateinit var patient: Patient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        patient = intent.getParcelableExtra(ViewPatientActivity.PATIENT_EXTRA_KEY) ?: return
        initViews()
        initActions()
        initObservers()
    }

    private fun initViews() {
        binding.nameTv.text = getString(R.string.name_label, patient.name)
        binding.dobTv.text = getString(R.string.dob_label, patient.dob.toString("dd-MM-yyyy"))
        binding.genderTv.text = getString(R.string.gender_label, patient.gender)
    }

    private fun initActions() {
        binding.generateBtn.setOnClickListener {
            val generalCondition = getGeneralCondition()
            val heartAction = getHeartAction()
            val heartSound = getHeartSound()
            val breathing = getBreathing()
            val headInjury = getHeadInjury()

            generateReportViewModel.generateReport(patient.id, generalCondition, heartAction, heartSound, breathing, headInjury)
        }
    }

    private fun initObservers() {
        generateReportViewModel.onReportGenerated.observe(this) { report ->
            report ?: return@observe

            val intent = Intent(this, ViewReportActivity::class.java).apply {
                putExtra(ViewReportActivity.REPORT_EXTRA_KEY, report)
            }
            startActivity(intent)
            finish()
        }

        generateReportViewModel.isLoading.observe(this) { isLoading ->
            isLoading ?: return@observe
            binding.loadingLottie.isVisible = isLoading
        }

        generateReportViewModel.showError.observe(this) { error ->
            error ?: return@observe

            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun getGeneralCondition(): Int? {
        return when (binding.generalConditionRg.checkedRadioButtonId) {
            binding.consciousRb.id -> GeneralConditionEnum.CONSCIOUS.id
            binding.somnolentRb.id -> GeneralConditionEnum.SOMNOLENT.id
            binding.soporRb.id -> GeneralConditionEnum.SOPOR.id
            binding.comaRb.id -> GeneralConditionEnum.COMA.id
            binding.deliriumRb.id -> GeneralConditionEnum.DELIRIUM.id
            binding.desorientatedRb.id -> GeneralConditionEnum.DESORIENTATED.id
            binding.sallowRb.id -> GeneralConditionEnum.SALLOW.id
            else -> null
        }
    }

    private fun getHeartAction(): Int? {
        return when (binding.heartActionRg.checkedRadioButtonId) {
            binding.rhythmicRb.id -> HeartActionEnum.RHYTHMIC.id
            binding.arhythmicRb.id -> HeartActionEnum.ARHYTHMIC.id
            binding.gallopingRb.id -> HeartActionEnum.GALOPPING.id
            else -> null
        }
    }

    private fun getHeartSound(): Int? {
        return when (binding.heartSoundRg.checkedRadioButtonId) {
            binding.clearRb.id -> HeartSoundEnum.CLEAR.id
            binding.dullRb.id -> HeartSoundEnum.DULL.id
            binding.inaudibleRb.id -> HeartSoundEnum.INAUDIBLE.id
            else -> null
        }
    }

    private fun getBreathing(): Int? {
        return when (binding.breathingRg.checkedRadioButtonId) {
            binding.vesicularRb.id -> BreathingEnum.VESICULAR.id
            binding.weakVesicularRb.id -> BreathingEnum.WEAK_VESICULAR.id
            binding.prolongedExpiriumRb.id -> BreathingEnum.PROLONGED_EXPIRIUM.id
            binding.parodoxicalRb.id -> BreathingEnum.PARADOXICAL.id
            binding.additionalRespirationEffortRb.id -> BreathingEnum.ADDITIONAL_RESPIRATION_EFFORT.id
            else -> null
        }
    }

    private fun getHeadInjury(): Int? {
        return when (binding.headInjuryRg.checkedRadioButtonId) {
            binding.parietalScalpWoundsRb.id -> HeadInjuryEnum.PARIETAL_SCALP_WOUNDS.id
            binding.hematomaRb.id -> HeadInjuryEnum.HEMATOMA.id
            binding.liquidBloodNoseEarsRb.id -> HeadInjuryEnum.LIQUID_BLOOD_NOSE_EARS.id
            binding.periorbitalChangesRb.id -> HeadInjuryEnum.PERIORBITAL_CHANGES.id
            binding.facialInjuriesRb.id -> HeadInjuryEnum.FACIAL_INJURIES.id
            else -> null
        }
    }
}