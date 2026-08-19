package com.aroma.healthguardai.ui.ondevice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import com.aroma.healthguardai.repository.OnDeviceLLMRepository

@Composable
fun OnDeviceTestScreen() {

    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    val repository = remember {
        OnDeviceLLMRepository(context)
    }

    var answer by remember {
        mutableStateOf("")
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    var isInitialized by remember {
        mutableStateOf(false)
    }

    var error by remember {
        mutableStateOf("")
    }

    // Initialize Gemma when screen opens
    LaunchedEffect(Unit) {

        isLoading = true
        error = ""

        try {

            repository.initialize()

            isInitialized = true

        } catch (e: Exception) {

            error =
                "Model initialization failed: ${e.message}"

        } finally {

            isLoading = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "On-Device Gemma Test"
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        if (isLoading) {

            CircularProgressIndicator()

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = "Loading Gemma model..."
            )

        } else {

            Button(
                onClick = {

                    scope.launch {

                        isLoading = true
                        answer = ""
                        error = ""

                        try {

                            answer =
                                repository.generateResponse(
                                    "Hello. Introduce yourself in one short sentence."
                                )

                        } catch (e: Exception) {

                            error =
                                "Error: ${e.message}"

                        } finally {

                            isLoading = false
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = isInitialized && !isLoading
            ) {

                Text(
                    text = "Test Gemma"
                )
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        if (error.isNotEmpty()) {

            Text(
                text = error
            )

        } else if (answer.isNotEmpty()) {

            Text(
                text = answer
            )
        }
    }
}