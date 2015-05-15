/**
 * 
 */
package com.hussar.app.common.email.batch;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;

/**
 * @author xyi
 *
 */
public class MailSender{

	private static Logger logger = Logger.getLogger(MailSender.class);
	private static Queue<MailInfo> mailInfoQueue = new ConcurrentLinkedQueue<MailInfo>();
	private static MailSenderThread senderThread;
	
	
	public static synchronized void sendMail(String to, String subject, String content){
		put(new MailInfo(to, subject, content));
	}
	
	private static void put(MailInfo mailInfo){
		mailInfoQueue.add(mailInfo);
		startThread();
	}
	
	private static void startThread() {
		if(senderThread != null){
			if(senderThread.isRunning()){
				if(senderThread.isWaiting()){
					senderThread.notifyNow();
				}else{
					logger.info("The send mail thread is running.");
				}
			}else{
				senderThread = null;
				startThread();
			}
		}else{
			senderThread = new MailSenderThread(mailInfoQueue);
			senderThread.start();
		}
    }
	
}
