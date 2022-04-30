package com.ya;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

// Вариант 2. Параметризованный тест. Тестовые данные для всех классов эквивалентности и граничных значений
@RunWith(Parameterized.class)
public class AccountVariant2ParameterizedTest {

    private final String errorMessage;
    private final String nameData;
    private final boolean expected;

    public AccountVariant2ParameterizedTest(String errorMessage, String nameData, boolean expected) {
        this.errorMessage = errorMessage;
        this.nameData = nameData;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                // Тестовые данные с учетом классов эквивалентности и граничных значений
                // Корректные строки (по длине, по количеству пробелов и их расположению)
                {"При корректной строке (3 символа,1 пробел в середине) false вместо true", "Л И", true},
                {"При корректной строке (19 символов, 1 пробел в середине) false вместо true", "Милана Петрановская", true},
                {"При корректной строке (11 символов, 1 пробел в середине) false вместо true", "Иван Иванов", true},
                // Cтроки с некорректной длиной (менее 3-х символов)
                {"При длине строки (1 символ-пробел) true вместо false", " ", false},
                {"При длине строки (1 символ) true вместо false", "Л", false},
                {"При длине строки (2 символа) true вместо false", "ФИ", false},
                // Cтроки с некорректной длиной (более 19-ти символов), пробел в середине
                {"При длине строки (20 символов) true вместо false", "Петр Сидоровиновский", false},
                {"При длине строки (21 символ) true вместо false", "Сидор Сидоровиновский", false},
                {"При длине строки (50 символов) true вместо false", "Светланасветланасветлана Светличнаясветличнаясветл", false},
                // Строки с некорректным количеством пробелов (без пробела, более 1-го пробела), количество символов корректное
                {"БЕЗ пробела в строке true вместо false", "ОлегЛукьянов", false},
                {"С 2-мя пробелами в строке true вместо false", "Фед ор Прутков", false},
                {"С 3-мя пробелами в строке true вместо false", "Вале ра Чер нов", false},
                {"С 10-ю пробелами в строке true вместо false", "Илья          Лист", false},
                // Строки с пробелом в начале строки, в конце строки, количество символов корректное
                {"С пробелом в начале строки true вместо false", " ИринаСветлакова", false},
                {"С пробелом в конце строки true вместо false", "ВикторияКим ", false},
        };
    }

    @Test
    @DisplayName("Проверка строки {nameData}")
    public void checkAllNameToEmboss() {
        Account account = new Account(nameData);
        boolean actual = account.checkNameToEmboss(nameData);
        assertThat(errorMessage, actual, equalTo(expected));
    }
}
