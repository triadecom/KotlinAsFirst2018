@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence", "UNREACHABLE_CODE")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.sqrt
import java.lang.Math.pow
import lesson3.task1.digitNumber

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
    v.forEach {
        mod += pow(it, 2.0)
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
    val result = mutableListOf<Int>()
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
    return result
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
    val result = mutableListOf<Int>()
    var a = n

    do {
        result.add(a % base)
        a /= base
    } while (a != 0)

    if (result.toString() == "") result.add(0)

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
    val result = mutableListOf<String>()
    val converted = convert(n, base)
    var rem: Int
    for (num in converted) {
        rem = num % base
        if (num < 10) result.add(num.toString())
        else result.add(('a' + (rem - 10)).toString())
    }
    return result.joinToString(separator = "")
}


/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var result = 0
    for (i in digits) {
        result = i + result * base
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

fun decimalFromString(str: String, base: Int): Int =
        decimal(str.map {
            if (it.isDigit()) (it - '0')
            else (it - 'a' + 10)
        }, base)

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 =
 */
fun roman(n: Int): String {
    val digits = listOf(
            1,                // digits[0]
            4,                // digits[1]
            5,                // digits[2]
            9,                // digits[3]
            10,               // digits[4]
            40,               // digits[5]
            50,               // digits[6]
            90,               // digits[7]
            100,              // digits[8]
            400,              // digits[9]
            500,              // digits[10]
            900,              // digits[11]
            1000              // digits[12]
    )

    val result = mutableListOf<String>()
    var a = n
    while (a / 1000 > 0) {
        a -= 1000
        result.add(charsetRoman[12])
    }
    while (a > 0)
        for (i in 0 until digits.size)
            if (a < digits[i]) {
                a -= digits[i - 1]
                result.add(charsetRoman[i - 1])
                break
            }
    return result.joinToString(separator = "")
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */

val charsetRoman = listOf(
        "I",              // charsetRoman[0]
        "IV",             // charsetRoman[1]
        "V",              // charsetRoman[2]
        "IX",             // charsetRoman[3]
        "X",              // charsetRoman[4]
        "XL",             // charsetRoman[5]
        "L",              // charsetRoman[6]
        "XC",             // charsetRoman[7]
        "C",              // charsetRoman[8]
        "CD",             // charsetRoman[9]
        "D",              // charsetRoman[10]
        "CM",             // charsetRoman[11]
        "M"               // charsetRoman[12]
)

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
        "одиннадцать",           // charsetDecSimple[0]
        "двенадцать",           // charsetDecSimple[1]
        "тринадцать",           // charsetDecSimple[2]
        "четырнадцать",         // charsetDecSimple[3]
        "пятнадцать",           // charsetDecSimple[4]
        "шестнадцать",          // charsetDecSimple[5]
        "семнадцать",           // charsetDecSimple[6]
        "восемнадцать",         // charsetDecSimple[7]
        "девятнадцать")         // charsetDecSimple[8]

val charsetExceptions = listOf(
        "тысяча",               // charsetExceptions[0]
        "тысяч",                // charsetExceptions[1]
        "тысячи",               // charsetExceptions[2]
        "одна",                 // charsetExceptions[3]
        "две")                  // charsetExceptions[4]

/*
Простые числа - список charsetSimple[0..9]
десятки, начиная от Десяти - список charsetDec[0..8]
сотни, начиная ста - список charsetHundreds[0..8]
Исключения - список charsetExceptions[0..4]
*/


// Функция, разбивающая целое число на её составляющие, занося в список

fun numberDefine(n: Int): List<Int> {
    val digit = mutableListOf<Int>()
    var a = 10
    var b = 1
    var c = 1

    if (pow(10.0, (digitNumber(n) - 1).toDouble()).toInt() == n) {
        digit.add(1)
        do {
            c *= 10
            digit.add(0)
        } while (c != n)
        return digit
    } else for (i in 0 until Math.ceil(Math.log10(n.toDouble())).toInt()) {
        digit.add(n % a / b)
        a *= 10
        b *= 10
    }
    return digit.reversed()
}


// Функция, записывающая трехзначные числа прописью по-русски.

fun triadeDefine(n: Int, pos: Boolean): String {
    val result = mutableListOf<String>()
    var triade = listOf<Int>()

    // преборазуем число n в необходимую нам триаду
    // pos - указывает на часть триады, где значения:
    // true - нужная триада является тысячей
    // false - нужная триада не является тысячей

    val digit = if (pos) n / 1000 else n % 1000

    when {
        digit > 99 -> triade = numberDefine(digit)
        digit > 9 -> triade = listOf(0) + numberDefine(digit)
        digit > 0 -> triade = listOf(0, 0) + digit
        digit == 0 -> return ""
    }

    for (i in 1 until 10) if (triade[0] == i) result.add(charsetHundreds[i - 1])

    if (triade[1] == 1) {
        if (triade[2] == 0) result.add(charsetDec[0])
        for (i in 1 until 10) if ((triade[2] == i)) result.add(charsetDecSimple[i - 1])
    }

    for (i in 2 until 10) if (triade[1] == i) result.add(charsetDec[i - 1])

    if (pos) {
        if ((triade[2] == 1) && (triade[1] != 1)) result.add(charsetExceptions[3])
        if ((triade[2] == 2) && (triade[1] != 1)) result.add(charsetExceptions[4])
        for (i in 3 until 10) if ((triade[2] == i) && (triade[1] != 1)) result.add(charsetSimple[i])
    } else for (i in 1 until 10) if ((triade[2] == i) && (triade[1] != 1)) result.add(charsetSimple[i])

    return result.joinToString(separator = " ")
}


fun thousandCheck(n: Int): String {
    val digit = numberDefine(n)
    var num = 0
    when {
        digit.size == 6 -> num = 2
        digit.size == 5 -> num = 1
        digit.size == 4 -> num = 0
    }

    if ((num == 1) && (digit[0] == 1)) return charsetExceptions[1]
    if ((num == 2) && (digit[1] == 1)) return charsetExceptions[1]

    return when {
        digit[num] == 0 -> charsetExceptions[1]
        digit[num] == 1 -> charsetExceptions[0]
        ((digit[num] > 1) && (digit[num] < 5)) -> charsetExceptions[2]
        else -> charsetExceptions[1]
    }
}

fun russian(n: Int): String {
    val result = mutableListOf<String>()

    if (n != 0) {
        result.add(triadeDefine(n, true))
        if (result.first() != "") result.add(thousandCheck(n))
        else result.removeAt(0)
        result.add(triadeDefine(n, false))
        if (result.last() == "") result.removeAt(2)

        return result.joinToString(separator = " ")
    }
    return charsetSimple[0]
}


