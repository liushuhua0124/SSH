package cn.et.jurisdiction.entity;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Result {
	/**
	 * 0表示失败 1表示成功
	 */
	private int code;
	/**
	 * 失败的消�?
	 */
	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		if (message != null)
			return message.substring(0, 1000);
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setMessage(Exception msg) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		msg.printStackTrace(new PrintStream(baos));
		this.message = new String(baos.toByteArray());
	}
}
