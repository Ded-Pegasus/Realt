package com.innowise.realt.model;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public abstract class ValidFunctions {

    public static Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                FieldError::getDefaultMessage);

        return bindingResult.getFieldErrors().stream().collect(collector);
    }

    public static String saveTitlePhoto(MultipartFile file, String uploadPath,
                                        String title, String tag) throws IOException {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        title = title.replaceAll("\"","");
        title = title.replaceAll("\\\\","");
        title = title.replaceAll("-","");
        title = title.trim();
        String fileName = tag + title + ".jpg";
        file.transferTo(new File(uploadPath + "/" + fileName));
        return fileName;
    }

    public static boolean isDouble(String price) {
        try {
            Double d = Double.parseDouble(price);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isNumeric(String str)
    {
        try
        {
            int d = Integer.parseInt(str.substring(0, str.length()-6));
            d = Integer.parseInt(str.substring(str.length()-6));
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    public static boolean isPhoneNumber(String str)
    {
        return str.length() == 12;
    }
}

