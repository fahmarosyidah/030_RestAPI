package com.example.consumerestapi.repository

import com.example.consumerestapi.model.Kontak
import com.example.consumerestapi.network.KontakService
import java.io.IOException
import java.lang.Exception

interface KontakRepository {
    /** Fetches List of Kontak from kontakApi*/
    suspend fun  getKontak(): List<Kontak>

    suspend fun insertKontak(kontak: Kontak)

    suspend fun updateKontak(id: Int, kontak: Kontak)

    suspend fun deleteKontak(id: Int)

    suspend fun getKontakById(id: Int): Kontak
}

class NetworkKontakRepository(
    private val kontakApiService: KontakService
): KontakRepository {
    /** Fetches List of Kontak from kontakApi*/
    override suspend fun getKontak(): List<Kontak> = kontakApiService.getKontak()

    override suspend fun insertKontak(kontak: Kontak) {
        kontakApiService.insertKontak(kontak)
    }

    override suspend fun updateKontak(id: Int, kontak: Kontak) {
        kontakApiService.updateKontak(id, kontak)
    }

    override suspend fun deleteKontak(id: Int) {
        try {
            val response = kontakApiService.deleteKontak(id)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete kontak. HTTP status code: ${response.code()}")
            }
            else {
                response.message()
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getKontakById(id: Int): Kontak {
        return kontakApiService.getKontakById(id)
    }
}