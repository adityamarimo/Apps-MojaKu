//
// Created by MacBook on 08/01/2021.
//

#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_moja_mojaku_core_utils_SecretDoor_getBaseUrl(JNIEnv *env, jobject /*this*/) {
    std::string baseUrl = "https://api.jikan.moe/v4/";
    return env->NewStringUTF(baseUrl.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_moja_mojaku_core_utils_SecretDoor_getHostName(JNIEnv *env, jobject){
    std::string hostName = "api.jikan.moe";
    return env->NewStringUTF(hostName.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_moja_mojaku_core_utils_SecretDoor_getSHA1(JNIEnv *env, jobject){
    std::string sha1 = "sha256/nkgigGydpZZdbxLG7CjylGxueujHetMqXEeplW5X71g=";
    return env->NewStringUTF(sha1.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_moja_mojaku_core_utils_SecretDoor_getSHA2(JNIEnv *env, jobject){
    std::string sha2 = "sha256/jQJTbIh0grw0/1TkHSumWb+Fs0Ggogr621gT3PvPKG0=";
    return env->NewStringUTF(sha2.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_moja_mojaku_core_utils_SecretDoor_getSHA3(JNIEnv *env, jobject){
    std::string sha3 = "sha256/C5+lpZ7tcVwmwQIMcRtPbsQtWLABXhQzejna0wHFr8M=";
    return env->NewStringUTF(sha3.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_moja_mojaku_core_utils_SecretDoor_getPassPhrase(JNIEnv *env, jobject){
    std::string pass = "manga";
    return env->NewStringUTF(pass.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_moja_mojaku_core_utils_SecretDoor_getDatabaseName(JNIEnv *env, jobject){
    std::string nameDatabase = "MojaKuManga.db";
    return env->NewStringUTF(nameDatabase.c_str());
}
