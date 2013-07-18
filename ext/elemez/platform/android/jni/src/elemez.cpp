#include <rhodes.h>
#include "rubyext/WebView.h"
#include <stdlib.h>
#include "ruby/ext/rho/rhoruby.h"

extern "C" void elemez_native_raiseDisruption(const char* sender, const char* source, int userInitiated) {

    JNIEnv *env = jnienv();

    jclass cls = rho_find_class(env, "com/elemez/Elemez");
    if (!cls) {
        return;
    }

    jmethodID mid = env->GetStaticMethodID(cls, "raiseDisruption", "(Ljava/lang/String;Ljava/lang/String;Z)V");
    if (!mid) {
        return;
    }

    jstring objSender = env->NewStringUTF(sender);
    jstring objSource = env->NewStringUTF(source);
    jboolean objUserInitiated = userInitiated == 1 ? JNI_TRUE : JNI_FALSE;

    env->CallStaticObjectMethod(cls, mid, objSender, objSource, objUserInitiated);
    
    env->DeleteLocalRef(objSender);
    env->DeleteLocalRef(objSource);
}
