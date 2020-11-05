yajsw-stable-12.15 

    * bug: state change shell script not executed
    * new: posix: support for systemd per default @see docs for details.
    * bug: posix: use java prop java.home instead of java_home
    * new: WRAPPER_MANAGER.keystore(key): access keystore from within the application.
    * bug: evaluating property x = ${x} results in recursion overflow
    * bug: read-only exception in configuration2.EnvironmentConfiguration @see https://sourceforge.net/p/yajsw/support-requests/38/
    * change: use sigar to get subprocess cpu, memory, handles
    * change: upgrade vulnerable libs

Note: this is the last release to support java 1.7. The next relase will support JDK8 - JDK 14
