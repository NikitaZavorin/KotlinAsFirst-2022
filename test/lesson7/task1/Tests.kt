package lesson7.task1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.io.File
import java.time.LocalTime

class Tests {

    private fun assertFileContent(name: String, expectedContent: String) {
        val file = File(name)
        val content = file.readLines().joinToString("\n")
        assertEquals(expectedContent, content)
    }

    @Test
    @Tag("Example")
    fun alignFile() {
        alignFile("input/align_in1.txt", 50, "temp.txt")
        assertFileContent(
            "temp.txt", """Для написания разных видов программ сейчас
применяются разные языки программирования.
Например, в сфере мобильных программ сейчас правят
бал языки Swift (мобильные устройства под
управлением iOS) и Java (устройства под
управлением Android). Системные программы, как
правило, пишутся на языках C или {cpp}. Эти же
языки долгое время использовались и для создания
встраиваемых программ, но в последние годы в этой
области набирает популярность язык Java. Для
написания web-клиентов часто используется
JavaScript, а в простых случаях -- язык разметки
страниц HTML. Web-серверы используют опять-таки
Java (в сложных случаях), а также Python и PHP (в
более простых). Наконец, простые desktop-программы
сейчас могут быть написаны на самых разных языках,
и выбор во многом зависит от сложности программы,
области её использования, предполагаемой
операционной системы. В первую очередь следует
назвать языки Java, {cpp}, C#, Python, Visual
Basic, Ruby, Swift.

Самым универсальным и одновременно самым
распространённым языком программирования на данный
момент следует считать язык Java. Java в широком
смысле -- не только язык, но и платформа для
выполнения программ под самыми разными
операционными системами и на разной аппаратуре.
Такая универсальность обеспечивается наличием
виртуальной машины Java -- системной программы,
интерпретирующей Java байт-код в машинные коды
конкретного компьютера или системы. Java также
включает богатейший набор библиотек для
разработки."""
        )
        File("temp.txt").delete()
    }

    @Test
    @Tag("8")
    fun deleteMarked() {
        deleteMarked("input/delete_in1.txt", "temp.txt")
        assertFileContent(
            "temp.txt", """Задачи _надо_ решать правильно,

и не надо при этом никуда торопиться___
            """.trimIndent()
        )
        File("temp.txt").delete()
    }

    @Test
    @Tag("14")
    fun countSubstrings() {
        assertEquals(
            mapOf("РАЗНЫЕ" to 2, "ные" to 2, "Неряшливость" to 1, "е" to 49, "эволюция" to 0),
            countSubstrings("input/substrings_in1.txt", listOf("РАЗНЫЕ", "ные", "Неряшливость", "е", "эволюция"))
        )
        assertEquals(
            mapOf("Карминовый" to 2, "Некрасивый" to 2, "белоглазый" to 1),
            countSubstrings("input/substrings_in1.txt", listOf("Карминовый", "Некрасивый", "белоглазый"))
        )
        assertEquals(
            mapOf("--" to 4, "ее" to 2, "животное" to 2, "." to 2),
            countSubstrings("input/substrings_in2.txt", listOf("--", "ее", "животное", "."))
        )
    }

    @Test
    fun fone() {
        assertEquals(
            2.2, fone(
                "input/f1_test.txt"
            )
        )
    }

    @Test
    fun exel() {
        assertEquals(
            2.73,
            exel(
                """1.5, 2.67, 3.0, 1.4, 5.0
|5.2, 7.1, -4.8, 0.0
|1.4, 6.0, 2.5, -1.9""".trimMargin(),
                "C1-A3"
            )
        )
    }

    @Test
    fun basStation() {
        assertEquals(
            "трамвай 3",
            basStation("input/bas.txt", "Парк отдыха", "ул. Дворцовая")
        )
    }


    @Test
    @Tag("200000")
    fun peterG() {
        assertEquals(0, peterG("input/program.txt"))
    }

    @Test
    @Tag("10213")
    fun colors() {
        assertEquals(Pair("GREEN", "BLUE"), colors("input/color.txt"))
    }

    @Test
    @Tag("1232")
    fun midM() {
        assertEquals(Pair(listOf(0.5, -3.3), 0.15), midM("input/mid.txt"))
    }

    @Test
    @Tag("123222")
    fun upcomingDates() {
        assertEquals(Pair(4, 5), upcomingDates("input/upcomingDates.txt"))
    }

    @Test
    @Tag("776")
    fun triangel() {
        assertEquals(12.0, triangel("input/triangels.txt"))
    }

    @Test
    @Tag("232")
    fun grandFather() {
        assertEquals(listOf(Pair("Mike", "Derek")), grandFather("input/humans.txt"))
    }

    @Test
    @Tag("989")
    fun abc() {
        assertEquals("XYZ", abc("input/ABC.txt"))
    }

    @Test
    fun calculateScore() {
        assertEquals(
            mapOf(
                "Зенит" to TeamStats(gamesPlayed = 2, wins = 2, draws = 0, losses = 0, points = 6),
                "Спартак" to TeamStats(gamesPlayed = 2, wins = 1, draws = 0, losses = 1, points = 3),
                "Динамо" to TeamStats(gamesPlayed = 2, wins = 0, draws = 1, losses = 1, points = 1),
                "ЦСКА" to TeamStats(gamesPlayed = 2, wins = 0, draws = 1, losses = 1, points = 1)
            ), calculateScore("input/footballScore.txt")
        )
    }

    @Test
    fun findPhoneNumbers() {
        assertEquals(
            listOf("Мобильный +79211234567"),
            findPhoneNumbers("input/phoneNum.txt", "Михаил Мобильный")
        )
    }
    @Test
    fun apartment(){
        assertEquals(listOf("Школьная 12-14", "Садовая 19-1-55"),apartment("input/apartment.txt","кухня 8"))
    }
}


















