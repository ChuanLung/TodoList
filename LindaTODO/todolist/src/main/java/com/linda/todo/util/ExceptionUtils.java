package com.linda.todo.util;

import java.io.PrintWriter;
import java.io.StringWriter;


public class ExceptionUtils {

    /**
     * 取得錯誤詳細資訊
     *
     * @param e Exception
     * @return 錯誤詳細資訊
     */
    public static String getErrorDetail(Exception e) {
        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));
        return errors.toString();
    }
}
