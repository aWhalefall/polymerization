package retrofit.exception

/**
 * Author: yangweichao
 * Date:   2018/11/13 2:25 PM
 * Description: 服务器产生的Excption，比如404，503等等服务器返回的Excption
 */


class ServiceException : Exception {
    var code: Int = 0
    var displayMessage: String? = null

    constructor(code: Int, displayMessage: String?) {
        this.code = code
        this.displayMessage = displayMessage
    }

    constructor(code: Int, message: String, displayMessage: String?) : super(message) {
        this.code = code
        this.displayMessage = displayMessage
    }
}

