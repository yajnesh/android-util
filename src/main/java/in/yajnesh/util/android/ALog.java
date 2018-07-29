package in.yajnesh.util.android;

/*
This file is part of AndroidUtils.

AndroidUtils is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
any later version.

Foobar is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with AndroidUtils. If not, see <https://www.gnu.org/licenses/>.

(ɔ) Yajnesh T
*/
import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Better Logger class with multipurpose use
 * <p>
 *
 * @author Yajnesh T
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public class ALog {

    /**
     * Prefix to be added before each log
     */
    private static String sLogPrefix = "";
    /**
     * enable automatically detecting the calling class name
     */
    private static boolean sEnableCallerClassName = true;
    /**
     * If file logging is enabled, in root of sd card, a file with this name will be created
     */
    private static String sFileName = "AndroidLog.txt";
    /**
     * Should enable file logging
     */
    private static boolean sEnableFileLogging = false;

    /**
     * Log levels
     */
    private static boolean sShowWtf = true;
    private static boolean sShowError = true;
    private static boolean sShowWarn = true;
    private static boolean sShowInfo = true;
    private static boolean sShowDebug = true;
    private static boolean sShowVerbose = true;

    /**
     * Enable disable file logging
     *
     * @param enable true for enable, false to disable
     */
    public static void enableFileLogging(boolean enable) {
        sEnableFileLogging = enable;
    }

    /**
     * Sets the filename to be written for each log
     *
     * @param fileName filename or filePath, Eg: "myLog.txt" , "my/directory/myLog.txt"
     */
    public static void setLogFileName(String fileName) {
        sFileName = fileName;
    }

    /**
     * Enable auto detection of the calling class
     *
     * @param enable true to enable
     */
    public static void enableAutoDetectionOfCaller(boolean enable) {
        sEnableCallerClassName = enable;
    }

    /**
     * Set the prefix for each log
     *
     * @param prefix Prefix to be added before each log
     */
    public static void setLogPrefix(String prefix) {
        sLogPrefix = prefix;
    }

    /**
     * Set the log level
     *
     * @param level choose the log level from {@link LogLevel}
     */
    public static void setLogLevel(int level) {
        sShowWtf = false;
        sShowError = false;
        sShowWarn = false;
        sShowInfo = false;
        sShowDebug = false;
        sShowVerbose = false;


        switch (level) {
            case LogLevel.VERBOSE:
                sShowVerbose = true;
            case LogLevel.DEBUG:
                sShowDebug = true;
            case LogLevel.INFO:
                sShowInfo = true;
            case LogLevel.WARN:
                sShowWarn = true;
            case LogLevel.ERROR:
                sShowError = true;
            case LogLevel.WTF:
                sShowWtf = true;
        }
    }


    @NonNull
    private static String g(int type, @Nullable String msg, @Nullable Throwable t, @Nullable Integer resId, @Nullable Context c) {
        try {
            if (resId != null && c != null) {
                msg = c.getString(resId);
            }

            if (msg == null) {
                msg = GenericUtil.EMPTY_STRING;
            }

            if (!canPrintLog(type)) {
                return msg;
            }


            String tag = getCallerClassName();

            switch (type) {
                case LogLevel.WTF:
                    if (sShowWtf) {
                        if (t == null) {
                            Log.wtf(tag, msg);
                        } else {
                            Log.wtf(tag, msg, t);
                        }
                    }
                    break;
                case LogLevel.ERROR:
                    if (sShowError) {
                        if (t == null) {
                            Log.e(tag, msg);
                        } else {
                            Log.e(tag, msg, t);
                        }
                    }
                    break;
                case LogLevel.WARN:
                    if (sShowWarn) {
                        if (t == null) {
                            Log.w(tag, msg);
                        } else {
                            Log.w(tag, msg, t);
                        }
                    }
                    break;
                case LogLevel.INFO:
                    if (sShowInfo) {
                        if (t == null) {
                            Log.i(tag, msg);
                        } else {
                            Log.i(tag, msg, t);
                        }
                    }
                    break;
                case LogLevel.DEBUG:
                    if (sShowDebug) {
                        if (t == null) {
                            Log.d(tag, msg);
                        } else {
                            Log.d(tag, msg, t);
                        }
                    }
                    break;
                case LogLevel.VERBOSE:
                    if (sShowVerbose) {
                        if (t == null) {
                            Log.v(tag, msg);
                        } else {
                            Log.v(tag, msg, t);
                        }
                    }
                    break;

            }

            writeToFile(tag + " : " + msg);
        } catch (Throwable e) {
            Log.e("WTF", "Error while reporting error, having a bad day?");
        }
        //noinspection ConstantConditions , null is made EMPTY_STRING
        return msg;
    }

    private static boolean canPrintLog(int type) {
        switch (type) {
            case LogLevel.WTF:
                if (sShowWtf) return true;
            case LogLevel.ERROR:
                if (sShowError) return true;
            case LogLevel.WARN:
                if (sShowWarn) return true;
            case LogLevel.INFO:
                if (sShowInfo) return true;
            case LogLevel.DEBUG:
                if (sShowDebug) return true;
            case LogLevel.VERBOSE:
                if (sShowVerbose) return true;

        }
        return false;
    }

    /**
     * Write What a Terrible Failure log  <br/><br/>
     * Condition: {@link #setLogLevel(int)} must be set between <code>LogLevel.WTF</code> and <code>LogLevel.VERBOSE</code>
     * <p>
     * if {@link #enableFileLogging(boolean)} is set to true, it will be written to file
     * </p>
     *
     * @param msg log to be printed
     * @return the msg
     */
    @NonNull
    public static String wtf(@Nullable String msg) {
        return g(LogLevel.WTF, msg, null, null, null);
    }

    /**
     * Write What a Terrible Failure log  <br/><br/>
     * Condition: {@link #setLogLevel(int)} must be set between <code>LogLevel.WTF</code> and <code>LogLevel.VERBOSE</code>
     * <p>
     * if {@link #enableFileLogging(boolean)} is set to true, it will be written to file
     * </p>
     *
     * @param msg log to be printed
     * @param t   an exception to log
     * @return the msg
     */
    @NonNull
    public static String wtf(@Nullable String msg, @Nullable Throwable t) {
        return g(LogLevel.WTF, msg, t, null, null);
    }

    /**
     * Write What a Terrible Failure log  <br/><br/>
     * Condition: {@link #setLogLevel(int)} must be set between <code>LogLevel.WTF</code> and <code>LogLevel.VERBOSE</code>
     * <p>
     * if {@link #enableFileLogging(boolean)} is set to true, it will be written to file
     * </p>
     *
     * @param c     Context
     * @param resId String resource id
     * @return The String representing the resId
     */
    @NonNull
    public static String wtf(@Nullable Context c, int resId) {
        return g(LogLevel.WTF, null, null, resId, c);
    }


    /**
     * Write Error log  <br/><br/>
     * Condition: {@link #setLogLevel(int)} must be set between <code>LogLevel.ERROR</code> and <code>LogLevel.VERBOSE</code>
     * <p>
     * if {@link #enableFileLogging(boolean)} is set to true, it will be written to file
     * </p>
     *
     * @param msg log to be printed
     * @return the msg
     */
    @NonNull
    public static String e(@Nullable String msg) {
        return g(LogLevel.ERROR, msg, null, null, null);
    }

    /**
     * Write Error log  <br/><br/>
     * Condition: {@link #setLogLevel(int)} must be set between <code>LogLevel.ERROR</code> and <code>LogLevel.VERBOSE</code>
     * <p>
     * if {@link #enableFileLogging(boolean)} is set to true, it will be written to file
     * </p>
     *
     * @param msg log to be printed
     * @param t   an exception to log
     * @return the msg
     */
    @NonNull
    public static String e(@Nullable String msg, @Nullable Throwable t) {
        return g(LogLevel.ERROR, msg, t, null, null);
    }

    /**
     * Write Error log  <br/><br/>
     * Condition: {@link #setLogLevel(int)} must be set between <code>LogLevel.ERROR</code> and <code>LogLevel.VERBOSE</code>
     * <p>
     * if {@link #enableFileLogging(boolean)} is set to true, it will be written to file
     * </p>
     *
     * @param c     Context
     * @param resId String resource id
     * @return The String representing the resId
     */
    @NonNull
    public static String e(@Nullable Context c, int resId) {
        return g(LogLevel.ERROR, null, null, resId, c);
    }


    /**
     * Write Warn log  <br/><br/>
     * Condition: {@link #setLogLevel(int)} must be set between <code>LogLevel.WARN</code> and <code>LogLevel.VERBOSE</code>
     * <p>
     * if {@link #enableFileLogging(boolean)} is set to true, it will be written to file
     * </p>
     *
     * @param msg log to be printed
     * @return the msg
     */
    @NonNull
    public static String w(@Nullable String msg) {
        return g(LogLevel.WARN, msg, null, null, null);
    }

    /**
     * Write Warn log  <br/><br/>
     * Condition: {@link #setLogLevel(int)} must be set between <code>LogLevel.WARN</code> and <code>LogLevel.VERBOSE</code>
     * <p>
     * if {@link #enableFileLogging(boolean)} is set to true, it will be written to file
     * </p>
     *
     * @param msg log to be printed
     * @param t   an exception to log
     * @return the msg
     */
    @NonNull
    public static String w(@Nullable String msg, @Nullable Throwable t) {
        return g(LogLevel.WARN, msg, t, null, null);
    }

    /**
     * Write Warn log  <br/><br/>
     * Condition: {@link #setLogLevel(int)} must be set between <code>LogLevel.WARN</code> and <code>LogLevel.VERBOSE</code>
     * <p>
     * if {@link #enableFileLogging(boolean)} is set to true, it will be written to file
     * </p>
     *
     * @param c     Context
     * @param resId String resource id
     * @return The String representing the resId
     */
    @NonNull
    public static String w(@Nullable Context c, int resId) {
        return g(LogLevel.WARN, null, null, resId, c);
    }


    /**
     * Write Debug log  <br/><br/>
     * Condition: {@link #setLogLevel(int)} must be set between <code>LogLevel.DEBUG</code> and <code>LogLevel.VERBOSE</code>
     * <p>
     * if {@link #enableFileLogging(boolean)} is set to true, it will be written to file
     * </p>
     *
     * @param msg log to be printed
     * @return the msg
     */
    @NonNull
    public static String d(@Nullable String msg) {
        return g(LogLevel.DEBUG, msg, null, null, null);
    }

    /**
     * Write Debug log  <br/><br/>
     * Condition: {@link #setLogLevel(int)} must be set between <code>LogLevel.DEBUG</code> and <code>LogLevel.VERBOSE</code>
     * <p>
     * if {@link #enableFileLogging(boolean)} is set to true, it will be written to file
     * </p>
     *
     * @param msg log to be printed
     * @param t   an exception to log
     * @return the msg
     */
    @NonNull
    public static String d(@Nullable String msg, @Nullable Throwable t) {
        return g(LogLevel.DEBUG, msg, t, null, null);
    }

    /**
     * Write Debug log  <br/><br/>
     * Condition: {@link #setLogLevel(int)} must be set between <code>LogLevel.DEBUG</code> and <code>LogLevel.VERBOSE</code>
     * <p>
     * if {@link #enableFileLogging(boolean)} is set to true, it will be written to file
     * </p>
     *
     * @param c     Context
     * @param resId String resource id
     * @return The String representing the resId
     */
    @NonNull
    public static String d(@Nullable Context c, int resId) {
        return g(LogLevel.DEBUG, null, null, resId, c);
    }

    /**
     * Write Info log  <br/><br/>
     * Condition: {@link #setLogLevel(int)} must be set between <code>LogLevel.INFO</code> and <code>LogLevel.VERBOSE</code>
     * <p>
     * if {@link #enableFileLogging(boolean)} is set to true, it will be written to file
     * </p>
     *
     * @param msg log to be printed
     * @return the msg
     */
    @NonNull
    public static String i(@Nullable String msg) {
        return g(LogLevel.INFO, msg, null, null, null);
    }

    /**
     * Write Info log  <br/><br/>
     * Condition: {@link #setLogLevel(int)} must be set between <code>LogLevel.INFO</code> and <code>LogLevel.VERBOSE</code>
     * <p>
     * if {@link #enableFileLogging(boolean)} is set to true, it will be written to file
     * </p>
     *
     * @param msg log to be printed
     * @param t   an exception to log
     * @return the msg
     */
    @NonNull
    public static String i(@Nullable String msg, @Nullable Throwable t) {
        return g(LogLevel.INFO, msg, t, null, null);
    }

    /**
     * Write Info log  <br/><br/>
     * Condition: {@link #setLogLevel(int)} must be set between <code>LogLevel.INFO</code> and <code>LogLevel.VERBOSE</code>
     * <p>
     * if {@link #enableFileLogging(boolean)} is set to true, it will be written to file
     * </p>
     *
     * @param c     Context
     * @param resId String resource id
     * @return The String representing the resId
     */
    @NonNull
    public static String i(@Nullable Context c, int resId) {
        return g(LogLevel.INFO, null, null, resId, c);
    }


    /**
     * Write Verbose log  <br/><br/>
     * Condition: {@link #setLogLevel(int)} must be set to <code>LogLevel.VERBOSE</code>
     * <p>
     * if {@link #enableFileLogging(boolean)} is set to true, it will be written to file
     * </p>
     *
     * @param msg log to be printed
     * @return the msg
     */
    @NonNull
    public static String v(@Nullable String msg) {
        return g(LogLevel.VERBOSE, msg, null, null, null);
    }

    /**
     * Write Verbose log  <br/><br/>
     * Condition: {@link #setLogLevel(int)} must be set to <code>LogLevel.VERBOSE</code>
     * <p>
     * if {@link #enableFileLogging(boolean)} is set to true, it will be written to file
     * </p>
     *
     * @param msg log to be printed
     * @param t   an exception to log
     * @return the msg
     */
    @NonNull
    public static String v(@Nullable String msg, @Nullable Throwable t) {
        return g(LogLevel.VERBOSE, msg, t, null, null);
    }

    /**
     * Write Verbose log  <br/><br/>
     * Condition: {@link #setLogLevel(int)} must be set to <code>LogLevel.VERBOSE</code>
     * <p>
     * if {@link #enableFileLogging(boolean)} is set to true, it will be written to file
     * </p>
     *
     * @param c     Context
     * @param resId String resource id
     * @return The String representing the resId
     */
    @NonNull
    public static String v(@Nullable Context c, int resId) {
        return g(LogLevel.VERBOSE, null, null, resId, c);
    }


    /**
     * AbraKadabra :D, This is how we get caller class name.
     */
    @SuppressWarnings("SpellCheckingInspection")
    private static String getCallerClassName() {
        try {
            if (sEnableCallerClassName) {
                StackTraceElement st = GenericUtil.get(new Exception().getStackTrace(), 2);
                return sLogPrefix + (st != null ? st.getClassName() : GenericUtil.EMPTY_STRING);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return sLogPrefix;
    }

    /**
     * Write log to file, if file logging is enabled via {@link #enableFileLogging(boolean) enableFileLogging(true)}
     *
     * @param string the string to be written
     * @see #enableFileLogging(boolean)
     */
    public static synchronized void writeToFile(String string) {

        if (!sEnableFileLogging) {
            return;
        }
        forceWriteToFile(string);
    }

    private static synchronized void forceWriteToFile(String string) {

        try {
            FileWriter fs = new FileWriter(Environment.getExternalStorageDirectory() + File.separator + sFileName, true);
            BufferedWriter fbw = new BufferedWriter(fs);
            fbw.newLine();
            fbw.write(string);
            fbw.newLine();
            fbw.close();
            fs.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    /**
     * Show a short toast
     *
     * @param c    Context
     * @param text Text to be shown
     * @return The text passed
     */
    @Nullable
    public static String toast(Context c, String text) {
        try {
            if (c != null) Toast.makeText(c, text, Toast.LENGTH_SHORT).show();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return text;
    }

    /**
     * Show a short toast
     *
     * @param c     Context
     * @param resId Resource id of the text
     * @return The text passed
     */
    @Nullable
    public static String toast(Context c, int resId) {
        try {
            if (c != null) return toast(c, c.getString(resId));
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Log levels<br/>
     * <p>
     * {@link #WTF} wtf and above <br/>
     * {@link #ERROR} error and above <br/>
     * {@link #WARN} warn and above <br/>
     * {@link #INFO} info and above <br/>
     * {@link #DEBUG} debug and above <br/>
     * {@link #VERBOSE} verbose and above <br/>
     * {@link #NONE} disable all logs
     */
    public static class LogLevel {
        /**
         * wtf and above
         */
        public static final int WTF = -1;

        /**
         * error and above
         */
        public static final int ERROR = 0;
        /**
         * warn and above
         */
        public static final int WARN = 1;
        /**
         * info and above
         */
        public static final int INFO = 2;
        /**
         * debug and above
         */
        public static final int DEBUG = 3;
        /**
         * verbose and above
         */
        public static final int VERBOSE = 4;
        /**
         * disable all logs
         */
        public static final int NONE = 5;
    }


}
