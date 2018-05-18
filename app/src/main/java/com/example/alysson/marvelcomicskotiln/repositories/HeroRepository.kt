package com.example.alysson.marvelcomicskotiln.repositories

import android.content.Context
import android.util.Log
import com.example.alysson.marvelcomicskotiln.R
import com.example.alysson.marvelcomicskotiln.models.Hero
import com.example.alysson.marvelcomicskotiln.services.HeroService
import com.example.alysson.marvelcomicskotiln.services.RetrofitClient
import com.google.gson.Gson
import com.google.gson.JsonObject
import io.reactivex.Observable
import io.realm.Realm
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import okhttp3.RequestBody
import org.json.JSONObject
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*
import android.R.attr.publicKey
import android.graphics.Bitmap
import android.widget.ImageView
import com.example.alysson.marvelcomicskotiln.models.CharacterDataContainer
import com.squareup.picasso.Picasso


class HeroRepository(val context: Context) {

    private var heroService: HeroService? = null
    private var realm: Realm = Realm.getDefaultInstance()
    private val timeStamp: Long = Date().time
    private var hash: String = ""
    private val apiKey: String

    init {
        val retrofit = RetrofitClient.getClient()
        this.heroService = retrofit!!.create(HeroService::class.java)
        apiKey = this.context.getString(R.string.PUBLIC_KEY)
        hash = md5()
        Log.d("HeroRepository", "Init works")
    }

    fun saveObj(obj: RealmObject) {
        this.openDB()

        realm.executeTransaction {
            it.copyToRealmOrUpdate(obj)
        }
        realm.close()
    }

    fun findHero(id: Int): Hero? {

        this.openDB()
        return this.realm.where(Hero::class.java).equalTo("id", id).findFirst()

    }


    fun findFirstCharacterDataContainer(): CharacterDataContainer? {
        this.openDB()

        return realm.where(CharacterDataContainer::class.java).findFirst()

    }

    fun getHeroes(offset: Int): Observable<JsonObject> {
        return this.heroService!!.getAll(timeStamp, apiKey, hash, offset)
    }

    fun thumbnailUrl(url: String): String {
        return "$url?ts=$timeStamp&apikey=$publicKey&hash=$hash"
    }

    fun setHeroPerfil(hero: Hero, perfil: ImageView): Unit {
        return Picasso.get().load(thumbnailUrl(hero.thumbnail!!.getUrl())).into(perfil)
    }

//    fun getUser(): User? {
//        this.openDB()
//
//        return realm.where(User::class.java).findFirst()
//    }
//
//    fun signIn(phone: String, password: String): Observable<JsonObject>? {
//        return this.heroService?.signIn(phone, password)
//    }
//
//    fun completeRegister(user: User): Observable<JsonObject>? {
//        val userString = JSONObject(Gson().toJson(realm.copyFromRealm(user)))
//        val json = JSONObject().put("user", userString)
//        val request = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json.toString())
//        return this.heroService?.completeRegister(request)
 //  }

    private fun md5(): String {
        val hash = "$timeStamp${context.getString(R.string.PRIVATE_KEY)}$apiKey"

        val messageDigest = MessageDigest.getInstance("MD5")
        messageDigest.update(hash.toByteArray(), 0, hash.length)

        return BigInteger(1, messageDigest.digest()).toString(16)
    }

    private fun openDB(){
        if (realm.isClosed)
            realm = Realm.getDefaultInstance()
    }

}