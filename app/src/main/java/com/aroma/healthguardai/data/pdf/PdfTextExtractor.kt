package com.aroma.healthguardai.data.pdf

import android.content.Context
import android.net.Uri
import com.tom_roush.pdfbox.android.PDFBoxResourceLoader
import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.text.PDFTextStripper

class PdfTextExtractor(
    private val context: Context
) {

    init {
        PDFBoxResourceLoader.init(context)
    }

    fun extractText(uri: Uri): String {

        val inputStream = context.contentResolver.openInputStream(uri)
            ?: return ""

        inputStream.use { stream ->

            PDDocument.load(stream).use { document ->

                val stripper = PDFTextStripper()

                return stripper.getText(document)
            }
        }
    }
}