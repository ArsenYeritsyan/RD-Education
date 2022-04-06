package com.javagang.rdcoursemanagementplatform.utility;

import freemarker.template.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import com.javagang.rdcoursemanagementplatform.constant.Constants;
import com.javagang.rdcoursemanagementplatform.model.dto.InvitationEmailDto;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.Map;
import javax.servlet.ServletContext;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class MailUtility {

  private final JavaMailSenderImpl sender;
  private final ServletContext context;
  private final FreeMarkerConfigurer freemarkerConfig;

  @Async
  public void resetPasswordMail(String email, String jwtToken) {
    SimpleMailMessage message = new SimpleMailMessage();
    String link = context.getContextPath() + Constants.RESET_PASS_LINK + jwtToken;
    message.setTo(email);
    message.setSubject("Password Reset Request");
    message.setText("To reset your password, click the link	" + link);
    sender.send(message);
  }

  //  public void sendMail(String email, String jwtToken) {
  //    SimpleMailMessage message = new SimpleMailMessage();
  //    String link = (context.getContextPath() + Constants.TOKEN_VERIFICATION_LINK + jwtToken);
  //    message.setTo(email);
  //    message.setSubject("Account verification mail");
  //    message.setText(
  //        "Registration Successful to activate your account click on this link   " + link);
  //    sender.send(message);
  //    }

  public void sendEmailOnRegistration() throws Exception {
    MimeMessage message = sender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);
    helper.setTo("rdcourse@mail.com");
    helper.setText("Registered successfully!");
    helper.setSubject("  ");

    sender.send(message);
  }

  public void sendEmailUsingFtl(InvitationEmailDto emailDto, String templatePath)
      throws MailSendException {
    MimeMessage mimeMessage = sender.createMimeMessage();
    try {
      MimeMessageHelper helper =
          new MimeMessageHelper(
              mimeMessage,
              MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
              StandardCharsets.UTF_8.name());
      helper.setFrom(emailDto.getFrom());
      helper.setSubject(emailDto.getSubject());
      helper.setTo(emailDto.getTo());
      String emailBody = prepareEmailBody(templatePath, emailDto.getTemplateData());
      helper.setText(emailBody, Constants.IS_MIME_MESSAGE_TEXT_HTML);
      sender.send(mimeMessage);
    } catch (Exception e) {
      throw new MailSendException(String.format("Failed to send email to %s", emailDto.getTo()));
    }
  }

  private String prepareEmailBody(String path, Map<String, Object> templateData) throws Exception {
    Template template = freemarkerConfig.getConfiguration().getTemplate(path);
    return FreeMarkerTemplateUtils.processTemplateIntoString(template, templateData);
  }
}
