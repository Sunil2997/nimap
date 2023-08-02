//package com.bountyregister.serviceImpl;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import com.bountyregister.repositories.MailRepository;
//import com.bountyregister.service.EmailInterface;
//
//@Service
//public class EmailServiceImpl implements EmailInterface {
//
//	private static final Logger LOG = LoggerFactory.getLogger(EmailServiceImpl.class);
//
//	@Autowired
//	private JavaMailSender javaMailSender;
//	
//	@Value("${file.upload-dir}")
//	private String myProperty;
//
//	@Value("${SMTP_FROM_EMAIL}")
//	private String fromEmail;
//
//	@Autowired
//	private MailRepository mailRepository;
//	
//	@Override
//	public String sendMail(String emailTo, String subject, String text) {
//
//		try {
//			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//			simpleMailMessage.setFrom(fromEmail);
//			simpleMailMessage.setTo(emailTo);
//			simpleMailMessage.setSubject(subject);
//			simpleMailMessage.setText(text);
//			javaMailSender.send(simpleMailMessage);
//			LOG.info("EmailServiceImpl >> sendMail() >>  Email  Send successfully  >>  ");
//			return "Email Send";
//		} catch (Exception e) {
//			LOG.info("EmailServiceImpl >> sendMail() >>  Email Not Send successfully >>  " + e.getMessage());
//			return "Email Not Send";
//		}
//
//	}
//
//	@Override
//	public int generateOTP() {
//
//		int min = 100000;
//		int max = 999999;
//
//		int randomInt = (int) Math.floor(Math.random() * (max - min + 1) + min);
//		return randomInt;
//
//	}
//
//	@Override
//	public void sendSimpleMessage(String emailTo, String subject, String text) throws MessagingException {
//		try {
//			MimeMessage message = javaMailSender.createMimeMessage();
//			MimeMessageHelper helper = new MimeMessageHelper(message);
//
//			helper.setFrom(fromEmail);
//			helper.setTo(emailTo);
//			helper.setSubject(subject);
//			helper.setText(text, true);
//			javaMailSender.send(message);
//			LOG.info("EmailServiceImpl >> sendSimpleMessage() >>  Email  Send successfully  >>  ");
//		} catch (Exception e) {
//			LOG.info("EmailServiceImpl >> sendSimpleMessage() >>  Email Not Send successfully >>  " + e.getMessage());
//		}
//	}
//
////	@Override
////	public MultipartFile htmlToPdf(String htmlContent, String originalFileName) throws Exception {
////		ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
////
////		PdfWriter pdfWriter = new PdfWriter(pdfOutputStream);
////		PdfDocument pdfDoc = new PdfDocument(pdfWriter);
////		Rectangle rectangle = new Rectangle(710, 520);
////		PageSize pagesize = new PageSize(rectangle);
////		pdfDoc.setDefaultPageSize(pagesize);
////		
////		
////		ConverterProperties converterProperties = new ConverterProperties();
////
////		HtmlConverter.convertToPdf(htmlContent, pdfDoc, converterProperties);
////
////		pdfOutputStream.flush();
////		pdfOutputStream.close();
////
////		byte[] pdfBytes = pdfOutputStream.toByteArray();
////
////		Path pdfPath = Files.createTempFile(originalFileName, ".pdf");
////
////		try (FileOutputStream fos = new FileOutputStream(pdfPath.toFile())) {
////			fos.write(pdfBytes);
////		}
////
////		MultipartFile multipartFile = new MockMultipartFile(originalFileName, originalFileName, "application/pdf",
////				pdfBytes);
////
////		return multipartFile;
////		
////	
////	}
////	
////	@Override
////	public MultipartFile generateImageFromPDFf(String filename,String originalFileName ,String extension) throws IOException {
////		String pdfFilePath = myProperty + "/" + filename + ".pdf";
////		PDDocument document = PDDocument.load(new File(pdfFilePath));
////	    PDFRenderer pdfRenderer = new PDFRenderer(document);
////
////	        BufferedImage bim = pdfRenderer.renderImageWithDPI(
////	          0, 300, ImageType.RGB);
////	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
////	        ImageIO.write(bim, "png", baos);
////	        MultipartFile multipartFile = new MockMultipartFile(filename, originalFileName, 
////	        		MediaType.IMAGE_PNG_VALUE, baos.toByteArray());
////
////	      
////	    
////	    document.close();
////	    return multipartFile;
////	}
//	
//}
