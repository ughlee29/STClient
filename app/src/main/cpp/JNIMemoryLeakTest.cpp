#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_sillytavern_kotlin_engine_JNIMemoryLeakTest_nativePing(JNIEnv* env, jobject) {
    std::string message = "jni-ok";
    return env->NewStringUTF(message.c_str());
}
