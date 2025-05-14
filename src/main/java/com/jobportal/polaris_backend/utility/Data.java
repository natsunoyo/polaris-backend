package com.jobportal.polaris_backend.utility;

public class Data {
    public static String getMessageBody(String otp, String name){
        return "<!DOCTYPE html>" +
                        "<html lang='uk'>" +
                        "<head>" +
                        "  <meta charset='UTF-8'>" +
                        "  <title>Ваш одноразовий пароль</title>" +
                        "</head>" +
                        "<body style='font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;'>" +
                        "  <div style='background-color: #fff; max-width: 600px; margin: auto; border-radius: 8px; " +
                        "              box-shadow: 0 0 10px rgba(0,0,0,0.1); padding: 30px;'>" +
                        "    <h2>Вітаємо, "+ name +"!</h2>" +
                        "    <p>Ви запитали одноразовий пароль для підтвердження вашої електронної адреси або входу до облікового запису.</p>" +
                        "    <p>Ваш одноразовий пароль:</p>" +
                        "    <div style='font-size: 28px; font-weight: bold; color: #2c3e50; margin: 20px 0;'>" + otp + "</div>" +
                        "    <p>Цей код дійсний протягом <strong>10 хвилин</strong>. Не передавайте його нікому.</p>" +
                        "    <p>Якщо ви не запитували цей код, просто проігноруйте цей лист.</p>" +
                        "    <a href='#' style='display:inline-block;padding:12px 24px;background-color:#007bff;color:white;" +
                        "              text-decoration:none;border-radius:6px;margin-top:20px;'>Перейти до сайту</a>" +
                        "    <div style='font-size:12px;color:#888;text-align:center;margin-top:30px;'>" +
                        "      Ви отримали цей лист, тому що створили обліковий запис або спробували увійти до системи.<br>" +
                        "      Якщо у вас є питання — зверніться до служби підтримки." +
                        "    </div>" +
                "    <hr style='margin: 40px 0; border: none; border-top: 1px solid #ddd;' />" +
                "    <div style='text-align: center; font-size: 13px; color: #999;'>З повагою, команда <strong>Polaris JobEntity Portal</strong></div>" +
                "  </div>" +
                "</body>" +
                "</html>";


    }
}
