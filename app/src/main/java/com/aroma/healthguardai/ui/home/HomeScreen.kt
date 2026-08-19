package com.aroma.healthguardai.ui.home

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.aroma.healthguardai.repository.RAGRepositoryProvider
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    onAskAI: () -> Unit
) {

    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    val ragRepository = remember {
        RAGRepositoryProvider.get(context)
    }

    var documentLoaded by remember {
        mutableStateOf(false)
    }

    var chunksCount by remember {
        mutableStateOf(0)
    }

    var message by remember {
        mutableStateOf("")
    }

    val pdfPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->

        if (uri != null) {

            scope.launch {

                try {

                    chunksCount =
                        ragRepository.processDocument(uri)

                    documentLoaded = true

                    message =
                        "Document processed successfully!"

                } catch (e: Exception) {

                    documentLoaded = false

                    message =
                        "Error: ${e.message}"
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "HealthGuardAI",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = "Your Private Health Assistant",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Button(
            onClick = {
                pdfPicker.launch(
                    arrayOf("application/pdf")
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Upload Health Document")
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        if (documentLoaded) {

            Text(
                text = "Document loaded successfully!"
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Chunks created: $chunksCount"
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Document processed successfully!"
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Button(
                onClick = onAskAI,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ask Health AI")
            }
        }

        if (message.isNotEmpty()) {

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = message
            )
        }
    }
}