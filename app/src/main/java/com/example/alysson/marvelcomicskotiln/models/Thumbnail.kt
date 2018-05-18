package com.example.alysson.marvelcomicskotiln.models


import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

@RealmClass
open class Thumbnail : RealmObject(){

    @PrimaryKey
    var path:String? = null
    var extension:String? = null

    fun getUrl(): String {
        return "$path.$extension"
    }
}