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

package org.rzo.yajsw;

// TODO: Auto-generated Javadoc
/**
 * The Interface Constants.
 */
public interface Constants
{

	/** The Constant DEFAULT_PORT. */
	public static final int DEFAULT_PORT = 15003;

	/** The Constant DEFAULT_SO_TIMEOUT. */
	public static final int DEFAULT_SO_TIMEOUT = 10000;

	/** The Constant DEFAULT_CPU_TIMEOUT. */
	public static final int DEFAULT_CPU_TIMEOUT = 10000;

	/** The Constant DEFAULT_STARTUP_TIMEOUT. */
	public static final int DEFAULT_STARTUP_TIMEOUT = 30;

	/** The Constant DEFAULT_MAX_FAILED_INVOCATIONS. */
	public static final int DEFAULT_MAX_FAILED_INVOCATIONS = 5;

	/** The Constant DEFAULT_SUCCESSFUL_INVOCATION_TIME. */
	public static final int DEFAULT_SUCCESSFUL_INVOCATION_TIME = 60;

	public static final int DEFAULT_EXIT_ON_MAIN_TERMINATE = -1;

	public static final int DEFAULT_EXIT_ON_MAIN_EXCEPTION = 999;

	/** The Constant DEFAULT_PING_INTERVAL. */
	public static final int DEFAULT_PING_INTERVAL = 5;

	/** The Constant DEFAULT_PING_TIMEOUT. */
	public static final int DEFAULT_PING_TIMEOUT = 30;

	/** The Constant DEFAULT_RESTART_DELAY. */
	public static final int DEFAULT_RESTART_DELAY = 5;

	/** The Constant DEFAULT_SHUTDOWN_TIMEOUT. */
	public static final int DEFAULT_SHUTDOWN_TIMEOUT = 30;

	/** The Constant DEFAULT_JVM_EXIT_TIMEOUT. */
	public static final int DEFAULT_JVM_EXIT_TIMEOUT = 15;

	public static final String DEFAULT_DAEMON_RUN_LEVEL_DIR = "rc5.d";
	public static final String DEFAULT_DAEMON_TEMPLATE = "conf/daemon.vm";
	public static final String DEFAULT_DAEMON_DIR = "/etc/init.d";
	public static final String DEFAULT_SYSTEMD_DIR = "/etc/systemd/system";
	public static final String DEFAULT_DAEMON_PID_DIR = "/var/run";
	public static final String DEFAULT_DAEMON_K_ORDER = "99";
	public static final String DEFAULT_DAEMON_S_ORDER = "99";

	public static final int DEFAULT_EXIT_CODE_STOP = 0;
	public static final int DEFAULT_EXIT_CODE_KILL = 999;
	public static final int DEFAULT_EXIT_CODE_FATAL = 999;

	public static final String DEFAULT_SERVICE_START_TYPE = "AUTO_START";

	public static final boolean DEFAULT_CONSOLE_VISIBLE = false;

	public static final String DEFAULT_CONTROL = "TIGHT";

	public static final boolean DEFAULT_RELOAD_CONFIGURATION = false;

	public static final boolean DFAULT_CACHE_LOCAL = false;

	/** The Constant WRAPPER_MSG_START. */
	public static final byte WRAPPER_MSG_START = (byte) 100;

	/** The Constant WRAPPER_MSG_STOP. */
	public static final byte WRAPPER_MSG_STOP = (byte) 101;

	/** The Constant WRAPPER_MSG_RESTART. */
	public static final byte WRAPPER_MSG_RESTART = (byte) 102;

	/** The Constant WRAPPER_MSG_PING. */
	public static final byte WRAPPER_MSG_PING = (byte) 103;

	/** The Constant WRAPPER_MSG_STOP_PENDING. */
	public static final byte WRAPPER_MSG_STOP_PENDING = (byte) 104;

	/** The Constant WRAPPER_MSG_START_PENDING. */
	public static final byte WRAPPER_MSG_START_PENDING = (byte) 105;

	/** The Constant WRAPPER_MSG_STARTED. */
	public static final byte WRAPPER_MSG_STARTED = (byte) 106;

	/** The Constant WRAPPER_MSG_STOPPED. */
	public static final byte WRAPPER_MSG_STOPPED = (byte) 107;

	/** The Constant WRAPPER_MSG_KEY. */
	public static final byte WRAPPER_MSG_KEY = (byte) 110;

	/** The Constant WRAPPER_MSG_BADKEY. */
	public static final byte WRAPPER_MSG_BADKEY = (byte) 111;

	/** The Constant WRAPPER_MSG_LOW_LOG_LEVEL. */
	public static final byte WRAPPER_MSG_LOW_LOG_LEVEL = (byte) 112;

	/** The Constant WRAPPER_MSG_PING_TIMEOUT. */
	public static final byte WRAPPER_MSG_PING_TIMEOUT = (byte) 113;

	/** The Constant WRAPPER_MSG_SERVICE_CONTROL_CODE. */
	public static final byte WRAPPER_MSG_SERVICE_CONTROL_CODE = (byte) 114;

	/** The Constant WRAPPER_MSG_PROPERTIES. */
	public static final byte WRAPPER_MSG_KEYSTORE = (byte) 115;
	public static final byte WRAPPER_MSG_KEYSTORE_RESULT = (byte) 122;

	/** The Constant WRAPPER_MSG_OKKEY. */
	public static final byte WRAPPER_MSG_OKKEY = (byte) 116;

	/** The Constant WRAPPER_MSG_STOP_TIMER. */
	public static final byte WRAPPER_MSG_STOP_TIMER = (byte) 117;

	/** The Constant WRAPPER_MSG_THREAD_DUMP. */
	public static final byte WRAPPER_MSG_THREAD_DUMP = (byte) 118;

	public static final byte WRAPPER_MSG_SERVICE_STARTUP = (byte) 119;

	public static final byte WRAPPER_MSG_GC = (byte) 120;

	public static final byte WRAPPER_MSG_DUMP_HEAP = (byte) 121;

	public static final int DEFAULT_RMI_PORT = 1099;

	public static final String DEFAULT_LOG_FORMAT = "LPNTM";

	public static final boolean DEFAULT_CONSOLE_MINIMIZED = false;

	public static final int MAX_CONC_INVOC = 5;

}
