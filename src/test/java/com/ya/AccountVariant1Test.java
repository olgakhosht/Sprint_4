package com.ya;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

// Вариант 1. Тесты на каждый класс эквивалентности отдельно
public class AccountVariant1Test {

    @Test
    @DisplayName("Проверка корректной строки (по длине, с пробелом в середине)")
    public void checkCorrectNameToEmboss() {
        String[] correctNames = {"Л И", "Милана Петрановская", "Иван Иванов"};
        for (int i = 0; i <= 2; i++) {
            Account account = new Account(correctNames[i]);
            boolean actual = account.checkNameToEmboss(correctNames[i]);
            assertThat("При корректной строке - false", actual, equalTo(true));
        }
    }

    @Test
    @DisplayName("Проверка строки без значения")
    public void checkNameNullToEmboss() {
        Account account = new Account(null);
        boolean actual = account.checkNameToEmboss(null);
        assertThat("При строке без значения не false", actual, equalTo(false));
    }

    @Test
    @DisplayName("Проверка строки с некорректной длиной")
    public void checkNameWithInvalidLengthToEmboss() {
        String[] namesWithInvalidLength = {"", " ", "Л", "ФИ", "Петр Сидоровиновский", "Сидор Сидоровиновский", "Светланасветланасветлана Светличнаясветличнаясветл"};
        for (int i = 0; i <= 6; i++) {
            Account account = new Account(namesWithInvalidLength[i]);
            boolean actual = account.checkNameToEmboss(namesWithInvalidLength[i]);
            assertThat("При некорректной длине строки - true", actual, equalTo(false));
        }
    }

    @Test
    @DisplayName("Проверка строки с некорректным количеством пробелов")
    public void checkNameWithInvalidCountCancelToEmboss() {
        String[] namesWithInvalidCountCancel = {"ОлегЛукьянов", "Фед ор Прутков", "Вале ра Чер нов", "Илья          Лист"};
        for (int i = 0; i <= 3; i++) {
            Account account = new Account(namesWithInvalidCountCancel[i]);
            boolean actual = account.checkNameToEmboss(namesWithInvalidCountCancel[i]);
            assertThat("При некорректном количестве пробелов в строке - true", actual, equalTo(false));
        }
    }

    @Test
    @DisplayName("Проверка строки с пробелом в начале")
    public void checkNameWithStartSpaceToEmboss() {
        String nameWithStartSpace = " ИринаСветлакова";
        Account account = new Account(nameWithStartSpace);
        boolean actual = account.checkNameToEmboss(nameWithStartSpace);
        assertThat("При пробеле в начале строки - true", actual, equalTo(false));
    }

    @Test
    @DisplayName("Проверка строки с пробелом в конце")
    public void checkNameWithEndSpaceToEmboss() {
        String nameWithEndSpace = "ВикторияКим ";
        Account account = new Account(nameWithEndSpace);
        boolean actual = account.checkNameToEmboss(nameWithEndSpace);
        assertThat("При пробеле в конце строки - true", actual, equalTo(false));
    }
}
