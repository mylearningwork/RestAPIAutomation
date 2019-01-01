package utilities;

import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMailSSLWithAttachment  {
	
	public static void main(String[] args) {
		
		
		sendReportByEmail("friend9831@gmail.com", "ALOK_1234", "FRIEND9831@GMAIL.COM");
		
	}
	
 public static void sendReportByEmail(final String fromEmail, final String fromEmailPassword, String toEmail) {
	 
         
		// Create object of Property file
		Properties props = new Properties();

		// this will set host of server- you can change based on your requirement 
		props.put("mail.smtp.host", "smtp.gmail.com");

		// set the port of socket factory 
		props.put("mail.smtp.socketFactory.port", "465");

		// set socket factory
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

		// set the authentication to true
		props.put("mail.smtp.auth", "true");

		// set the port of SMTP server
		props.put("mail.smtp.port", "465");
		
			
		// This will handle the complete authentication
		Session session = Session.getDefaultInstance(props,

				new javax.mail.Authenticator() {

					protected PasswordAuthentication getPasswordAuthentication() {

					return new PasswordAuthentication(fromEmail, fromEmailPassword);

					}

				});

		try {

			// Create object of MimeMessage class
			Message message = new MimeMessage(session);

			// Set the from address
			message.setFrom(new InternetAddress(fromEmail));

			// Set the recipient address
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(toEmail));
            
                        // Add the subject link
			message.setSubject("Testing Report");

			// Create object to add multimedia type content
			BodyPart messageBodyPart1 = new MimeBodyPart();

			// Set the body of email
			messageBodyPart1.setText("Sap Labs test report");
			// Create another object to add another content
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			// Mention the file which you want to send
			String filename = extentReportFileWithPath();

			// Create data source and pass the filename
			DataSource source = new FileDataSource(filename);

			// set the handler
			messageBodyPart2.setDataHandler(new DataHandler(source));

			// set the file
			messageBodyPart2.setFileName(filename);

			// Create object of MimeMultipart class
			Multipart multipart = new MimeMultipart();

			// add body part 1
			multipart.addBodyPart(messageBodyPart2);

			// add body part 2
			multipart.addBodyPart(messageBodyPart1);

			// set the content
			message.setContent(multipart);

			// finally send the email
			Transport.send(message);

		//	logger.info("=====Email Sent to  "+toEmail+"  =====");

		} catch (MessagingException e) {

			throw new RuntimeException(e);

		}

	}
 /**
  * this method will return the absolute extent report file path for sending in emails.
  * @return  Exact extent report file path
  */
 public static String extentReportFileWithPath() {
	 // Extent report  folder path from config.properties
	 String folderName = "C:\\Users\\22231\\git\\LatestProjectC4\\extentReports";
		File[] listFiles = new File(folderName).listFiles();

	    String fileName = listFiles[0].getName();		      
		// complete extent report path  
		return folderName+fileName;
		
	 
 }

}
