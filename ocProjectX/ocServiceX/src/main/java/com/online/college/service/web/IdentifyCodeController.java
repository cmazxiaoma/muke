package com.online.college.service.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.online.college.common.web.SessionContext;

/**
 *
 * @Description: 验证码生成器
 * @author cmazxiaoma
 * @date 2018-02-08 10:24
 * @version V1.0
 */

@Controller
@RequestMapping("/tools/identiry")
public class IdentifyCodeController {

    @RequestMapping("/code")
    public void init(HttpServletRequest request, HttpServletResponse response) {
        // 随机字母数字
        String random = RandomStringUtils.randomAlphanumeric(4);
        SessionContext.setAttribute(request, SessionContext.IDENTIFY_CODE_kEY, random);

        response.setContentType("image/jpeg");
        response.addHeader("pragma", "NO-cache");
        response.addHeader("Cache-Control", "no-cache");
        response.addDateHeader("Expries", 0);

        int width = 100, height = 33;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();

        // 填充背景色
        graphics.setColor(new Color(255, 255, 255));

        // 设置字体
        Font deFont = new Font("SansSerif", Font.PLAIN, 26);

        graphics.setFont(deFont);
        graphics.fillRect(0, 0, width, height);

        // 设置字体色
        graphics.setColor(Color.BLACK);

        // 设置内容
        graphics.drawString(random, 20, 25);

        // 部署
        graphics.dispose();

        try {
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(image, "JPG", outputStream);
            outputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
