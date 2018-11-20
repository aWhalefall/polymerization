package com.polymerization.core.download

/**
 * Author: yangweichao
 * Date:   2018/11/20 4:13 PM
 * Description: 数据流进度回调
 */


interface ProgressListener {
    /**
     * @param progress     已经下载或上传字节数
     * @param total        总字节数
     * @param isComplete        是否完成
     */
    fun progress(total:Long,progress:Long,isComplete:Boolean)
}