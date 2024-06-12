package br.com.fiap.locawebchallenge.shared.utils

import java.security.MessageDigest

fun encrypter(password: String): String {
    val bytes = password.toByteArray()
    val md5Digest = MessageDigest.getInstance("MD5").digest(bytes)
    return md5Digest.joinToString("") { "%02x".format(it) }
}