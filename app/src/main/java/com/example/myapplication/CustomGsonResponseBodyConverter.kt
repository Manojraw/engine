package com.example.myapplication

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException
import java.io.StringReader

class CustomGsonResponseBodyConverter<T>(
    private val gson: Gson,
    private val adapter: TypeAdapter<T>
) :
    Converter<ResponseBody, T> {
    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T {
        val response = value.string()
        try {
            val jsonReader = gson.newJsonReader(StringReader(response))
            jsonReader.isLenient = true // 👈 IMPORTANT! Ye lax mode me parsing karega
            return adapter.read(jsonReader)
        } catch (e: Exception) {
            throw IOException("Parsing error: " + e.message)
        } finally {
            value.close()
        }
    }
}
