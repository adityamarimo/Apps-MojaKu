package com.moja.mojaku.core.utils

object SecretDoor {
    init {
        System.loadLibrary("native-lib")
    }

    external fun getBaseUrl(): String
    external fun getHostName(): String
    external fun getSHA1(): String
    external fun getSHA2(): String
    external fun getSHA3(): String
    external fun getPassPhrase(): String
    external fun getDatabaseName(): String
}