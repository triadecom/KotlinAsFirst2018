@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson1.task1.quadraticRootProduct
import lesson2.task2.daysInMonth
import java.lang.IllegalArgumentException
import lesson4.task1.*
import java.time.Month
import java.time.Year
import java.util.*

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main(args: Array<String>) {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */

val monthDigit = listOf("января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября",
        "октября", "ноября", "декабря")

fun dateStrToDigit(str: String): String {
    val date = str.split(" ").toMutableList()
    // первым делом проверяем дату на ошибки или пустоту
    try {
        if ((date.size != 3) || (!monthDigit.contains(date[1].toLowerCase())) || (date[0].toInt() !in 1..31)) return ""
    } catch (e: NumberFormatException) {
        return ""
    }
    // проверяем дату на корректность по григорианскому календарю
    date[1] = (monthDigit.indexOf(date[1].toLowerCase()) + 1).toString()
    if (date[0].toInt() > daysInMonth(date[1].toInt(), date[2].toInt())) return ""
    // представляем день и месяц двумя числами через форматирование строки
    return String.format("%02d.%02d.%d", date[0].toInt(), date[1].toInt(), date[2].toInt())
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val date = digital.split(".").toMutableList()
    // первым делом проверяем дату на ошибки или пустоту
    try {
        if ((date.size != 3) || (date[0].toInt() !in 1..31) || (date[1].toInt() !in 1..12)) return ""
    } catch (e: NumberFormatException) {
        return ""
    }
    // проверяем дату на корректность по григорианскому календарю
    if (date[0].toInt() > daysInMonth(date[1].toInt(), date[2].toInt())) return ""
    date[0] = String.format("%01d", date[0].toInt())
    date[1] = monthDigit[date[1].toInt() - 1]

    return date.joinToString(separator = " ")
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -98 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку
 */

fun flattenPhoneNumber(phone: String): String =
        if (Regex("""((\+?|\d)\d+?\s*(\(\d+\))?((\s*-*)*\d+)+|\d+)""").matches(phone))
            phone.replace(Regex("""\s|-|\(|\)"""), "")
        else ""

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    if (!Regex("""[\d%-]+(\s[\d%-]+)*""").matches(jumps) ||
            !jumps.contains(Regex("""\d"""))) return -1
    val res = mutableListOf<Int>()
    jumps.split(" ").forEach { if (Regex("""\d+""").matches(it)) res.add(it.toInt()) }

    return res.max()!!
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    if (!Regex("""[\d%+\s\-]+""").matches(jumps)) return -1
    val str = jumps.split(Regex("""[\s%-]+"""))
    val result = mutableListOf<Int>()


    for (i in 0 until str.size) {
        if (str[i].contains("+")) result.add(str[i - 1].toInt())
    }
    return result.max() ?: -1
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    if (!Regex("""\d+(\s[+-]\s\d+)*""").matches(expression))
        throw IllegalArgumentException()
    val exp = expression.split(" ")
    var res = exp[0].toInt()

    for (i in 1 until exp.size step 2) {
        if (exp[i] == "+") res += exp[i + 1].toInt()
        if (exp[i] == "-") res -= exp[i + 1].toInt()
    }
    return res
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val strList = str.toLowerCase().split(" ")
    var index = 0

    for (i in 0 until strList.size - 1)
        if (strList[i].toLowerCase() == strList[i + 1].toLowerCase()) return index
        else index += strList[i].length + 1
    return -1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String {
    val costMap = mutableMapOf<Double, String>()
    val str = description.split("; ")
    var res = ""

    for (item in str) if (item.split(" ").size != 2) return ""

    for (item in str) {
        if (item.split(" ")[1].toDouble() < 0.0) return ""
        costMap[item.split(" ")[1].toDouble()] = item.split(" ")[0]
        if (item.split(" ")[1].toDouble() == costMap.keys.max())
            res = costMap.getValue(item.split(" ")[1].toDouble())
    }
    return res
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */

fun fromRoman(roman: String): Int {
    if ((!Regex("""^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})${'$'}""").matches(roman))
            || roman.isEmpty()) return -1  // Проверка строки на ошибки вначале функции делает её выполнение быстрее
    val str = mutableListOf<Int>()
    val romanCharMap = mapOf(
            'I' to 1,
            'V' to 5,
            'X' to 10,
            'L' to 50,
            'C' to 100,
            'D' to 500,
            'M' to 1000
    )
    roman.toList().forEach { str.add(romanCharMap.getValue(it)) }  // Перевод с Римских чисел в Арабские
    var result = str[0]
    for (i in 1 until str.size) {
        if (str[i] <= str[i - 1]) result += str[i]
        else if (str[i] > str[i - 1]) result += (str[i] - (str[i - 1] * 2))
    }
    return result
}

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {
    if (!Regex("""[+<>\[\]\s-]+""").matches(commands)) throw IllegalArgumentException()
    // проверяем, все ли скобки закрыты
    if (commands.contains(Regex("""[\[\]]+"""))) {
        var ct = 0
        commands.toMutableList().forEach {
            if (it == '[') ct++
            if (it == ']') ct--
        }
        if (ct != 0) throw IllegalArgumentException()
    }

    var pos = cells / 2
    val res = mutableListOf<Int>()
    repeat(cells) { res.add(0) }
    var count = 0
    var cmdCount = 0

    while (count < commands.length && cmdCount < limit) {
        val actualCmd = commands[count]
        when (actualCmd) {
            ' ' -> { }
            '<' -> pos--
            '>' -> pos++
            '-' -> res[pos]--
            '+' -> res[pos]++
            '[' -> if (res[pos] == 0) {
                var pass = 1
                while (pass > 0) {
                    count++
                    if (commands[count] == '[') pass++
                    else if (commands[count] == ']') pass--
                }
            }
            ']' -> if (res[pos] != 0) {
                var pass = 1
                while (pass > 0) {
                    count--
                    if (commands[count] == ']') pass++
                    else if (commands[count] == '[') pass--
                }
            }
            else -> throw IllegalArgumentException()
        }
        count++
        cmdCount++
        // Проверяем, не ушла ли комманда за границы конвеера
        if (pos !in 0 until cells) throw IllegalStateException()
    }
    return res
}

