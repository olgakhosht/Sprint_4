package com.ya;

import org.apache.commons.lang3.StringUtils;

public class Account {
    private final String name;

    public Account(String name) {
        this.name = name;
    }

    public boolean checkNameToEmboss(String name) {
        if (name.length() >= 3 & name.length() <= 19 & StringUtils.countMatches(name, " ") == 1 & name.charAt(0) != ' ' & name.charAt(name.length() - 1) != ' ')
            return true;
        else
            return false;
    }
}
