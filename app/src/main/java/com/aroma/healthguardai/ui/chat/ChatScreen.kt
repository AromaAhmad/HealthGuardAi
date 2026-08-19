package com.aroma.healthguardai.ui.chat
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aroma.healthguardai.viewmodel.ChatViewModel
import androidx.compose.material3.RadioButton
@Composable
fun ChatScreen(
    chatViewModel: ChatViewModel = viewModel()
) {

    var question by remember {
        mutableStateOf("")
    }
    var selectedModel by remember {
        mutableStateOf("Gemini")
    }

    val answer by chatViewModel.answer.collectAsState()

    val isLoading by chatViewModel.isLoading.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {

        Text(
            text = "Health AI Assistant"
        )
        Text(
            text = "Choose AI Model"
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            RadioButton(
                selected = selectedModel == "Gemini",
                onClick = {
                    selectedModel = "Gemini"
                }
            )

            Text(
                text = "Gemini Cloud"
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            RadioButton(
                selected = selectedModel == "Gemma",
                onClick = {
                    selectedModel = "Gemma"
                }
            )

            Text(
                text = "On-device Gemma"
            )
        }
        Spacer(
            modifier = Modifier.height(16.dp)
        )

        if (answer.isNotEmpty()) {

            Text(
                text = answer,
                modifier = Modifier.padding(
                    bottom = 16.dp
                )
            )
        }

        if (isLoading) {

            CircularProgressIndicator(
                modifier = Modifier.padding(
                    bottom = 16.dp
                )
            )
        }

        OutlinedTextField(
            value = question,
            onValueChange = {
                question = it
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text("Ask a health question...")
            }
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Button(
            onClick = {

                chatViewModel.askQuestion(

                        question = question,
                        model = selectedModel
                    )

            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading && question.isNotBlank()
        ) {
            Text("Ask AI")
        }
    }
}