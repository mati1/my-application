# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Development\Android_SDK/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# Multiple bottom sheets in app break without this
-keep class com.google.android.material.bottomsheet.BottomSheetBehavior { *; }

# Required by `EitherCallAdapterFactory`
-keep,allowoptimization,allowshrinking,allowobfuscation class arrow.core.Either

# Keep the class names of views for a more understandable observability logs
-keepnames class * extends androidx.appcompat.app.AppCompatActivity
-keepnames class * extends androidx.fragment.app.Fragment

# Firebase Crashlytics
# source: https://firebase.google.com/docs/crashlytics/get-deobfuscated-reports?platform=android
-keepattributes SourceFile,LineNumberTable        # Keep file names and line numbers.
-keep public class * extends java.lang.Exception  # Optional: Keep custom exceptions.

# Proguard/R8 need this to ensure that retracing stack traces is unambiguous. Otherwise above rule doesn't fully work.
## https://developer.android.com/studio/build/shrink-code#decode-stack-trace
-renamesourcefileattribute SourceFile


# Sendbird
-dontwarn com.sendbird.android.shadow.**

# Legacy ProcessOut SDK
-keep class com.processout.processout_sdk.** { *; }
