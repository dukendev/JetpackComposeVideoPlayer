package com.ysanjeet535.jetpackcomposevideoplayer.utils

import android.util.Base64
import android.util.Patterns
import com.ysanjeet535.jetpackcomposevideoplayer.ui.video.DEFAULT_URL
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class StringUtils {
    companion object {
        fun String.encrypt(password: String): String {
            val secretKeySpec = SecretKeySpec(password.toByteArray(), "AES")
            val iv = ByteArray(16)
            val charArray = password.toCharArray()
            for (i in charArray.indices) {
                iv[i] = charArray[i].toByte()
            }
            val ivParameterSpec = IvParameterSpec(iv)

            val cipher = Cipher.getInstance("AES/GCM/NoPadding")
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec)

            val encryptedValue = cipher.doFinal(this.toByteArray())
            return Base64.encodeToString(encryptedValue, Base64.DEFAULT)
        }

        fun String.decrypt(password: String): String {
            val secretKeySpec = SecretKeySpec(password.toByteArray(), "AES")
            val iv = ByteArray(16)
            val charArray = password.toCharArray()
            for (i in charArray.indices) {
                iv[i] = charArray[i].toByte()
            }
            val ivParameterSpec = IvParameterSpec(iv)

            val cipher = Cipher.getInstance("AES/GCM/NoPadding")
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec)

            val decryptedByteValue = cipher.doFinal(Base64.decode(this, Base64.DEFAULT))
            return String(decryptedByteValue)
        }

        fun String.checkAndGetYoutubeURL(): String {
            return if (this.isYoutubeUrl()) {
                this
            } else DEFAULT_URL
        }

        fun String.isYoutubeUrl(): Boolean {
            return (Patterns.WEB_URL.matcher(this).matches())
        }
    }

}