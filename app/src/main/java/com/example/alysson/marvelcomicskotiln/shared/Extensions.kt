package com.example.alysson.marvelcomicskotiln.shared

import android.content.Intent
import android.support.v7.app.AppCompatActivity

fun <T> AppCompatActivity.intentFor(cls: Class<T>) = Intent(this, cls)