package me.digi.sdk.interapp

import android.content.Intent
import android.os.Bundle

abstract class DMEAppCallbackHandler {

    abstract fun canHandle(requestCode: Int, responseCode: Int, data:Intent?): Boolean

    abstract fun handle(intent: Intent?)

    abstract fun extractAndAppendMetadata(payload: Map<String, Any>)
}