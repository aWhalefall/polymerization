package retrofit.exception

import android.net.ParseException

import com.google.gson.JsonParseException

import org.json.JSONException

import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

//自己本地产生的Exception，比如解析错误，网络链接错误等等。
object LocalException {

    /**
     * 未知错误
     */
    val UNKNOWN = 1000

    /**
     * 解析错误
     */
    val PARSE_ERROR = 1001

    /**
     * 网络错误
     */
    val NETWORK_ERROR = 1002

    /**
     * 协议错误
     */
    val HTTP_ERROR = 1003

    fun handleException(e: Throwable): ServiceException {
        val ex: ServiceException
        if (e is JsonParseException
                || e is JSONException
                || e is ParseException) {
            //解析错误
            ex = ServiceException(PARSE_ERROR, e.message)
            return ex
        } else if (e is ConnectException) {
            //网络错误
            ex = ServiceException(NETWORK_ERROR, e.message)
            return ex
        } else if (e is UnknownHostException || e is SocketTimeoutException) {
            //连接错误
            ex = ServiceException(NETWORK_ERROR, e.message)
            return ex
        } else {
            //未知错误
            ex = ServiceException(UNKNOWN, e.message)
            return ex
        }
    }
}
