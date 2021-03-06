/*******************************************************************************
 * Copyright  2015 rzorzorzo@users.sf.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

package org.rzo.yajsw.controller;

import org.rzo.yajsw.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class Message.
 */
public class Message implements Constants
{

	/** The _code. */
	private byte _code;

	/** The _message. */
	private String _message = "";

	/**
	 * Instantiates a new message.
	 * 
	 * @param code
	 *            the code
	 * @param message
	 *            the message
	 */
	public Message(byte code, String message)
	{
		_code = code;
		if (message != null)
			_message = message;
	}

	/**
	 * Gets the code.
	 * 
	 * @return the code
	 */
	public byte getCode()
	{
		return _code;
	}

	/**
	 * Gets the message.
	 * 
	 * @return the message
	 */
	public String getMessage()
	{
		return _message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Message:" + codeToString(_code) + ":" + _message;
	}

	/**
	 * Code to string.
	 * 
	 * @param code
	 *            the code
	 * 
	 * @return the string
	 */
	public static String codeToString(byte code)
	{
		switch (code)
		{
		case WRAPPER_MSG_START:
			return "START";

		case WRAPPER_MSG_STOP:
			return "STOP";

		case WRAPPER_MSG_RESTART:
			return "RESTART";

		case WRAPPER_MSG_PING:
			return "PING";

		case WRAPPER_MSG_STOP_PENDING:
			return "STOP_PENDING";

		case WRAPPER_MSG_START_PENDING:
			return "START_PENDING";

		case WRAPPER_MSG_STARTED:
			return "STARTED";

		case WRAPPER_MSG_STOPPED:
			return "STOPPED";

		case WRAPPER_MSG_KEY:
			return "KEY";

		case WRAPPER_MSG_BADKEY:
			return "BADKEY";

		case WRAPPER_MSG_LOW_LOG_LEVEL:
			return "LOW_LOG_LEVEL";

		case WRAPPER_MSG_PING_TIMEOUT:
			return "PING_TIMEOUT";

		case WRAPPER_MSG_SERVICE_CONTROL_CODE:
			return "SERVICE_CONTROL_CODE";

		case WRAPPER_MSG_KEYSTORE:
			return "KEYSTORE";

		case WRAPPER_MSG_KEYSTORE_RESULT:
			return "KEYSTORE_RESULT";

		case WRAPPER_MSG_OKKEY:
			return "OKKEY";

		case WRAPPER_MSG_THREAD_DUMP:
			return "THREAD_DUMP";

		default:
			return "UNKNOWN(" + code + ")";

		}
	}

}
