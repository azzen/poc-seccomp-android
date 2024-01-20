#include <jni.h>
#include <string>
#include <unistd.h>
#include <sys/syscall.h>
#include <sys/types.h>

extern "C" JNIEXPORT jstring

JNICALL
Java_com_example_poc_1seccomp_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {

    pid_t pid;
    pid = syscall(SYS_getpid);

    std::string s{std::to_string(pid)};

    // make unauthorized syscall
    const char *path = "/";
    int result = syscall(SYS_chroot, path);

    if (result == -1) {
        perror("SYS_chroot failed but wasn't blocked by seccomp");
        return env->NewStringUTF(std::string{"Hello world"}.c_str());
    }

    return env->NewStringUTF(s.c_str());
}