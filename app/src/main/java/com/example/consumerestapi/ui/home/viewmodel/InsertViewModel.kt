package com.example.consumerestapi.ui.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.consumerestapi.model.Kontak
import com.example.consumerestapi.repository.KontakRepository

class InsertViewModel(private val kontakRepository: KontakRepository) : ViewModel() {
    var insertKontakState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertKontakState(insertUiEvent: InsertUiEvent) {
        insertKontakState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertKontak() {
        viewModelScope
    }
}

data class InsertUiEvent(
    val id: Int = 0,
    val nama: String = "",
    val alamat: String = "",
    val nohp: String = ""
)

fun InsertUiEvent.toKontak() : Kontak = Kontak(
    id = id,
    nama = nama,
    alamat = alamat,
    nohp = nohp
)

data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)

fun Kontak.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    id = id,
    nama = nama,
    alamat = alamat,
    nohp = nohp
)

fun Kontak.toUiStateKontak(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

