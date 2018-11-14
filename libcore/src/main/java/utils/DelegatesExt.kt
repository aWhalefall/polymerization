package utils



/**
 * Author: yangweichao
 * Date:   2018/11/14 1:38 PM
 * Description: 属性委托: http://www.runoob.com/kotlin/kotlin-delegated.html
 */


object DelegatesExt {

    fun <T> preference(name: String, default: T) = Preference(name, default)
}