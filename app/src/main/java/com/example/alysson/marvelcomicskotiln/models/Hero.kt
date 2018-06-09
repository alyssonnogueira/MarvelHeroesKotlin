package com.example.alysson.marvelcomicskotiln.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Hero : RealmObject() {

    @PrimaryKey
    var id: Int = 0
    var name: String? = null
    var description: String? = null
    var modified: String? = null
    var thumbnail: Thumbnail? = null
    var comics: Comics? = null
    var urls: RealmList<HeroUrls> = RealmList()
}