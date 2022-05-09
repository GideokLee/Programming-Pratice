package com.lg.conn.testbleplayer

import java.math.BigInteger
import java.util.*
import kotlin.experimental.and

/* This class encapsulates utility functions */

class Conversion1 {

    companion object Conversion {
        fun loUint16(v: Short): Byte {
            return (v and 0xFF) as Byte
        }

        //    fun hiUint16(v: Short): Byte {
        fun hiUint16(v: BigInteger): Byte {
            return (v shr 8) as Byte
        }

        //    fun buildUint16(hi: Byte, lo: Byte): Short {
//        return ((hi shl 8) + (lo and 0xff))
//    }
        fun buildUint16(hi: BigInteger, lo: Short): Short {
            return ((hi shl 8) as Short + (lo and 0xff)) as Short
        }

        fun BytetohexString(b: ByteArray, len: Int): String {
            val sb = StringBuilder(b.size * (2 + 1))
            val formatter = Formatter(sb)
            for (i in 0 until len) {
                if (i < len - 1) formatter.format("%02X:", b[i]) else formatter.format("%02X", b[i])
            }
            formatter.close()
            return sb.toString()
        }

        fun BytetohexString(b: ByteArray, reverse: Boolean): String {
            val sb = StringBuilder(b.size * (2 + 1))
            val formatter = Formatter(sb)
            if (!reverse) {
                for (i in b.indices) {
                    if (i < b.size - 1) formatter.format(
                        "%02X:",
                        b[i]
                    ) else formatter.format("%02X", b[i])
                }
            } else {
                for (i in b.size - 1 downTo 0) {
                    if (i > 0) formatter.format("%02X:", b[i]) else formatter.format("%02X", b[i])
                }
            }
            formatter.close()
            return sb.toString()
        }

        // Convert hex String to Byte
        fun hexStringtoByte(sb: String?, results: ByteArray): Int {
            var i = 0
            var j = false
            if (sb != null) {
                for (k in 0 until sb.length) {
                    if (sb[k] >= '0' && sb[k] <= '9' || sb[k] >= 'a' && sb[k] <= 'f'
                        || sb[k] >= 'A' && sb[k] <= 'F'
                    ) {
                        if (j) {
//                        (results[i] += Character.digit(sb[k], 16).toByte()).toByte()
                            val conv = Character.digit(sb[k], 16)
                            val result = (results[i] + conv).toByte()
                            results[i] = result
                            i++
                        } else {
                            results[i] = (Character.digit(sb[k], 16) shl 4).toByte()
                        }
                        j = !j
                    }
                }
            }
            return i
        }

        fun isAsciiPrintable(str: String?): Boolean {
            if (str == null) {
                return false
            }
            val sz = str.length
            for (i in 0 until sz) {
                if (isAsciiPrintable(str[i]) == false) {
                    return false
                }
            }
            return true
        }

        private fun isAsciiPrintable(ch: Char): Boolean {
            return ch.toInt() >= 32 && ch.toInt() < 127
        }
    }
}