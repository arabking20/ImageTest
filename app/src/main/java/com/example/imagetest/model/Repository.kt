package com.example.imagetest.model
import com.example.imagetest.R
import android.content.Context
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception

class Repository {
    //@Throws()
    fun deSerialize(context: Context): List<MovieResponse>
    {
        try {


            val jsonFileIS = context.resources.openRawResource(R.raw.movie_gist)
            val jsonString = convertIStoString(jsonFileIS)
            val movieResponseResult = convertStringToDataClass(jsonString)
            return movieResponseResult

        } catch (ex: Exception){
            // catch the error to prevent runtime crashes
            ex.printStackTrace()
            return emptyList()

        }finally {
            //it will be always executed,(unless you call system.exit)
            // for clean up
            println("Finally will be always invoked. ")

        }

    }



    private fun convertIStoString(inputStream: InputStream): String
    {
        val reader = BufferedReader(
            InputStreamReader(inputStream)
        )
        var line = reader.readLine()
        val result = StringBuilder()
        while (line != null) {
            result.append(line)
            line = reader.readLine()
        }
        return result.toString()
    }

    private fun convertStringToDataClass(inputString: String):
            List<MovieResponse>
    {

        val jsonArray = JSONArray(inputString)
        val result = mutableListOf<MovieResponse>()
        for (i in 0 until jsonArray.length()) {
            val elementJsonObject = jsonArray.getJSONObject(i)
            val movieResponse = MovieResponse(
                elementJsonObject.getString("title"),
                elementJsonObject.getString("image"),
                elementJsonObject.getDouble("rating"),
                elementJsonObject.getInt("releaseYear"),
                elementJsonObject.getJSONArray("genre").toList()

            )
            result.add(movieResponse)
        }
        return result
    }
    // fun TARGETNAME . funName(optionals): Optionals
    private fun JSONArray.toList(): List<String>{
        val result = mutableListOf<String>()
        for (i in 0 until this.length()) {
            result.add(
                this.getString(i)
            )
        }
        return result
    }
}