package com.demo.app

import android.os.Bundle
import org.json.JSONException
import org.json.JSONObject

object BundleUtils {
    fun convertJsonToBundle(json: JSONObject): Bundle {
        val bundle = Bundle()
        try {
            val iterator = json.keys()
            while (iterator.hasNext()) {
                val key = iterator.next() as String
                val value = json[key]
                when (value.javaClass.simpleName) {
                    "String" -> bundle.putString(key, value as String)
                    "Integer" -> bundle.putInt(key, (value as Int))
                    "Long" -> bundle.putLong(key, (value as Long))
                    "Boolean" -> bundle.putBoolean(key, (value as Boolean))
                    "JSONObject" -> {}
                    "Float" -> bundle.putFloat(key, (value as Float))
                    "Double" -> bundle.putDouble(key, (value as Double))
                    else -> bundle.putString(key, value.javaClass.simpleName)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return bundle
    }
}

fun JSONObject.toMap(): Map<String, Any> {
    val map = mutableMapOf<String, Any>()
    val keysItr: Iterator<String> = this.keys()
    while (keysItr.hasNext()) {
        val key = keysItr.next()
        var value: Any = this.get(key)
        when (value) {
            is JSONObject -> value = value.toMap()
        }
        map[key] = value
    }
    return map
}