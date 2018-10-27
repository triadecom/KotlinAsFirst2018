@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson3.task1.len
import kotlin.math.sqrt
import java.lang.Math.pow

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var mod = 0.0
    if (v.size > 0) {
        for (i in 0 until v.size) {
            mod += v[i] * v[i]
        }
    }
    return sqrt(mod)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if (list.isEmpty()) return 0.0
    return list.sum() / list.size
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val averageDigit = mean(list)

    for (i in 0 until list.size)
        list[i] -= averageDigit

    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    if (a.isEmpty() || b.isEmpty()) return 0.0
    var C = 0.0

    for (i in 0 until a.size) {
        C += (a[i] * b[i])
    }
    return C
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double =
        p.withIndex().fold(0.0) { previousResult, (index, value) ->
            previousResult + value * pow(x, index.toDouble())
        }


/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    for (i in 1 until list.size) list[i] += list[i - 1]
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var result = mutableListOf<Int>()
    var a = 2
    var num = n

    while (a <= num) {
        if (num % a == 0) {
            result.add(a)
            num /= a
        } else {
            a++
        }
    }

    return result.sorted()
}


/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")


/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var result = mutableListOf<Int>()
    var a = n

    while (a != 0) {
        result.add(a % base)
        a /= base
    }
    return result.reversed()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String {
    var result = ""
    var a = n
    var rem = 0

    while (a != 0) {
        rem = a % base
        result += if (rem < 10) rem.toString() else ('a' + (rem - 10))
        a /= base
    }
    return result.reversed()
}


/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var a = 0.0
    var result = 0
    for (element in digits.reversed()) {
        result += (element * pow(a, base.toDouble())).toInt()
        a++
    }
    return result
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int = TODO()

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 =
 */
fun roman(n: Int): String = TODO()

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */


val charsetSimple = listOf(
        "ноль",                 // charsetSimple[0]
        "один",                 // charsetSimple[1]
        "два",                  // charsetSimple[2]
        "три",                  // charsetSimple[3]
        "четыре",               // charsetSimple[4]
        "пять",                 // charsetSimple[5]
        "шесть",                // charsetSimple[6]
        "семь",                 // charsetSimple[7]
        "восемь",               // charsetSimple[8]
        "девять")               // charsetSimple[9]

val charsetDec = listOf(
        "десять",               // charsetDec[0]
        "двадцать",             // charsetDec[1]
        "тридцать",             // charsetDec[2]
        "сорок",                // charsetDec[3]
        "пятьдесят",            // charsetDec[4]
        "шестьдесят",           // charsetDec[5]
        "семьдесят",            // charsetDec[6]
        "восемьдесят",          // charsetDec[7]
        "девяносто")            // charsetDec[8]

val charsetHundreds = listOf(
        "сто",                  // charsetHundreds[0]
        "двести",               // charsetHundreds[1]
        "триста",               // charsetHundreds[2]
        "четыреста",            // charsetHundreds[3]
        "пятьсот",              // charsetHundreds[4]
        "шестьсот",             // charsetHundreds[5]
        "семьсот",              // charsetHundreds[6]
        "восемьсот",            // charsetHundreds[7]
        "девятьсот")            // charsetHundreds[8]

val charsetDecSimple = listOf(
        "одинадцать",           // charsetDecSimple[0]
        "двенадцать",           // charsetDecSimple[1]
        "тринадцать",           // charsetDecSimple[2]
        "четырнадцать",         // charsetDecSimple[3]
        "пятнадцать",           // charsetDecSimple[4]
        "шестнадцать",          // charsetDecSimple[5]
        "семнадцать",           // charsetDecSimple[6]
        "восемнадцать",         // charsetDecSimple[7]
        "девятнадцать")         // charsetDecSimple[8]

val charsetExeptions = listOf(
        "тысяча",               // charsetExeptions[0]
        "тысяч",                // charsetExeptions[1]
        "тысячи",               // charsetExeptions[2]
        "одна",                 // charsetExeptions[3]
        "две")                  // charsetExeptions[4]

/*
Простые числа - список charsetSimple[0..9]
десятки, начиная от Десяти - список charsetDec[0..8]
сотни, начиная ста - список charsetHundreds[0..8]
Исключения - список charsetExeptions[0..4]
*/


fun numberDefine(n: Int): List<Int> {      // Функция, разбивающая целое число на её составляющие
    val digit = mutableListOf<Int>()
    var a = 10
    var b = 1


    for (i in 0 until Math.ceil(Math.log10(n.toDouble())).toInt()) {
        digit.add(n % a / b)
        a *= 10
        b *= 10
    }
    return digit.reversed()
}


fun triadeDefine(n: Int, hundred: Boolean): String {
    var digit = listOf<Int>()
    digit = numberDefine(n)
    var charResult = mutableListOf<String>()
    for (i in 0 until 10) if (digit[0] == i) charResult.add(charsetHundreds[i - 1])
    if (digit[1] == 1) for (i in 0 until 10) if (digit[2] == i) charResult.add(charsetDecSimple[i - 1])
    for (i in 0 until 10) if (digit[1] == i) charResult.add(charsetDec[i - 1])
    if ((hundred == true) && (digit[2] == 1)) charResult.add(charsetExeptions[3])
    for (i in 0 until 10) if ((digit[2] == i) && digit[2] > 1) charResult.add(charsetSimple[i])

    return charResult.joinToString(separator = " ")
}


fun russian(n: Int): String {
    val digits = numberDefine(n)
    val length = Math.ceil(Math.log10(n.toDouble())).toInt()
    var result = mutableListOf<String>()
    var triade = ""

    if (length == 6) {
        triade = digits[0].toString() + digits[1].toString() + digits[2].toString()
        result.add(triadeDefine(triade.toInt(), true))

        if (digits[2] == 1) result.add(charsetExeptions[0])
        else if ((digits[2] > 1) && (digits[2] < 5)) result.add(charsetExeptions[2])
        result.add(charsetExeptions[1])

        triade = digits[3].toString() + digits[4].toString() + digits[5].toString()
        result.add(triadeDefine(triade.toInt(), false))
        return result.joinToString(separator = " ")

    }

    if (length == 5) {
        for (i in 0 until 10) {
            if ((digits[0] == 1) && (digits[1] == i)) result.add(charsetDecSimple[i - 1])
            else if ((digits[0] == i) && (digits[0] != 1)) result.add(charsetDec[i - 1])
           // if (digits[1] == i) result.add()
        }
        if (digits[0] == 1) result.add(charsetExeptions[0])
        else if ((digits[1] > 1) && (digits[1] < 5)) result.add(charsetExeptions[2])
        result.add(charsetExeptions[1])
        triade = digits[3].toString() + digits[4].toString() + digits[5].toString()
        result.add(triadeDefine(triade.toInt(), false))
        return result.joinToString(separator = " ")
    }

    if ((length == 4) && (digits[0] == 1)) result.add(charsetExeptions[0])
    if (digits[0] == 2) result.add(charsetExeptions[4])
    for (i in 0 until 10) {
        if (digits[0] == 1) result.add(charsetExeptions[0])
        if (digits[0] == 2) result.add(charsetExeptions[4])
        if ((digits[0] == i) && (digits[0] != 1) && (digits[0] != 2)) result.add(charsetSimple[i])


    }
    if ((digits[0] > 1) && (digits[0] < 5)) result.add(charsetExeptions[2])
    result.add(charsetExeptions[1])
    triade = digits[1].toString() + digits[2].toString() + digits[3].toString()
    result.add(triadeDefine(triade.toInt(), false))
    return result.joinToString(separator = " ")


    if (length == 3) {
        triade = digits[0].toString() + digits[1].toString() + digits[2].toString()
        return result.add(triadeDefine(triade.toInt(), false)).toString()
    }

    if (length == 2) {
        for (i in 0 until 10) {
            if ((digits[0] == 1) && (digits[1] == i)) return charsetDecSimple[i - 1]
            for (i in 0 until 10) {
                if (digits[0] == i) result.add(charsetDec[i - 1])
                if ((digits[1] == i) && (digits[1] > 1)) result.add(charsetSimple[i])
                return result.joinToString(separator = " ")
            }
        }
    }
    return charsetSimple[digits[0]]
}

