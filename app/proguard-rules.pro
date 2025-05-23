# MOSHI: Полная защита
-keep class com.squareup.moshi.** { *; }
-keep class * extends com.squareup.moshi.JsonAdapter { *; }
-keepclasseswithmembers class * {
    @com.squareup.moshi.Json <fields>;
}

# RETROFIT
-keep class retrofit2.** { *; }
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

# Сохраняем информацию о дженериках
-keepattributes Signature

# Сохраняем аннотации (нужны Moshi)
-keepattributes *Annotation*

# Сохраняем все классы с @JsonClass (generateAdapter = true)
-keep class * {
    @com.squareup.moshi.JsonClass <fields>;
}
-keep @com.squareup.moshi.JsonClass class * {
    <init>(...);
}

# Конкретно сохранить ваши модели
-keep class com.example.marvelheroes.data.models.** { *; }

# Если используете KotlinJsonAdapterFactory — сохранить классы с kotlin-модулями
-keep class kotlin.Metadata

-dontwarn coil3.PlatformContext

# Generic-типы (обязательно!)
-keepattributes Signature, RuntimeVisibleAnnotations