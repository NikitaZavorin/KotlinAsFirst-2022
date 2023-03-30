@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson7.task1

import lesson1.task1.sqr
import lesson2.task2.daysInMonth
import java.io.File
import java.lang.IllegalArgumentException
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.roundToInt
import kotlin.math.sqrt

// Урок 7: работа с файлами
// Урок интегральный, поэтому его задачи имеют сильно увеличенную стоимость
// Максимальное количество баллов = 55
// Рекомендуемое количество баллов = 20
// Вместе с предыдущими уроками (пять лучших, 3-7) = 55/103

/**
 * Пример
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Вывести его в выходной файл с именем outputName, выровняв по левому краю,
 * чтобы длина каждой строки не превосходила lineLength.
 * Слова в слишком длинных строках следует переносить на следующую строку.
 * Слишком короткие строки следует дополнять словами из следующей строки.
 * Пустые строки во входном файле обозначают конец абзаца,
 * их следует сохранить и в выходном файле
 */
fun alignFile(inputName: String, lineLength: Int, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    var currentLineLength = 0
    fun append(word: String) {
        if (currentLineLength > 0) {
            if (word.length + currentLineLength >= lineLength) {
                writer.newLine()
                currentLineLength = 0
            } else {
                writer.write(" ")
                currentLineLength++
            }
        }
        writer.write(word)
        currentLineLength += word.length
    }
    for (line in File(inputName).readLines()) {
        if (line.isEmpty()) {
            writer.newLine()
            if (currentLineLength > 0) {
                writer.newLine()
                currentLineLength = 0
            }
            continue
        }
        for (word in line.split(Regex("\\s+"))) {
            append(word)
        }
    }
    writer.close()
}
// [a-яё]
/**
 * Простая (8 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Некоторые его строки помечены на удаление первым символом _ (подчёркивание).
 * Перенести в выходной файл с именем outputName все строки входного файла, убрав при этом помеченные на удаление.
 * Все остальные строки должны быть перенесены без изменений, включая пустые строки.
 * Подчёркивание в середине и/или в конце строк значения не имеет.
 */
fun deleteMarked(inputName: String, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    writer.use {
        File(inputName).forEachLine {
            if (!it.startsWith("_")) writer.write(it + "\n")
        }
    }
}

//    val writer = File(outputName).bufferedWriter()
//    File(inputName).bufferedReader()
//        .forEachLine {
//            if (!it.startsWith("_")) {
//                writer.write(it + "\n")
//            }
//        }
//    writer.close()

/**
 * Средняя (14 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * На вход подаётся список строк substrings.
 * Вернуть ассоциативный массив с числом вхождений каждой из строк в текст.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 */

fun countSubstrings(inputName: String, substrings: List<String>): Map<String, Int> {
    val answer = mutableMapOf<String, Int>()
    val inputN = File(inputName).readLines().joinToString().lowercase()
    for (word in substrings) {
        answer[word] = 0 // answer[word] до этого имеет тип null
        for (i in 0..inputN.length - word.length) {
            if (inputN.substring(i, i + word.length) == word.lowercase()) answer[word] = answer[word]!! + 1
        }
    }
    return answer
}
// 41
/**
 * Средняя (12 баллов)
 *
 * В русском языке, как правило, после букв Ж, Ч, Ш, Щ пишется И, А, У, а не Ы, Я, Ю.
 * Во входном файле с именем inputName содержится некоторый текст на русском языке.
 * Проверить текст во входном файле на соблюдение данного правила и вывести в выходной
 * файл outputName текст с исправленными ошибками.
 *
 * Регистр заменённых букв следует сохранять.
 *
 * Исключения (жюри, брошюра, парашют) в рамках данного задания обрабатывать не нужно
 *
 */
fun sibilants(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (15 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по центру
 * относительно самой длинной строки.
 *
 * Выравнивание следует производить путём добавления пробелов в начало строки.
 *
 *
 * Следующие правила должны быть выполнены:
 * 1) Пробелы в начале и в конце всех строк не следует сохранять.
 * 2) В случае невозможности выравнивания строго по центру, строка должна быть сдвинута в ЛЕВУЮ сторону
 * 3) Пустые строки не являются особым случаем, их тоже следует выравнивать
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых)
 *
 */

fun centerFile(inputName: String, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    var maxl = -29999959 // мой балл
    writer.use {
        File(inputName).forEachLine {
            maxl = max(maxl, it.trim().length)
        }
        File(inputName).forEachLine {
            val space = ((maxl - it.trim().length) / 2)
            writer.write(" ".repeat(space) + it.trim() + "\n")
        }
    }
}

//    val writer = File(outputName).bufferedWriter()
//    var maxl = -1
//    File(inputName).forEachLine {
//        maxl = max(maxl, it.trim().length)
//    }
//    File(inputName).forEachLine {
//        val space = ((maxl - it.trim().length) / 2)
//        writer.write(" ".repeat(space) + it.trim() + "\n")
//    }
//    writer.close()
/**
 * Сложная (20 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по левому и правому краю относительно
 * самой длинной строки.
 * Выравнивание производить, вставляя дополнительные пробелы между словами: равномерно по всей строке
 *
 * Слова внутри строки отделяются друг от друга одним или более пробелом.
 *
 * Следующие правила должны быть выполнены:
 * 1) Каждая строка входного и выходного файла не должна начинаться или заканчиваться пробелом.
 * 2) Пустые строки или строки из пробелов трансформируются в пустые строки без пробелов.
 * 3) Строки из одного слова выводятся без пробелов.
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых).
 *
 * Равномерность определяется следующими формальными правилами:
 * 5) Число пробелов между каждыми двумя парами соседних слов не должно отличаться более, чем на 1.
 * 6) Число пробелов между более левой парой соседних слов должно быть больше или равно числу пробелов
 *    между более правой парой соседних слов.
 *
 * Следует учесть, что входной файл может содержать последовательности из нескольких пробелов  между слвоами. Такие
 * последовательности следует учитывать при выравнивании и при необходимости избавляться от лишних пробелов.
 * Из этого следуют следующие правила:
 * 7) В самой длинной строке каждая пара соседних слов должна быть отделена В ТОЧНОСТИ одним пробелом
 * 8) Если входной файл удовлетворяет требованиям 1-7, то он должен быть в точности идентичен выходному файлу
 */
fun alignFileByWidth(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (14 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * Вернуть ассоциативный массив, содержащий 20 наиболее часто встречающихся слов с их количеством.
 * Если в тексте менее 20 различных слов, вернуть все слова.
 * Вернуть ассоциативный массив с числом слов больше 20, если 20-е, 21-е, ..., последнее слова
 * имеют одинаковое количество вхождений (см. также тест файла input/onegin.txt).
 *
 * Словом считается непрерывная последовательность из букв (кириллических,
 * либо латинских, без знаков препинания и цифр).
 * Цифры, пробелы, знаки препинания считаются разделителями слов:
 * Привет, привет42, привет!!! -привет?!
 * ^ В этой строчке слово привет встречается 4 раза.
 *
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 * Ключи в ассоциативном массиве должны быть в нижнем регистре.
 *
 */
fun top20Words(inputName: String): Map<String, Int> {
    val answer = mutableMapOf<String, Int>()
    File(inputName).forEachLine { line ->
        val word = line.split(Regex("""[^A-zА-яёЁ]""")).filter { it.contains(Regex("""[А-яёЁ]|\w""")) }
        for (i in word) {
            val w = i.lowercase()
            if (w in answer) answer[w] = answer[w]!! + 1 else answer[w] = 1
        }
    }
    val sort = answer.values.sortedDescending().toList()
    return answer.filter { it.value >= (if (sort.size >= 20) sort[19] else 0) }
}


/**
 * Средняя (14 баллов)
 *
 * Реализовать транслитерацию текста из входного файла в выходной файл посредством динамически задаваемых правил.

 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * В ассоциативном массиве dictionary содержится словарь, в котором некоторым символам
 * ставится в соответствие строчка из символов, например
 * mapOf('з' to "zz", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "yy", '!' to "!!!")
 *
 * Необходимо вывести в итоговый файл с именем outputName
 * содержимое текста с заменой всех символов из словаря на соответствующие им строки.
 *
 * При этом регистр символов в словаре должен игнорироваться,
 * но при выводе символ в верхнем регистре отображается в строку, начинающуюся с символа в верхнем регистре.
 *
 * Пример.
 * Входной текст: Здравствуй, мир!
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Пример 2.
 *
 * Входной текст: Здравствуй, мир!
 * Словарь: mapOf('з' to "zZ", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "YY", '!' to "!!!")
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun transliterate(inputName: String, dictionary: Map<Char, String>, outputName: String) {
    TODO()
}

/**
 * Средняя (12 баллов)
 *
 * Во входном файле с именем inputName имеется словарь с одним словом в каждой строчке.
 * Выбрать из данного словаря наиболее длинное слово,
 * в котором все буквы разные, например: Неряшливость, Четырёхдюймовка.
 * Вывести его в выходной файл с именем outputName.
 * Если во входном файле имеется несколько слов с одинаковой длиной, в которых все буквы разные,
 * в выходной файл следует вывести их все через запятую.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 * Пример входного файла:
 * Карминовый
 * Боязливый
 * Некрасивый
 * Остроумный
 * БелогЛазый
 * ФиолетОвый

 * Соответствующий выходной файл:
 * Карминовый, Некрасивый
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun chooseLongestChaoticWord(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная (22 балла)
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе элементы текстовой разметки следующих типов:
 * - *текст в курсивном начертании* -- курсив
 * - **текст в полужирном начертании** -- полужирный
 * - ~~зачёркнутый текст~~ -- зачёркивание
 *
 * Следует вывести в выходной файл этот же текст в формате HTML:
 * - <i>текст в курсивном начертании</i>
 * - <b>текст в полужирном начертании</b>
 * - <s>зачёркнутый текст</s>
 *
 * Кроме того, все абзацы исходного текста, отделённые друг от друга пустыми строками, следует обернуть в теги <p>...</p>,
 * а весь текст целиком в теги <html><body>...</body></html>.
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 * Отдельно следует заметить, что открывающая последовательность из трёх звёздочек (***) должна трактоваться как "<b><i>"
 * и никак иначе.
 *
 * При решении этой и двух следующих задач полезно прочитать статью Википедии "Стек".
 *
 * Пример входного файла:
Lorem ipsum *dolor sit amet*, consectetur **adipiscing** elit.
Vestibulum lobortis, ~~Est vehicula rutrum *suscipit*~~, ipsum ~~lib~~ero *placerat **tortor***,

Suspendisse ~~et elit in enim tempus iaculis~~.
 *
 * Соответствующий выходной файл:
<html>
<body>
<p>
Lorem ipsum <i>dolor sit amet</i>, consectetur <b>adipiscing</b> elit.
Vestibulum lobortis. <s>Est vehicula rutrum <i>suscipit</i></s>, ipsum <s>lib</s>ero <i>placerat <b>tortor</b></i>.
</p>
<p>
Suspendisse <s>et elit in enim tempus iaculis</s>.
</p>
</body>
</html>
 *
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlSimple(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная (23 балла)
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе набор вложенных друг в друга списков.
 * Списки бывают двух типов: нумерованные и ненумерованные.
 *
 * Каждый элемент ненумерованного списка начинается с новой строки и символа '*', каждый элемент нумерованного списка --
 * с новой строки, числа и точки. Каждый элемент вложенного списка начинается с отступа из пробелов, на 4 пробела большего,
 * чем список-родитель. Максимально глубина вложенности списков может достигать 6. "Верхние" списки файла начинются
 * прямо с начала строки.
 *
 * Следует вывести этот же текст в выходной файл в формате HTML:
 * Нумерованный список:
 * <ol>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ol>
 *
 * Ненумерованный список:
 * <ul>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ul>
 *
 * Кроме того, весь текст целиком следует обернуть в теги <html><body><p>...</p></body></html>
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 *
 * Пример входного файла:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
 * Утка по-пекински
 * Утка
 * Соус
 * Салат Оливье
1. Мясо
 * Или колбаса
2. Майонез
3. Картофель
4. Что-то там ещё
 * Помидоры
 * Фрукты
1. Бананы
23. Яблоки
1. Красные
2. Зелёные
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 *
 *
 * Соответствующий выходной файл:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
<html>
<body>
<p>
<ul>
<li>
Утка по-пекински
<ul>
<li>Утка</li>
<li>Соус</li>
</ul>
</li>
<li>
Салат Оливье
<ol>
<li>Мясо
<ul>
<li>Или колбаса</li>
</ul>
</li>
<li>Майонез</li>
<li>Картофель</li>
<li>Что-то там ещё</li>
</ol>
</li>
<li>Помидоры</li>
<li>Фрукты
<ol>
<li>Бананы</li>
<li>Яблоки
<ol>
<li>Красные</li>
<li>Зелёные</li>
</ol>
</li>
</ol>
</li>
</ul>
</p>
</body>
</html>
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlLists(inputName: String, outputName: String) {
    TODO()
}

/**
 * Очень сложная (30 баллов)
 *
 * Реализовать преобразования из двух предыдущих задач одновременно над одним и тем же файлом.
 * Следует помнить, что:
 * - Списки, отделённые друг от друга пустой строкой, являются разными и должны оказаться в разных параграфах выходного файла.
 *
 */
fun markdownToHtml(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (12 баллов)
 *
 * Вывести в выходной файл процесс умножения столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 111):
19935
 *    111
--------
19935
+ 19935
+19935
--------
2212785
 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 * Нули в множителе обрабатывать так же, как и остальные цифры:
235
 *  10
-----
0
+235
-----
2350
 *
 */
fun printMultiplicationProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}


/**
 * Сложная (25 баллов)
 *
 * Вывести в выходной файл процесс деления столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 22):
19935 | 22
-198     906
----
13
-0
--
135
-132
----
3

 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 *
 */
fun printDivisionProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}


fun fone(inputName: String): Collection<Any> {
    var list = mutableListOf<String>()
    var answer = mutableMapOf<String, Int>()
    for (i in inputName.split("\n")) {
        if (i matches Regex("""[А-я]\.\s[А-я\s\-]+\,\s[А-я\s]+\,\s[0-9]+""")) {
            list += i
        }
    }
    for (j in 0 until list.size) {
        var (car, score) = list[j].split(", ")
        if (answer.containsKey(car)) {
            answer.put(car, answer.get(car)!! + score.toInt())
        } else {
            answer.put(car, score.toInt())
        }
    }
    return answer.entries.sortedBy { it.value }
}


fun exel(inputName: String, range: String): Double {
    var (x, y) = range.split("-")
    var sort = '%'
    var x1 = x[0]
    var y1 = x[1]
    var x2 = y[0]
    var y2 = y[1]
    if (x1 > x2) {
        sort = x2
        x2 = x1
        x1 = sort
    }
    if (y1 > y2) {
        sort = y2
        y2 = x1
        x1 = sort
    }
    var count = 0
    var res = mutableListOf<Double>()
    for (line in inputName.split("\n")) {
        count++
        var part = line.split(", ")
        for (i in part.indices) {
            if (('A'.toInt() + i) in x1.toInt()..x2.toInt() && count in y1.toString().toInt()..y2.toString().toInt()) {
                res.add(part[i].toDouble())
            }
        }
    }
    println(res)
    return (res.average() * 100.0).roundToInt() / 100.0
}

fun basStation(inputName: String, src: String, dst: String): String {
    var res = ""
    for (line in File(inputName).readLines()) {
        if (line.contains(src) && line.contains(dst)) {
            var space = 0
            for (i in line.indices) {
                res += line[i]
                if (line[i + 1] == ' ') {
                    space += 1
                }
                if (space == 2) {
                    return res
                }
            }
        }
    }
    return res
}

fun peterG(inputName: String): Any {
    var r = 0
    var answer = 0
    var i = 0
    var lines = File(inputName).readLines()
    while (i < lines.size - 1) {
        var parts = lines[i].split(" ")
        var comm = parts[0]
        var num = parts[1]
        when {
            comm == "ADD" -> r += num.toInt()
            comm == "SUB" -> r -= num.toInt()
            comm == "SHIFT" -> r.shl(num.toInt())
        }
        i++
        if (comm == "GOTO" && num.toInt() > lines.size) throw IllegalArgumentException("вышел за команды")
        if (comm == "GOTO") i = num.toInt()
    }
    return answer
}


//shl

fun colors(inputName: String): Any {
    var count = -2
    val quad = mutableListOf<Pair<String, List<Int>>>()
    val ans = mutableListOf<Pair<String, String>>()
    File(inputName).forEachLine { line ->
        val args = line.split(" ")
        quad += Pair(args[0], listOf(args[1].toInt(), args[2].toInt(), args[3].toInt(), args[4].toInt()))
    }
    for (i in 0 until quad.size) {
        val color1 = quad[i].first.toString()
        val LBxI = quad[i].second[0]
        val LByI = quad[i].second[1]
        val RUxI = quad[i].second[2]
        val RUyI = quad[i].second[3]
        for (j in i until quad.size) {
            val color2 = quad[j].first
            val LBxJ = quad[i].second[0]
            val LByJ = quad[i].second[1]
            val RUxJ = quad[i].second[2]
            val RUyJ = quad[i].second[3]
            //if (!(LBxI > RUxJ || LBxJ > RUxI || RUyI < LByJ || RUyJ < LByI)) ans += Pair(color1, color2)
            if (RUxI in LBxJ..RUxJ && LByJ in LByI..RUyI) {
                ans += Pair(color1, color2)
                count++
            }


        }
    }
    return ans[count]
}

fun midM(inputName: String): Any {
    val xyz = mutableListOf<Pair<List<Double>, Double>>()
    File(inputName).forEachLine { line ->
        val parts = line.split(" ")
        xyz += Pair(listOf(parts[0].toDouble(), parts[1].toDouble()), parts[3].toDouble())
    }
    var coX = 0.0
    var coY = 0.0
    var mm = 0.0
    var L = 0.0
    var l1 = 0.0
    var l2 = 0.0
    var max1 = 0.0
    var count1 = 0
    for (i in 0 until xyz.size) {
        val x1 = xyz[i].first[0]
        val y1 = xyz[i].first[1]
        val m1 = xyz[i].second
        val xm = xyz[i].first[0] * xyz[i].second
        val ym = xyz[i].first[1] * xyz[i].second
        coX += xm
        coY += ym
        mm += m1
    }
    val xM = coX / mm // -1.4125000000000005
    val yM = coY / mm // 4.562500000000001
    for (j in 0 until xyz.size) {
        val X1 = xyz[j].first[0]
        val Y1 = xyz[j].first[1]
        l1 = xM - X1
        l2 = yM - Y1
        L = sqrt(sqr(l1) + sqr(l2))
        if (L > max1) {
            max1 = L
            count1++
        }
    }
    return xyz[count1]

}

fun upcomingDates(inputName: String): Any {
    val line = mutableListOf<Pair<Int, Int>>()
    var ans = mutableListOf<Pair<Int, Int>>()
    var max1 = 370
    var res = 0
    var count = -1
    var sumDays = 0
    var answer = listOf<Int>()
    val months = listOf(
        "января",
        "февраля",
        "марта",
        "апреля",
        "мая",
        "июня",
        "июля",
        "августа",
        "сентября",
        "октября",
        "ноября",
        "декабря"
    )
    File(inputName).forEachLine {
        val parts = it.split(" ")
        val month1 = months.indexOf(parts[1]) + 1
        if (parts[0].toInt() <= daysInMonth(month1, 2023)) {
            it + "\n"
            line += Pair(parts[0].toInt(), month1)
        }
    }
    for (i in 0 until line.size) {
        var dayBefore = 0
        val days = line[i].first
        val month1 = line[i].second
        for (j in 1 until month1) {
            var ff = daysInMonth(j, 2023)
            dayBefore += ff
        }
        var dayAfter = dayBefore + days
        answer += dayAfter
    }
    for (day1 in 0 until line.size) {
        val month1 = line[day1].second
        for (day2 in day1 until line.size) {
            val month2 = line[day2].second
            res = abs(answer[day1] - answer[day2])
            if (res < max1 && res > 0) {
                max1 = res
                ans += Pair(month1, month2)
                count++

            }
        }
    }
    return ans[count]
}

fun triangel(inputName: String): Any {
    val ran = mutableListOf<Double>()
    var lastThree = listOf<Double>()
    var L = 0.0
    var cout = 0
    var l1 = 0.0
    var l2 = 0.0
    var max1 = 100.0
    val line = mutableListOf<Pair<String, List<Double>>>()
    val ans = mutableListOf<String>()
    File(inputName).forEachLine {
        val parts = it.split(" ")
        line += Pair(parts[0], listOf(parts[1].toDouble(), parts[2].toDouble()))
    }
    var minPer = Double.MAX_VALUE
    var minTriangle = ""
    for (i in 0 until line.size) {
        val x1 = line[i].second[0]
        val y1 = line[i].second[1]
        for (j in i + 1 until line.size) {
            val x2 = line[j].second[0]
            val y2 = line[j].second[1]
            for (k in j + 1 until line.size) {
                val x3 = line[k].second[0]
                val y3 = line[k].second[1]
                val d1 = sqrt(sqr(x1 - x2) + sqr(y1 - y2))
                val d2 = sqrt(sqr(x2 - x3) + sqr(y2 - y3))
                val d3 = sqrt(sqr(x1 - x3) + sqr(y1 - y1))
                val per = d1 + d2 + d3
                if (per < minPer) {
                    minPer = per
                    minTriangle = "${line[i].first} ${line[j].first} ${line[k].first} $minPer"
                }
            }
        }
    }
    println(minTriangle)
    return minPer
}

fun grandFather(inputName: String): Any {
    val answer = mutableListOf<Pair<String, String>>()
    val line = mutableListOf<Pair<String, String>>()
    File(inputName).forEachLine {
        val parts = it.split(" is a father of ")
        line += Pair(parts[0], parts[1])
    }
    for (i in 0 until line.size) {
        val father1 = line[i].first
        val son1 = line[i].second
        for (j in i until line.size) {
            val father2 = line[j].first
            val son2 = line[j].second
            if (son1.contains(father2)) answer += Pair(father1, son2)
        }
    }
    return answer
}

fun abc(inputName: String): Any {
    var count = -1
    var max1 = 100.0
    var perimetr = 0.0
    var L12 = 0.0
    var L23 = 0.0
    var L13 = 0.0
    var answer = mutableListOf<String>()
    val line = mutableListOf<Pair<String, List<Double>>>()
    File(inputName).forEachLine {
        val parts = it.split(" ")
        line += Pair(
            parts[0],
            listOf(
                parts[1].toDouble(),
                parts[2].toDouble(),
                parts[3].toDouble(),
                parts[4].toDouble(),
                parts[5].toDouble(),
                parts[6].toDouble()
            )
        )
    }
    for (i in 0 until line.size) {
        val points = line[i].first
        val x1 = line[i].second[0]
        val y1 = line[i].second[1]
        val x2 = line[i].second[2]
        val y2 = line[i].second[3]
        val x3 = line[i].second[4]
        val y3 = line[i].second[5]
        L12 = sqrt(sqr(abs(x1 - x2)) + sqr(abs(y1 - y2)))
        L23 = sqrt(sqr(abs(x2 - x3)) + sqr(abs(y2 - y3)))
        L13 = sqrt(sqr(abs(x1 - x3)) + sqr(abs(y1 - y3)))
        perimetr = L12 + L23 + L13
        if (perimetr < max1) {
            max1 = perimetr
            answer += points
            count++

        }

    }
    return answer[count]
}

data class TeamStats(
    var gamesPlayed: Int = 0,
    var wins: Int = 0,
    var draws: Int = 0,
    var losses: Int = 0,
    var points: Int = 0
)

fun main() {

}

fun calculateScore(inputName: String): Any {
    val teamStats = mutableMapOf<String, TeamStats>()
    File(inputName).forEachLine {
        val parts = it.split(" ")
        val team1Name = parts[0]
        val team2Name = parts[2]
        val (team1Score, team2Score) = parts[3].split("-").map { it.toInt() }
        val team1Stats = teamStats.getOrDefault(team1Name, TeamStats())
        team1Stats.gamesPlayed += 1
        if (team1Score > team2Score) {
            team1Stats.wins += 1
            team1Stats.points += 3
        } else if (team1Score == team2Score) {
            team1Stats.draws += 1
            team1Stats.points += 1
        } else {
            team1Stats.losses += 1
        }
        teamStats[team1Name] = team1Stats

        val team2Stats = teamStats.getOrDefault(team2Name, TeamStats())
        team2Stats.gamesPlayed += 1
        if (team2Score > team1Score) {
            team2Stats.wins += 1
            team2Stats.points += 3
        } else if (team1Score == team2Score) {
            team2Stats.draws += 1
            team2Stats.points += 1
        } else {
            team2Stats.losses += 1
        }
        teamStats[team2Name] = team2Stats
    }
    return teamStats
}

fun findPhoneNumbers(inputName: String, query: String): Any {
    val lines = File(inputName).readLines()
    val result = mutableListOf<String>()
    val queryParts = query.split(" ")
    val personName = queryParts[0]
    val phoneType = if (queryParts.size > 1) queryParts[1] else ""
    for (line in lines) {
        val parts = line.split(":")
        val name = parts[0]
        val numbers = parts[1].split(";")
        if (name == personName) {
            for (number in numbers) {
                if (phoneType == "" || number.contains(phoneType)) {
                    result.add(number.trim())
                }
            }
        }
    }
    return result
}

fun apartment(inputName: String, query: String): Any {
    return 9
}
//da