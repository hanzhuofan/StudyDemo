package com.hzf.demo.utils;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhuofan.han
 * @date 2021/9/5
 */
@Slf4j
@Component
public class MessageUtils {
    private static MessageSource messageSource;

    public MessageUtils(MessageSource messageSource) {
        MessageUtils.messageSource = messageSource;
    }

    public static String get(String msgKey) {
        return get(msgKey, new Object[0]);
    }

    public static String get(String msgKey, Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        try {
            return messageSource.getMessage(msgKey, args, locale);
        } catch (Exception e) {
            log.error("[MessageUtils::get] msgKey[{}] does not exist in {}", msgKey, locale.getLanguage());
            return msgKey;
        }
    }
}
