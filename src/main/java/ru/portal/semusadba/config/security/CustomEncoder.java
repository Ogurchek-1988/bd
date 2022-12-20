package ru.portal.semusadba.config.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CustomEncoder extends BCryptPasswordEncoder {
        public boolean upgradeEncoding(java.lang.String encodedPassword) {
            return false;
        }
}
