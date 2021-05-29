package com.greenfodor.medicalreports.networking

import android.util.Log
import com.google.gson.Gson
import com.greenfodor.medicalreports.R
import com.greenfodor.medicalreports.model.responses.ApiResponse
import com.greenfodor.medicalreports.model.responses.ErrorResponse
import com.greenfodor.medicalreports.utils.UiUtils.getString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONException
import retrofit2.Response
import java.net.UnknownHostException

abstract class SafeApiRequest {
    suspend fun <T : Any?> apiRequest(call: suspend () -> Response<T>): T {
        val response = try {
            call.invoke()
        } catch (e: Exception) {
            Log.e("ApiError", e.message, e)
            val message = when (e) {
                is UnknownHostException -> getString(R.string.no_internet_error_message)
                else -> e.message ?: getString(R.string.something_went_wrong)
            }

            throw ErrorResponse(message)
        }

        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val error = withContext(Dispatchers.IO) { response.errorBody()?.string() }
            val message = StringBuilder()
            error?.let {
                try {
                    val apiResponse = Gson().fromJson(it, ApiResponse::class.java)
                    val errorMessage = if (!apiResponse.data.isNullOrEmpty()) apiResponse.data else apiResponse.message
                    message.append(errorMessage)
                } catch (e: JSONException) {
                    Log.e("ApiError", e.message, e)
                    throw ErrorResponse(e.message ?: getString(R.string.something_went_wrong))
                }
            }

            throw ErrorResponse(message.toString())
        }
    }
}