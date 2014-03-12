package com.springapp.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * <p>Description: </p>
 *
 * @author Kim Hyeong Un
 * @since 2014. 3. 3.
 */
@XStreamAlias("response")
@Data
public class Response<M, T> implements Serializable
{
	private int code;
	@XmlElement(name = "msg")
	private M msg;
	private T result;

	public Response() {
	}

	public Response(int code, M msg) {
		this.code = code;
		this.msg = msg;
	}

	public Response(int code, M msg, T result) {
		this.code = code;
		this.msg = msg;
		this.result = result;
	}
}
