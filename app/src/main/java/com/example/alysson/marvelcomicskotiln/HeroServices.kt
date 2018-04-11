package com.example.alysson.marvelcomicskotiln

import android.content.Context
import android.provider.Settings.System.getString
import android.util.Base64
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.experimental.and
import kotlin.experimental.or

class HeroServices (val context: Context) {

    val URL = "http://gateway.marvel.com/v1/public/characters"

    val client : OkHttpClient = OkHttpClient()

    val timeStamp = System.currentTimeMillis().toString()

    var lastId = 0
    var limitId = 0

    fun getHeroes() : List<Hero> {

        val request : Request
 //"$URL?ts=$timeStamp&apikey="+        context.getString(R.string.PUBLIC_KEY)+                "&hash="+toMD5Hash()
        if(lastId == 0) {
            request = Request.Builder()
                    .url("$URL?ts=1523024155985&apikey=00554ed173ff5ed8e7af907d22bfbd12&hash=f26638fe90b831e4af32ab5434492fc7"
                    ).build()
        } else {
            if(lastId<limitId) {
                val newId = lastId++
                request = Request.Builder()
                        .url("$URL/$newId?ts=1523024155985&apikey=00554ed173ff5ed8e7af907d22bfbd12"+
                        "hash=f26638fe90b831e4af32ab5434492fc7"
                        )
                        .build()
            } else {
                return listOf()
            }
        }

        val response : Response = client.newCall(request).execute()

        val responseObject = JSONObject(response.body()?.string())

        System.out.println("MY GOD!!")

        //System.out.println(responseObject)
       val listHeroesObservable = parserJson(responseObject)

        return listHeroesObservable
        //return listOf()


//        val responseObject = gson.fromJson<Hero>(response.body()?.string())

    }

    fun parserJson(parserResponse: JSONObject): List<Hero> {

        val listHeroes = mutableListOf<Hero>()

        val resultsArray= parserResponse.getJSONObject("data").getJSONArray("results")

        val gson = GsonBuilder().create()

        resultsArray?.let {
            println(it)
            listHeroes.add(gson.fromJson(it.toString(), Hero::class.java))
        }
        return listHeroes
    }


        private fun doHashForRequest(): String {
        val HEX_CHARS = "0123456789ABCDEF"

        val hash = timeStamp+context.getString(R.string.PRIVATE_KEY)+context.getString(R.string.PUBLIC_KEY)

        val bytes = MessageDigest
                .getInstance("MD5")
                .digest(hash.toByteArray())
        val result = StringBuilder(bytes.size * 2)

        bytes.forEach {
            val i = it.toInt()
            //result.append(HEX_CHARS[i and 0xFF or 0x100]).substring(1,3)
            result.append(hash[i shr 4 and 0x0f])
            result.append(hash[i and 0x0f])
        }

        return result.toString()
    }

    fun doHashForRequestNAOFUNCIONA(): String? {
        val timeStamp = System.currentTimeMillis().toString()
        println(timeStamp)
        val hash = timeStamp+context.getString(R.string.PUBLIC_KEY)+context.getString(R.string.PRIVATE_KEY)
        println(hash)
        val encTarget = hash.toByteArray()
        val mdEnc: MessageDigest?
        return try {
            mdEnc = MessageDigest.getInstance("MD5")
            // Encryption algorithmy
            val md5Base16 = BigInteger(1, mdEnc.digest(encTarget))     // calculate md5 hash
            Base64.encodeToString(md5Base16.toByteArray(), 16).trim()     // convert from base16 to base64 and remove the new line character
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            null
        }
    }

    private fun byteArrayToHexString( array: Array<Byte> ): String {

        var result = StringBuilder(array.size * 2)

        for ( byte in array ) {

            val toAppend =
                    String.format("%2X", byte).replace(" ", "0") // hexadecimal
            result.append(toAppend).append("-")
        }
        result.setLength(result.length - 1) // remove last '-'

        return result.toString()
    }

    private fun toMD5Hash(): String {
        var text = timeStamp+context.getString(R.string.PRIVATE_KEY)+context.getString(R.string.PUBLIC_KEY)

        var result = ""

        try {

            val md5 = MessageDigest.getInstance("MD5")
            val md5HashBytes = md5.digest(text.toByteArray()).toTypedArray()

            result = byteArrayToHexString(md5HashBytes)
        }
        catch ( e: Exception ) {

            result = "error: ${e.message}"
        }

        return result
    }

}