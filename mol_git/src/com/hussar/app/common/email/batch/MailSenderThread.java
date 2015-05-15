/**
 * 
 */
package com.hussar.app.common.email.batch;

import java.util.Queue;

import com.hussar.app.common.email.SendMail;

/**
 * @author xyi
 *
 */
public class MailSenderThread extends Thread{

	private Queue<MailInfo> mailInfoQueue;
	private boolean runningFlag = true;
	private boolean waitingFlag = false;
	
	public MailSenderThread(Queue<MailInfo> mailInfoQueue){
		this.mailInfoQueue = mailInfoQueue;
	}
	
	public void run(){
		while(runningFlag){
			MailInfo mailInfo = mailInfoQueue.poll();
			if(mailInfo == null){
				synchronized (this) {
					waitingFlag = true;
					waitNow(10 * 60);
					stopThreadNow();
                }
			}else{
				SendMail.send(mailInfo.getTo(), mailInfo.getSubject(), mailInfo.getContent());
			}
		}
	}

	private synchronized void stopThreadNow() {
	    this.runningFlag = false;
	    this.waitingFlag = false;
    }
	
	public synchronized boolean isWaiting(){
		return this.waitingFlag;
	}
	
	public synchronized boolean isRunning(){
		return this.runningFlag;
	}

	private synchronized void waitNow(int second) {
	    try {
	        this.wait(1000 * second);
	    } catch (InterruptedException e) {
	    	waitingFlag = false;
	        e.printStackTrace();
	    }
    }
	
	public synchronized void notifyNow(){
		this.waitingFlag = false;
		this.notifyAll();
	}
	
}
